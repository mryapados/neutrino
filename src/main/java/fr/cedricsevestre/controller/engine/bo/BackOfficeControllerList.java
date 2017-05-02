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
import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.com.utils.EntityLocator;
import fr.cedricsevestre.controller.engine.AbstractController;
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
import fr.cedricsevestre.exception.UtilException;
import fr.cedricsevestre.service.custom.AlbumService;
import fr.cedricsevestre.service.custom.ProjectService;
import fr.cedricsevestre.service.engine.BaseService;
import fr.cedricsevestre.service.engine.bo.BackOfficeService;
import fr.cedricsevestre.service.engine.independant.objects.StorageService;
import fr.cedricsevestre.service.engine.independant.objects.FolderService;
import fr.cedricsevestre.service.engine.translation.LangService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;
import fr.cedricsevestre.specification.engine.IdProviderSpecification;
import fr.cedricsevestre.specification.engine.TranslationSpecification;

@Controller
public class BackOfficeControllerList extends BackOfficeController {
	@Autowired
	private TemplateService templateService;

	protected static final String BO_OBJECTS = "objects/";
	
	@RequestMapping(value = BO_LIST_URL, method = RequestMethod.GET)
	public ModelAndView list(@ModelAttribute("type") String type, Pageable pageRequest) throws ControllerException, ResourceNotFoundException {

		try {
			Folder folder = getBOFolder();
			ModelAndView modelAndView = baseView(BO_LIST_PAGE, folder);
			
			Class<?> object = entityLocator.getEntity(type).getClass();
			modelAndView.addObject("objectType", object.getSimpleName());
			if (Translation.class.isAssignableFrom(object)){
				modelAndView.addObject("objectBaseType", Translation.class.getSimpleName());
			} else if (NoTranslation.class.isAssignableFrom(object)){
				modelAndView.addObject("objectBaseType", NoTranslation.class.getSimpleName());
			}
			
			modelAndView.addObject("boResources", backOfficeService.getResources(object));
			
			
			NDatas<IdProvider> tDatas = backOfficeService.findAll(object, pageRequest);

			modelAndView.addObject("objectDatas", tDatas.getObjectDatas());
			modelAndView.addObject("datas", tDatas.getObjectDatas().getContent());
			modelAndView.addObject("fields", tDatas.getFields());

			return modelAndView;
		} catch (ServiceException e) {
			throw new ControllerException(e);
		} catch (ClassNotFoundException e) {
			throw new ResourceNotFoundException(type + " Not found !", e);
		}
		
	}

	@RequestMapping(value = BO_BLOCK_LIST_URL + "{type}/{id}/{field}", method = RequestMethod.GET)
	public ModelAndView getAssignableblocklist(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "type") String ownerType, @PathVariable(value = "id") Integer ownerId, @PathVariable(value = "field") String ownerField, Pageable pageRequest) throws ResourceNotFoundException, ControllerException {
		try {
			Lang lang = common.getLang(LocaleContextHolder.getLocale().getLanguage());
			Folder folder = getBOFolder();
			
			fr.cedricsevestre.entity.engine.translation.objects.Page page = common.getPage(folder, BO_LIST_PAGE, lang);
			Template block = templateService.identify(folder, BO_BLOCK_LIST, lang);

			ModelAndView modelAndView = baseView(page, block, null, folder);

			modelAndView.addObject("page", page);
			modelAndView.addObject("activeBlock", block);
			response.addHeader("Object-Type", "parsedBlock");  

			Class<?> ownerObject = entityLocator.getEntity(ownerType).getClass();
			NField nField = backOfficeService.getNField(ownerObject, ownerField);

			Boolean many = Iterable.class.isAssignableFrom(nField.getClazz());
			modelAndView.addObject("many", many);
			
			Class<?> recipientObject = entityLocator.getEntity(many ? nField.getOfClassName() : nField.getClassName()).getClass();
			modelAndView.addObject("objectType", recipientObject.getSimpleName());

			if (Translation.class.isAssignableFrom(recipientObject.getClass())){
				modelAndView.addObject("objectBaseType", Translation.class.getSimpleName());
			} else if (NoTranslation.class.isAssignableFrom(recipientObject.getClass())){
				modelAndView.addObject("objectBaseType", NoTranslation.class.getSimpleName());
			}
		
			String recipientField = nField.getReverseJoin();
			NDatas<IdProvider> tDatas = null;
			if (recipientField != null){
				Specification<IdProvider> spec = IdProviderSpecification.itsFieldIsAffectedTo(recipientField, ownerId);
				spec = Specifications.where(spec).or(IdProviderSpecification.isNotAffected(recipientField));
				tDatas = backOfficeService.findAll(recipientObject, pageRequest, spec);
			} else {
				tDatas = backOfficeService.findAll(recipientObject, pageRequest);
			}
			
			modelAndView.addObject("objectDatas", tDatas.getObjectDatas());
			modelAndView.addObject("datas", tDatas.getObjectDatas().getContent());
			modelAndView.addObject("fields", tDatas.getFields());

			return modelAndView;
			
		} catch (ServiceException e) {
			throw new ControllerException(e);
		} catch (ClassNotFoundException e) {
			throw new ResourceNotFoundException("Ressource not found !", e);
		} catch (UtilException e) {
			throw new ControllerException(e);
		}
	}

	@RequestMapping(value = BO_LIST_URL + BO_OBJECTS + "{type}", method = RequestMethod.GET)
	public @ResponseBody List<IdProviderDto> getObjects(@PathVariable(value = "type") String type, @RequestParam("id") Integer[] ids) throws ControllerException, ResourceNotFoundException {
		try {
			Class<?> object;
			object = entityLocator.getEntity(type).getClass();
			
			// Permet de limiter la requete
			Pageable pageRequest = new PageRequest(0, BO_MAX_REQUEST_ELEMENT);
			List<Integer> list = new ArrayList<>(Arrays.asList(ids));
			
			Specification<IdProvider> spec = IdProviderSpecification.idIn(list);
			Page<IdProvider> datas = backOfficeService.getFullObjects(object, pageRequest, spec);
			
			List<IdProviderDto> idProviderDtos = new ArrayList<>();
			for (IdProvider idProvider : datas) {
				idProviderDtos.add(IdProviderDto.from(idProvider));
			}
			
			return idProviderDtos;
		} catch (ClassNotFoundException e) {
			throw new ResourceNotFoundException(type + " Not found !", e);
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}

	}

}