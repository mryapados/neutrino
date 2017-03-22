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
@Scope("prototype")
@RequestMapping(value = Common.BO_URL)
@Secured({ "ROLE_WEBMASTER", "ROLE_ADMIN", "ROLE_BO" })
public abstract class BackOfficeController extends AbtractController {
	
	protected static final String BO_HOME_URL = "";
	protected static final String BO_HOME_PAGE = "@bo_page_home";
	
	protected static final String BO_LANGUAGE_URL = "language/";
	
	protected static final String BO_EDIT_URL = "edit/";
	protected static final String BO_EDIT_PAGE = "@bo_page_edit";
	
	protected static final String BO_VIEW_URL = "view/";
	protected static final String BO_VIEW_PAGE = "@bo_page_view";
	
	protected static final String BO_NEW_URL = "new/";
	protected static final String BO_NEW_TRANSLATION_URL = "new/translation/";
	
	protected static final String BO_REMOVES_URL = "removes/";
	protected static final String BO_REMOVE_URL = "remove/";
	
	protected static final String BO_LIST_URL = "list/";
	protected static final String BO_LIST_PAGE = "@bo_page_list";

	protected static final String BO_BLOCK_LIST_URL = "blocklist/";
	protected static final String BO_BLOCK_LIST = "@bo_ng_block_list";

	protected static final String BO_FILE_HOME_URL = "";
	protected static final String BO_FILE_HOME_PAGE = "@bo_page_file";
	protected static final String BO_FILE_ADD_URL = "add/";
	protected static final String BO_FILE_LIST_URL = "list/";
	
	protected static final Integer BO_MAX_REQUEST_ELEMENT = 1000;

	@Autowired
	protected EntityLocator entityLocator;
	
	@Autowired
	protected LangService langService;
	
	@Autowired
	protected BackOfficeService backOfficeService;
	
	protected Folder getBOFolder() throws JspException{
		try {
			return common.getFolder(Common.BACK);
		} catch (ServiceException e) {
			throw new JspException("Can't obtain BO folder", e);
		}
	}
	
	private ModelMap init() throws ServiceException{
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute("langs", langService.findAll());			
		return modelMap;
	}
	
	@Override
	public ModelAndView baseView(String pageNameWithoutLangCode, Folder folder) throws ServiceException {
		ModelAndView modelAndView = super.baseView(pageNameWithoutLangCode, folder);
		modelAndView.addAllObjects(init());
		return modelAndView;
	}
	
	
	protected IdProvider mkIdProvider(String objectTypeId) throws IllegalArgumentException{
		if (objectTypeId == null || objectTypeId.equals("")) return null;
		if (objectTypeId.substring(objectTypeId.length() - 1).equals(",")){
			objectTypeId = objectTypeId.substring(0, objectTypeId.length()-1);
		}
		
		System.out.println("<<<<<<<<<<<< mkIdProvider " + objectTypeId);
		String[] identifier = objectTypeId.split("_");
    	
    	String objectType = identifier[0];
    	Class<?> cls;
		try {
			cls = entityLocator.getEntity(objectType).getClass();
		} catch (ClassNotFoundException e) {
			throw new ResourceNotFoundException(objectType + " Not found !", e);
		}
		if (cls == null){
            throw new IllegalArgumentException ("Unknown idProvider type:" + objectType);
		}

    	Integer id = null;
    	if (identifier.length > 1){
    		try {
	    		id = Integer.parseInt(identifier[1]);
			} catch (NumberFormatException e) {
				System.out.println("/////////////////////////// " + "Can't parse " + identifier[1] + " !");
				
				throw new IllegalArgumentException("Can't parse " + identifier[1] + " !", e);
			}
    	}

    	if (id == null){
    		try {
    			System.out.println("<<<<<<<<<<<< new id ");
    			return ((IdProvider) cls.newInstance());
    		} catch (InstantiationException e) {
    			throw new IllegalArgumentException (e.getMessage(),  e);
    		} catch (IllegalAccessException e) {
    			throw new IllegalArgumentException (e.getMessage(),  e);
    		} 
    	} else {
    		try {
    			System.out.println("<<<<<<<<<<<< mkIdProvider id " + id);
    			return (backOfficeService.getData(cls, id));
			} catch (NumberFormatException e) {
				throw new IllegalArgumentException("Can't parse " + identifier[1] + " !", e);
			} catch (ServiceException e) {
				throw new IllegalArgumentException("Can't get " + cls.getName() + ", id = " + id + " !", e);
			}
    	}
	}

	@InitBinder("objectEdit")
	protected void initBinderIdProvider(WebDataBinder binder) {
		System.out.println("1 - initBinderIdProvider " + binder.getFieldDefaultPrefix() + " " + binder.getFieldMarkerPrefix() + " " + binder.getFieldDefaultPrefix() + " " + binder.getObjectName() + " - " + binder.getTarget());

		binder.registerCustomEditor(IdProvider.class, new PropertyEditorSupport() {
		    @Override 
		    public void setAsText(final String objectTypeId) throws IllegalArgumentException
		    {
		    	if(objectTypeId == null || objectTypeId == "") setValue(null);
		    	else {
		    		setValue(mkIdProvider(objectTypeId));
		    	}
		    }
		    @Override
		    public String getAsText() {
		    	System.out.println("getAsText = " + getValue());
			    if(getValue() == null) return "";
			    IdProvider object = (IdProvider) getValue();
			    return object.getObjectType() + "_" + object.getId().toString();
		    }
		});

		binder.registerCustomEditor(Iterable.class, new PropertyEditorSupport() {
		    @Override 
		    public void setAsText(final String listString)
		    {
		    	System.out.println("listString = " + listString);
		    	if(listString == null || listString.trim().length() == 0) {
		    		setValue(null);
		    		return;
		    	}
//		    	String[] objects = listString.split("=");
//		    	
//		    	String[] types = objects[0].split(";");
		    	
		    	

		    	//if (types.length == 2 && types[1].equals(IdProvider.class.getName())){
		    		//Convert string to List of Idprovider if possible
		    		try {
						List<IdProvider> bag = new ArrayList<>();
						//if (objects.length > 1){
							//String[] idProviders = objects[1].split(",");
							String[] idProviders = listString.split(",");
							for (String string : idProviders) {
								IdProvider item = mkIdProvider(string);
								bag.add(item);
							}
						//}
						setValue(bag);
						return;
		    		} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
		    		
		    		
		    		
//		    	} else {
//		    		System.out.println("Set original value");
//		    		//Set original value
//		    		setValue(listString);
//		    		return;
//		    	}
		    	
		    	
		    }

		    @SuppressWarnings("unchecked")
			@Override
		    public String getAsText() {
			    if(getValue() == null) return "";
			    Iterable<Object> list = (Iterable<Object>) getValue();
			    //StringBuilder result = new StringBuilder(list.getClass().getName() + ";" + IdProvider.class.getName() + "=");
			    
			    StringBuilder result = new StringBuilder();
			    //Convert list to Idproviders String if possible
			    for (Object object : list) {
			    	if (object instanceof IdProvider){
			    		IdProvider idProvider = (IdProvider) object;		    		
			    		result.append(idProvider.getObjectType() + "_" + idProvider.getId().toString() + ",");
			    	} else {
			    		//Return original list
			    		return list.toString();
			    	}
				}
			    return result.toString();
		    }
		});
		

		
	}
	

}












