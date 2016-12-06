package fr.cedricsevestre.controller.engine.bo;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.cedricsevestre.bean.NData;
import fr.cedricsevestre.bean.NDatas;
import fr.cedricsevestre.common.Common;
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
@RequestMapping(value = Common.BASE_BO_VIEWS_PATH)
@Secured({ "ROLE_WEBMASTER", "ROLE_ADMIN", "ROLE_BO" })
public class BackOfficeController extends AbtractController {
	@Autowired
	private BackOfficeService<IdProvider> backOfficeService;
	
	
	
	
	
	@Autowired
	private TemplateService templateService;
	
	
	
	
	
	
	
	@Autowired
	EntityLocator entityLocator;
	
	public static final String BO_HOME_URL = "";
	public static final String BO_HOME_PAGE = "@bo_page_home";
	
	public static final String BO_LIST_URL = "list/";
	public static final String BO_LIST_PAGE = "@bo_page_list";
	
	public static final String BO_EDIT_URL = "edit/";
	public static final String BO_EDIT_PAGE = "@bo_page_edit";
	
	public static final String BO_VIEW_URL = "view/";
	public static final String BO_VIEW_PAGE = "@bo_page_view";
	
	public static final String BO_NEW_URL = "new/";
	public static final String BO_NEW_PAGE = "@bo_page_new";
	
	public static final String BO_DEL_URL = "del/";
	
	private Folder getBOFolder() throws JspException{
		try {
			return common.getFolder(Common.BACK);
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
			modelAndView.addObject("objectType", object.getSimpleName());
			modelAndView.addObject("objectBaseType", object.getSuperclass().getSimpleName());
			
			NDatas<IdProvider> tDatas = backOfficeService.findAll(object, pageRequest);
			//modelAndView.addObject("datas", tDatas);
			
			
			modelAndView.addObject("objectDatas", tDatas.getObjectDatas());
			modelAndView.addObject("datas", tDatas.getObjectDatas().getContent());
			modelAndView.addObject("fields", tDatas.getFields());

		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}

	
	@RequestMapping(value = BO_DEL_URL, method = RequestMethod.POST)
	public ModelAndView delete(@RequestParam("type") String type, @RequestParam("id") Integer[] ids) {

		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		
//		Class<?> object = entityLocator.getEntity(type).getClass();
//		
//		
//		List<IdProvider> idProviders = new ArrayList<>();
//		
//		for (Integer id : ids) {
//			IdProvider data = backOfficeService.getData(object, id);
//			idProviders.add(data);
//		}
		

		
		
		

		
		
		
		
		try {
			
			
			Class<?> object = entityLocator.getEntity(type).getClass();
			
			
			List<Template> idProviders = new ArrayList<>();
			
			for (Integer id : ids) {
				IdProvider data = backOfficeService.getData(object, id);
				idProviders.add((Template) data);
			}
			
			
			templateService.remove(idProviders);
			
			
			
			
			
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		ModelAndView modelAndView = null;
		
		
		
		

		
		
		
		
		
		
		return modelAndView;
//		try {
//
//			
//		} catch (ServiceException e) {
//
//		}
		

	}
	
	@RequestMapping(value = BO_EDIT_URL, method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("type") String type, @ModelAttribute("id") Integer id) throws JspException   {
		return edit(type, id, false);
	}
	
	@RequestMapping(value = BO_EDIT_URL, method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("type") String type, @ModelAttribute("id") Integer id, @Valid @ModelAttribute("object") IdProvider data, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) throws JspException {
		ModelAndView modelAndView = null;
		if (result.hasErrors()) {
			modelAndView = edit(type, id, true);
		} else{
			try {
				backOfficeService.saveData(data);
				modelAndView = new ModelAndView("redirect:/" + Common.BASE_BO_VIEWS_PATH + BO_VIEW_URL);
				redirectAttributes.addAttribute("type", type);
				redirectAttributes.addAttribute("id", id);
				
			} catch (ServiceException e) {
				throw new JspException(e);
			}
		}
		return modelAndView;
	}

	
	
	
	
	
	@RequestMapping(value = BO_NEW_URL, method = RequestMethod.GET)
	public ModelAndView add(@RequestParam("type") String type, @RequestParam(value = "id", required = false) Integer id) throws JspException   {
		if (id == null) id = 0;
		return add(type, id, false);
	}
	
	@RequestMapping(value = BO_NEW_URL, method = RequestMethod.POST)
	public ModelAndView add(@RequestParam("type") String type, @RequestParam(value = "id", required = false) Integer id, @Valid @ModelAttribute("object") IdProvider data, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) throws JspException {
		if (id == null) id = 0;
		ModelAndView modelAndView = null;
		if (result.hasErrors()) {
			modelAndView = add(type, id, true);
		} else{
			try {
				data.setId(null);
				IdProvider res = backOfficeService.saveData(data);				
				modelAndView = new ModelAndView("redirect:/" + Common.BASE_BO_VIEWS_PATH + BO_VIEW_URL);
				redirectAttributes.addAttribute("type", type);
				redirectAttributes.addAttribute("id", res.getId());
			} catch (ServiceException e) {
				throw new JspException(e);
			}
		}
		return modelAndView;
	}
	
	public ModelAndView add(String type, Integer id, Boolean saveError) throws JspException   {
		return edit(type, id, true, saveError);
	}
	public ModelAndView edit(String type, Integer id, Boolean saveError) throws JspException   {
		return edit(type, id, false, saveError);
	}
	public ModelAndView edit(String type, Integer id, Boolean isNew, Boolean saveError) throws JspException   {
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(BO_EDIT_PAGE, folder);
			Class<?> object = entityLocator.getEntity(type).getClass();
			modelAndView.addObject("objectType", object.getSimpleName());
			modelAndView.addObject("objectBaseType", object.getSuperclass().getSimpleName());

			NData<IdProvider> tData = null;
			if (isNew){
				tData = backOfficeService.copy(object, id);
			} else {
				tData = backOfficeService.findOne(object, id);
			}
			
			modelAndView.addObject("fields", tData.getFields());
			
			if (saveError == null || saveError == false){
				modelAndView.addObject("object", tData.getObjectData());
			}

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

		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}
	


}
