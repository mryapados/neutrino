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
public class BackOfficeControllerRemove extends BackOfficeController {


	@RequestMapping(value = BO_REMOVE_URL, method = RequestMethod.POST) 
	public ModelAndView delete(@ModelAttribute("type") String type, @RequestParam("id") Integer id, RedirectAttributes redirectAttributes) throws ControllerException, ResourceNotFoundException {
		ModelAndView modelAndView = new ModelAndView("redirect:/" + Common.BO_URL + BO_LIST_URL);
		redirectAttributes.addAttribute("type", type);
		try {
			delete(type,  new Integer[]{id});
			redirectAttributes.addFlashAttribute("success", true);
		} catch (ControllerException e) {
			modelAndView = new ModelAndView("redirect:/" + Common.BO_URL + BO_VIEW_URL);
			redirectAttributes.addAttribute("id", id);
			redirectAttributes.addFlashAttribute("error", e);
			redirectAttributes.addFlashAttribute("success", false);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = BO_REMOVES_URL, method = RequestMethod.POST) 
	public ModelAndView delete(@RequestParam("type") String type, @RequestParam("id") Integer[] ids, RedirectAttributes redirectAttributes) throws ResourceNotFoundException {
		ModelAndView modelAndView = new ModelAndView("redirect:/" + Common.BO_URL + BO_LIST_URL);
		try {
			delete(type, ids);
			redirectAttributes.addFlashAttribute("success", true);
		} catch (ControllerException e) {
			redirectAttributes.addFlashAttribute("error", e);
			redirectAttributes.addFlashAttribute("success", false);
		}
		redirectAttributes.addAttribute("type", type);
		return modelAndView;
	}
	
	public void delete(String type, Integer[] ids) throws ControllerException, ResourceNotFoundException {
		try {
			Class<?> object;
			object = entityLocator.getEntity(type).getClass();
			List<IdProvider> idProviders = new ArrayList<>();
			for (Integer id : ids) {
				IdProvider data = backOfficeService.getData(object, id);
				idProviders.add(data);
			}
			backOfficeService.removeDatas(idProviders);
			
		} catch (ClassNotFoundException e) {
			throw new ResourceNotFoundException(type + " Not found !", e);
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}

}