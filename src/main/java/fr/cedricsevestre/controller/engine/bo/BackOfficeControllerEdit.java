package fr.cedricsevestre.controller.engine.bo;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.validation.Valid;

import org.hibernate.collection.internal.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Timed;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.cedricsevestre.bean.NData;
import fr.cedricsevestre.bean.NDatas;
import fr.cedricsevestre.bean.NField;
import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.dto.engine.IdProviderDto;
import fr.cedricsevestre.dto.engine.LangDto;
import fr.cedricsevestre.dto.engine.NoTranslationDto;
import fr.cedricsevestre.dto.engine.TemplateDto;
import fr.cedricsevestre.dto.engine.TranslationDto;
import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.Position;
import fr.cedricsevestre.entity.engine.independant.objects.User;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.FormException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.AlbumService;
import fr.cedricsevestre.service.custom.ProjectService;
import fr.cedricsevestre.service.engine.BaseService;
import fr.cedricsevestre.service.engine.EntityLocator;
import fr.cedricsevestre.service.engine.bo.BackOfficeService;
import fr.cedricsevestre.service.engine.independant.objects.StorageService;
import fr.cedricsevestre.service.engine.independant.objects.FolderService;
import fr.cedricsevestre.service.engine.translation.LangService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;
import fr.cedricsevestre.specification.engine.IdProviderSpecification;
import fr.cedricsevestre.specification.engine.TranslationSpecification;

@Controller
public class BackOfficeControllerEdit extends BackOfficeController {
	protected static final String REDIRECT = "redirect:/";
	protected static final String REDIRECT_TYPE = "type";
	protected static final String REDIRECT_ID = "id";
	
	protected static final String ATTR_OBJECTTYPE = "objectType";
	protected static final String ATTR_OBJECTBASETYPE = "objectBaseType";
	protected static final String ATTR_OBJECTLANG = "objectLang";
	protected static final String ATTR_FIELD = "fields";
	protected static final String ATTR_OBJECTVIEW = "objectView";
	protected static final String ATTR_OBJECTEDIT = "objectEdit";
	protected static final String ATTR_OBJECTNAME = "objectName";
	protected static final String ATTR_TYPE = "type";
	protected static final String ATTR_ID = "id";
	protected static final String ATTR_LG = "lg";
	
	protected static final String COPY = " [Copy]";
	
	@RequestMapping(value = BO_EDIT_URL, method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(ATTR_TYPE) String type, @RequestParam(ATTR_ID) Integer id) throws ControllerException, ResourceNotFoundException   {
		return edit(type, id, null, false);
	}
	
	@RequestMapping(value = BO_EDIT_URL, method = RequestMethod.POST)
	public ModelAndView save(@RequestParam(ATTR_TYPE) String type, @RequestParam(ATTR_ID) Integer id, @Valid @ModelAttribute(ATTR_OBJECTEDIT) IdProvider data, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ControllerException, ResourceNotFoundException {
		ModelAndView modelAndView = null;
		if (result.hasErrors()) {
			modelAndView = edit(type, id, null, true);
		} else{
			try {
				IdProvider idProvider = backOfficeService.saveData(data);
				modelAndView = new ModelAndView(REDIRECT + Common.BO_URL + BO_VIEW_URL);
				redirectAttributes.addAttribute(REDIRECT_TYPE, idProvider.getObjectType());
				redirectAttributes.addAttribute(REDIRECT_ID, idProvider.getId());
				
			} catch (ServiceException e) {
				throw new ControllerException(e);
			}
		}
		return modelAndView;
	}
	
	public ModelAndView edit(String type, Integer id, Lang lang, Boolean saveError) throws ControllerException, ResourceNotFoundException   {
		//Si id = null cela signifie que c'est un nouveau objet, lang est utile si c'est un objet de type Translation
		try {
			Folder folder = getBOFolder();
			ModelAndView modelAndView = baseView(BO_EDIT_PAGE, folder);

			Class<?> object = entityLocator.getEntity(type).getClass();

			modelAndView.addObject(ATTR_OBJECTTYPE, object.getSimpleName());
			modelAndView.addObject(ATTR_OBJECTBASETYPE, object.getSuperclass().getSimpleName());

			NData<IdProvider> tData;
			if (id == null) {
				tData = backOfficeService.add(object);
				if (Translation.class.isAssignableFrom(object)) {
					Translation translation = (Translation) backOfficeService.translate((Translation) tData.getObjectData(), lang);
					modelAndView.addObject(ATTR_OBJECTLANG, translation.getLang());
				}
			} else {
				tData = backOfficeService.findOne(object, id);
			}

			modelAndView.addObject(ATTR_FIELD, tData.getFields());

			IdProvider objectData = tData.getObjectData();
			if (saveError != null && saveError) {
				modelAndView.addObject(ATTR_OBJECTVIEW, objectData);
			} else {
				modelAndView.addObject(ATTR_OBJECTVIEW, objectData);
				modelAndView.addObject(ATTR_OBJECTEDIT, objectData);
			}
			modelAndView.addObject(ATTR_OBJECTNAME, tData.getObjectData().getName());

			return modelAndView;

		} catch (ServiceException e) {
			throw new ControllerException(e);
		} catch (ClassNotFoundException e) {
			throw new ResourceNotFoundException(type + " Not found !", e);
		}

	}
	
	
	@RequestMapping(value = BO_NEW_TRANSLATION_URL, method = RequestMethod.GET)
	public ModelAndView add(@RequestParam(ATTR_TYPE) String type, @RequestParam(ATTR_LG) String langCode, @RequestParam(value = ATTR_ID, required = false) Integer id, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ControllerException, ResourceNotFoundException   {
		try {
			Lang lang = langService.findByCode(langCode);
			if (lang == null) throw new ResourceNotFoundException(langCode + " Not found !");	
			if (id == null) return edit(type, id, lang, false);
			IdProvider added = copy(type, id, lang);
			ModelAndView modelAndView = new ModelAndView(REDIRECT + Common.BO_URL + BO_EDIT_URL);
			redirectAttributes.addAttribute(REDIRECT_TYPE, added.getObjectType());
			redirectAttributes.addAttribute(REDIRECT_ID, added.getId());
			return modelAndView;
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}
	
	@RequestMapping(value = BO_NEW_URL, method = RequestMethod.GET)
	public ModelAndView add(@RequestParam(ATTR_TYPE) String type, @RequestParam(value = ATTR_ID, required = false) Integer id, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ControllerException, ResourceNotFoundException   {
		if (id == null) return edit(type, id, null, false);
		IdProvider added = copy(type, id, null);
		ModelAndView modelAndView = new ModelAndView(REDIRECT + Common.BO_URL + BO_EDIT_URL);
		redirectAttributes.addAttribute(REDIRECT_TYPE, added.getObjectType());
		redirectAttributes.addAttribute(REDIRECT_ID, added.getId());
		return modelAndView;
	}
	
	
	@RequestMapping(value = BO_NEW_TRANSLATION_URL, method = RequestMethod.POST)
	public ModelAndView neww(@RequestParam(ATTR_TYPE) String type, @RequestParam(ATTR_LG) String langCode, @Valid @ModelAttribute(ATTR_OBJECTEDIT) IdProvider data, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ControllerException, ResourceNotFoundException {
		try {
			Lang lang = langService.findByCode(langCode);
			if (lang == null) throw new ResourceNotFoundException(langCode + " Not found !");	

			ModelAndView modelAndView = null;
			if (result.hasErrors()) {
				modelAndView = edit(type, null, lang, true);
			} else{
				try {
					Translation translation = (Translation) backOfficeService.translate((Translation) data, lang);
					translation = (Translation) backOfficeService.saveData(translation);
					
					modelAndView = new ModelAndView(REDIRECT + Common.BO_URL + BO_VIEW_URL);
					redirectAttributes.addAttribute(REDIRECT_TYPE, translation.getObjectType());
					redirectAttributes.addAttribute(REDIRECT_ID, translation.getId());
					
				} catch (ServiceException e) {
					throw new ControllerException(e);
				}
			}
			return modelAndView;
		
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}
	
	@RequestMapping(value = BO_NEW_URL, method = RequestMethod.POST)
	public ModelAndView neww(@RequestParam(ATTR_TYPE) String type, @Valid @ModelAttribute(ATTR_OBJECTEDIT) IdProvider data, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ControllerException, ResourceNotFoundException {
		ModelAndView modelAndView = null;
		if (result.hasErrors()) {
			modelAndView = edit(type, null, null, true);
		} else{
			try {
				IdProvider idProvider = backOfficeService.saveData(data);					
				
				modelAndView = new ModelAndView(REDIRECT + Common.BO_URL + BO_VIEW_URL);
				redirectAttributes.addAttribute(REDIRECT_TYPE, idProvider.getObjectType());
				redirectAttributes.addAttribute(REDIRECT_ID, idProvider.getId());
				
			} catch (ServiceException e) {
				throw new ControllerException(e);
			}
		}
		return modelAndView;
	}

	public IdProvider copy(String type, Integer id, Lang lang) throws ControllerException, ResourceNotFoundException   {
		try {
			if (id == 0) throw new ControllerException("Id = 0");
			
			Class<?> object = entityLocator.getEntity(type).getClass();
			if (Translation.class.isAssignableFrom(object)) {
				Translation base = (Translation) backOfficeService.getData(object, id);
				if (lang == null) lang = base.getLang();
				Translation translation = (Translation) backOfficeService.translate(base, lang);
				translation.setName(translation.getName() + COPY);
				return backOfficeService.saveData(translation);
			} else if(NoTranslation.class.isAssignableFrom(object)){
				NoTranslation noTranslation = (NoTranslation) backOfficeService.getData(object, id, null);
				noTranslation.setId(null);
				noTranslation.setName(noTranslation.getName() + COPY);
				return backOfficeService.saveData(noTranslation);
			} else {
				IdProvider idProvider = backOfficeService.getData(object, id, null);
				idProvider.setId(null);
				return backOfficeService.saveData(idProvider);
			}

		} catch (ServiceException e) {
			throw new ControllerException(e);
		} catch (ClassNotFoundException e) {
			throw new ResourceNotFoundException(type + " Not found !", e);
		}
	}

}












