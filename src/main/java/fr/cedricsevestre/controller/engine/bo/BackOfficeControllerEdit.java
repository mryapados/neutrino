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
import fr.cedricsevestre.exception.FormException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.AlbumService;
import fr.cedricsevestre.service.custom.ProjectService;
import fr.cedricsevestre.service.engine.BaseService;
import fr.cedricsevestre.service.engine.EntityLocator;
import fr.cedricsevestre.service.engine.bo.BackOfficeService;
import fr.cedricsevestre.service.engine.independant.objects.FileService;
import fr.cedricsevestre.service.engine.independant.objects.FolderService;
import fr.cedricsevestre.service.engine.translation.LangService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;
import fr.cedricsevestre.specification.engine.IdProviderSpecification;
import fr.cedricsevestre.specification.engine.TranslationSpecification;

@Controller
public class BackOfficeControllerEdit extends BackOfficeController {

	@RequestMapping(value = BO_EDIT_URL, method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("type") String type, @ModelAttribute("id") Integer id) throws JspException   {
		return edit(type, id, false);
	}
	
	@RequestMapping(value = BO_EDIT_URL, method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("type") String type, @ModelAttribute("id") Integer id, @Valid @ModelAttribute("objectEdit") IdProvider data, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) throws JspException {
		ModelAndView modelAndView = null;
		if (result.hasErrors()) {
			
			System.out.println("errrrrrrrrrrrrrrrrrrrrrrr" + result.getAllErrors().toString());

			modelAndView = edit(type, id, true);
		} else{
			try {

				
				
				backOfficeService.saveData(data);
				modelAndView = new ModelAndView("redirect:/" + Common.BO_URL + BO_VIEW_URL);
				redirectAttributes.addAttribute("type", type);
				redirectAttributes.addAttribute("id", id);
				
			} catch (ServiceException e) {
				throw new JspException(e);
			}
		}
		return modelAndView;
	}

	
	
	
	@RequestMapping(value = BO_NEW_TRANSLATION_URL, method = RequestMethod.GET)
	public ModelAndView add(@RequestParam("type") String type, @RequestParam("lg") String langCode, @RequestParam(value = "id", required = false) Integer id) throws JspException   {
		try {
			Lang lang;
			lang = langService.findByCode(langCode);
			if (lang == null) throw new ResourceNotFoundException(langCode + " Not found !");	
			if (id == null) id = 0;
			return add(type, id, lang, false);
		} catch (ServiceException e) {
			throw new JspException(e);
		}
	}
	
	@RequestMapping(value = BO_NEW_URL, method = RequestMethod.GET)
	public ModelAndView add(@RequestParam("type") String type, @RequestParam(value = "id", required = false) Integer id) throws JspException   {
		if (id == null) id = 0;
		return add(type, id, null, false);
	}
	
	@RequestMapping(value = BO_NEW_TRANSLATION_URL, method = RequestMethod.POST)
	public ModelAndView add(@RequestParam("type") String type, @RequestParam("lg") String langCode, @RequestParam(value = "id", required = false) Integer id, @Valid @ModelAttribute("objectEdit") Translation data, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) throws JspException {
		
		Lang lang;
		try {
			lang = langService.findByCode(langCode);
		} catch (ServiceException e) {
			throw new JspException(e);
		}
		
		if (id == null) id = 0;
		ModelAndView modelAndView = null;
		if (result.hasErrors()) {
			modelAndView = add(type, id, lang, true);
		} else{
			try {
				Translation base = (Translation) backOfficeService.translate(data, lang);
				Translation res = (Translation) backOfficeService.saveData(data);				
				modelAndView = new ModelAndView("redirect:/" + Common.BO_URL + BO_VIEW_URL);
				redirectAttributes.addAttribute("type", type);
				redirectAttributes.addAttribute("id", res.getId());
			} catch (ServiceException e) {
				throw new JspException(e);
			}
		}
		return modelAndView;
	}
	
	@RequestMapping(value = BO_NEW_URL, method = RequestMethod.POST)
	public ModelAndView add(@RequestParam("type") String type, @RequestParam(value = "id", required = false) Integer id, @Valid @ModelAttribute("objectEdit") IdProvider data, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) throws JspException {
		if (id == null) id = 0;
		ModelAndView modelAndView = null;
		if (result.hasErrors()) {
			modelAndView = add(type, id, null, true);
		} else{
			try {
				IdProvider res = backOfficeService.saveData(data);				
				modelAndView = new ModelAndView("redirect:/" + Common.BO_URL + BO_VIEW_URL);
				redirectAttributes.addAttribute("type", type);
				redirectAttributes.addAttribute("id", res.getId());
			} catch (ServiceException e) {
				throw new JspException(e);
			}
		}
		return modelAndView;
	}
	
	
	public ModelAndView add(String type, Integer id, Lang lang, Boolean saveError) throws JspException   {
		return edit(type, id, lang, true, saveError);
	}
	public ModelAndView edit(String type, Integer id, Boolean saveError) throws JspException   {
		return edit(type, id, null, false, saveError);
	}
	public ModelAndView edit(String type, Integer id, Lang lang, Boolean isNew, Boolean saveError) throws JspException   {
		try {
			Folder folder = getBOFolder();
			ModelAndView modelAndView = baseView(BO_EDIT_PAGE, folder);

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

			IdProvider objectData = tData.getObjectData();
			if (saveError != null && saveError){
				modelAndView.addObject("objectView", objectData);
			} else {
				modelAndView.addObject("objectView", objectData);
				modelAndView.addObject("objectEdit", objectData);
			}

			modelAndView.addObject("objectName", tData.getObjectData().getName());
			if (object.getSuperclass().equals(Translation.class)){
				Translation translation = (Translation) tData.getObjectData();
				
				if (translation.getLang() == null){
					translation.setLang(lang);
				}
				
				modelAndView.addObject("objectLang", translation.getLang());
			}

			return modelAndView;
			
		} catch (ServiceException e) {
			throw new JspException(e);
		} catch (ClassNotFoundException e) {
			throw new ResourceNotFoundException(type + " Not found !", e);
		}
		
	}


}












