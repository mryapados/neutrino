package fr.cedricsevestre.controller.engine.bo;

import javax.servlet.jsp.JspException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.bean.NData;
import fr.cedricsevestre.bean.NDatas;
import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.ProjectService;
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
	
	public static final String BOHOMEURL = "";
	public static final String BOHOMEPAGE = "@bo_home";
	
	public static final String BOLISTURL = "/list";
	public static final String BOLISTPAGE = "@bo_list";
	
	public static final String BOEDITURL = "/edit";
	public static final String BOEDITPAGE = "@bo_edit";
	
	public static final String BOVIEWURL = "/view";
	public static final String BOVIEWPAGE = "@bo_view";
	
	public static final String BONEWURL = "/new";
	public static final String BONEWPAGE = "@bo_new";
	
	private Folder getBOFolder() throws JspException{
		try {
			return common.getFolder("back");
		} catch (ServiceException e) {
			throw new JspException("Can't obtain BO folder", e);
		}
	}
	
	
	@RequestMapping(value = BOHOMEURL, method = RequestMethod.GET)
	public ModelAndView home() throws JspException   {
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(BOHOMEPAGE, folder);

		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}
	
	

	@RequestMapping(value = BOLISTURL, method = RequestMethod.GET)
	public ModelAndView list(@ModelAttribute("type") String type, Pageable pageRequest) throws JspException   {
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(BOLISTPAGE, folder);

			Class<?> object = BackOfficeService.getEntity(type);
			modelAndView.addObject("objectType", object.getSimpleName());
			if (object.getSuperclass().equals(Translation.class)){
				NDatas<Translation> tDatas = backOfficeTranslationService.findAll(object, pageRequest);
				modelAndView.addObject("datas", tDatas);
			} else if (object.getSuperclass().equals(NoTranslation.class)){
				NDatas<NoTranslation> tDatas = backOfficeNoTranslationService.findAll(object, pageRequest);
				modelAndView.addObject("datas", tDatas);
			}

		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}

	@RequestMapping(value = BOEDITURL, method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("type") String type, @ModelAttribute("id") Integer id) throws JspException   {
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		try {
			
			modelAndView = baseView(BOEDITPAGE, folder);

			Class<?> object = BackOfficeService.getEntity(type);
			modelAndView.addObject("objectType", object.getSimpleName());
			if (object.getSuperclass().equals(Translation.class)){
				NData<Translation> tData = backOfficeTranslationService.findOne(object, id);
				modelAndView.addObject("data", tData);
				modelAndView.addObject("objectLang", tData.getObjectData().getLang());
				modelAndView.addObject("objectName", tData.getObjectData().getName());
				
			} else if (object.getSuperclass().equals(NoTranslation.class)){
				NData<NoTranslation> tData = backOfficeNoTranslationService.findOne(object, id);
				modelAndView.addObject("data", tData);
				modelAndView.addObject("objectName", tData.getObjectData().getName());
			}

			
		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = BOEDITURL, method = RequestMethod.POST)
	public ModelAndView save(@Valid @ModelAttribute("data.objectData") Translation data) throws JspException   {
		
		System.out.println("Enter in save !!!!!");
		
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		
		
		
		return modelAndView;
	}
	
	@RequestMapping(value = BOVIEWURL, method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute("type") String type, @ModelAttribute("id") Integer id) throws JspException   {
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(BOVIEWPAGE, folder);

			Class<?> object = BackOfficeService.getEntity(type);
			modelAndView.addObject("objectType", object.getSimpleName());
			if (object.getSuperclass().equals(Translation.class)){
				NData<Translation> tData = backOfficeTranslationService.findOne(object, id);
				modelAndView.addObject("data", tData);
				modelAndView.addObject("objectLang", tData.getObjectData().getLang());
				modelAndView.addObject("objectName", tData.getObjectData().getName());
				
			} else if (object.getSuperclass().equals(NoTranslation.class)){
				NData<NoTranslation> tData = backOfficeNoTranslationService.findOne(object, id);
				modelAndView.addObject("data", tData);
				modelAndView.addObject("objectName", tData.getObjectData().getName());
			}

		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}
	
	

}
