package fr.cedricsevestre.controller.engine.bo;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.bean.NData;
import fr.cedricsevestre.bean.NDatas;
import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.Position;
import fr.cedricsevestre.entity.engine.independant.objects.User;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.ProjectService;
import fr.cedricsevestre.service.engine.EntityLocator;
import fr.cedricsevestre.service.engine.bo.BackOfficeService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/bo")
@Secured({ "ROLE_WEBMASTER", "ROLE_ADMIN", "ROLE_BO" })
public class BackOfficeController extends AbtractController {
	@Autowired
	private BackOfficeService<NoTranslation> backOfficeNoTranslationService;
	
	@Autowired
	private BackOfficeService<Translation> backOfficeTranslationService;
	
	@Autowired
	private BackOfficeService<IdProvider> backOfficeService;
	
	
	@Autowired
	private BackOfficeService<User> backOfficeUserService;
	@Autowired
	private BackOfficeService<Position> backOfficePositionService;
	
	@Autowired
	EntityLocator entityLocator;
	
	public static final String BO_HOME_URL = "";
	public static final String BO_HOME_PAGE = "@bo_page_home";
	
	public static final String BO_LIST_URL = "/list";
	public static final String BO_LIST_PAGE = "@bo_page_list";
	
	public static final String BO_EDIT_URL = "/edit";
	public static final String BO_EDIT_PAGE = "@bo_page_edit";
	
	public static final String BO_VIEW_URL = "/view";
	public static final String BO_VIEW_PAGE = "@bo_page_view";
	
	public static final String BO_NEW_URL = "/new";
	public static final String BO_NEW_PAGE = "@bo_page_new";
	
	public static final String NO_TRANSLATION_TYPE = "NoTranslation";
	public static final String TRANSLATION_TYPE = "Translation";
	
	public static final String SAVEURL = "/save";
	
	private Folder getBOFolder() throws JspException{
		try {
			return common.getFolder("back");
		} catch (ServiceException e) {
			throw new JspException("Can't obtain BO folder", e);
		}
	}
	
	
	@RequestMapping(value = BO_HOME_URL, method = RequestMethod.GET)
	public ModelAndView home() throws JspException   {
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(BO_HOME_PAGE, folder);

		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}
	
	

	@RequestMapping(value = BO_LIST_URL, method = RequestMethod.GET)
	public ModelAndView list(@ModelAttribute("type") String type, Pageable pageRequest) throws JspException   {
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(BO_LIST_PAGE, folder);
			Class<?> object = entityLocator.getEntity(type).getClass();
			System.out.println("		Type = " + type + ", Superclass = " + object.getSuperclass() + ", class = " +  object);
			modelAndView.addObject("objectType", object.getSimpleName());
			modelAndView.addObject("objectBaseType", object.getSuperclass().getSimpleName());
			
			NDatas<IdProvider> tDatas = backOfficeService.findAll(object, pageRequest);
			modelAndView.addObject("datas", tDatas);
			
//			if (object.getSuperclass().equals(Translation.class)){
//				NDatas<Translation> tDatas = backOfficeTranslationService.findAll(object, pageRequest);
//				modelAndView.addObject("datas", tDatas);
//			} else if (object.getSuperclass().equals(NoTranslation.class)){
//				NDatas<NoTranslation> tDatas = backOfficeNoTranslationService.findAll(object, pageRequest);
//				modelAndView.addObject("datas", tDatas);
//			} else if (object.getSuperclass().equals(User.class)){
//				NDatas<User> tDatas = backOfficeUserService.findAll(object, pageRequest);
//				modelAndView.addObject("datas", tDatas);
//			} else if (object.equals(Position.class)){
//				NDatas<Position> tDatas = backOfficePositionService.findAll(object, pageRequest);
//				modelAndView.addObject("datas", tDatas);
//			}

		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = BO_EDIT_URL, method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("type") String type, @ModelAttribute("id") Integer id) throws JspException   {
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(BO_EDIT_PAGE, folder);
			Class<?> object = entityLocator.getEntity(type).getClass();
			modelAndView.addObject("objectType", object.getSimpleName());
			modelAndView.addObject("objectBaseType", object.getSuperclass().getSimpleName());

			NData<IdProvider> tData = backOfficeService.findOne(object, id);
			modelAndView.addObject("fields", tData.getFields());
			modelAndView.addObject("object", tData.getObjectData());
			modelAndView.addObject("objectName", tData.getObjectData().getName());
			if (object.getSuperclass().equals(Translation.class)){
				Translation translation = (Translation) tData.getObjectData();
				modelAndView.addObject("objectLang", translation.getLang());
			}

		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = BO_EDIT_URL + "/" + TRANSLATION_TYPE + SAVEURL, method = RequestMethod.POST)
	public ModelAndView translationSave(@Valid @ModelAttribute("object") Translation data, BindingResult result) throws JspException   {
		
		
		
		System.out.println("HasError " + result.hasErrors());
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors().toString());

		}
		
		
		
		
		System.out.println("Enter in save !!!!!");
		
		System.out.println("Translation name = " + data.getName());
		System.out.println("Translation dateAdded = " + data.getDateAdded());
		System.out.println("Translation dateUpdated = " + data.getDateUpdated());
		
		
		Template template = (Template) data;
		System.out.println("Template path = " + template.getPath());

		
		
		try {
			backOfficeTranslationService.saveData(data);
		} catch (ServiceException e) {
			throw new JspException(e);
		}
		
		
		
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		
		
		
		return modelAndView;
	}
	
	@RequestMapping(value = BO_EDIT_URL + "/" + NO_TRANSLATION_TYPE + SAVEURL, method = RequestMethod.POST)
	public ModelAndView noTranslationSave(@Valid @ModelAttribute("object") NoTranslation data, BindingResult result) throws JspException   {
		
		
		
		System.out.println("HasError " + result.hasErrors());
		if (result.hasErrors()) {
			System.out.println(result.getAllErrors().toString());

		}
		
		System.out.println("Enter in save !!!!!");
		
		System.out.println("NoTranslation name = " + data.getName());
		System.out.println("NoTranslation dateAdded = " + data.getDateAdded());
		System.out.println("NoTranslation dateUpdated = " + data.getDateUpdated());
		
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		
		
		
		return modelAndView;
	}
	
	
	
	
 
	
	
	
	
	
	
	@RequestMapping(value = BO_VIEW_URL, method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute("type") String type, @ModelAttribute("id") Integer id) throws JspException   {
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(BO_VIEW_PAGE, folder);

			Class<?> object = entityLocator.getEntity(type).getClass();
			
			System.out.println("		Superclass = " + object.getSuperclass());
			
			modelAndView.addObject("objectType", object.getSimpleName());
			modelAndView.addObject("objectBaseType", object.getSuperclass().getSimpleName());
			
			NData<IdProvider> tData = backOfficeService.findOne(object, id);
			modelAndView.addObject("fields", tData.getFields());
			modelAndView.addObject("object", tData.getObjectData());
			modelAndView.addObject("objectName", tData.getObjectData().getName());
			if (object.getSuperclass().equals(Translation.class)){
				Translation translation = (Translation) tData.getObjectData();
				modelAndView.addObject("objectLang", translation.getLang());
			}
			
			
			
			
//			if (object.getSuperclass().equals(Translation.class)){
//				NData<Translation> tData = backOfficeTranslationService.findOne(object, id);
//				modelAndView.addObject("fields", tData.getFields());
//				modelAndView.addObject("object", tData.getObjectData());
//				modelAndView.addObject("objectLang", tData.getObjectData().getLang());
//				modelAndView.addObject("objectName", tData.getObjectData().getName());
//			} else if (object.getSuperclass().equals(NoTranslation.class)){
//				NData<NoTranslation> tData = backOfficeNoTranslationService.findOne(object, id);
//				modelAndView.addObject("fields", tData.getFields());
//				modelAndView.addObject("object", tData.getObjectData());
//				modelAndView.addObject("objectName", tData.getObjectData().getName());
//			} else if (object.getSuperclass().equals(User.class)){
//				NData<User> tData = backOfficeUserService.findOne(object, id);
//				modelAndView.addObject("fields", tData.getFields());
//				modelAndView.addObject("object", tData.getObjectData());
//				modelAndView.addObject("objectName", tData.getObjectData().getName());
//				
//				
//				
//			}
				
				
				
				
				

		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}
	
	

}
