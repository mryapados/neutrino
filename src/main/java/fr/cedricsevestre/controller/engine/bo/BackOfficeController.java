package fr.cedricsevestre.controller.engine.bo;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import fr.cedricsevestre.entity.custom.Album;
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
import fr.cedricsevestre.service.engine.translation.objects.PageService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;

@Controller
@Scope("prototype")
@RequestMapping(value = Common.BASE_BO_VIEWS_PATH)
@Secured({ "ROLE_WEBMASTER", "ROLE_ADMIN", "ROLE_BO" })
public class BackOfficeController extends AbtractController {
	@Autowired
	private BackOfficeService backOfficeService;
	
	
	@Autowired
	private PageService pageService;
	
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
	
	public static final String BO_REMOVES_URL = "removes/";
	public static final String BO_REMOVE_URL = "remove/";
	
	public static final String BO_BLOCK_LIST_URL = "blocklist/";
	public static final String BO_BLOCK_LIST = "@bo_block_list";
	
	
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
	public ModelAndView list(@ModelAttribute("type") String type, Pageable pageRequest) throws JspException {
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(BO_LIST_PAGE, folder);
			Class<?> object = entityLocator.getEntity(type).getClass();
			modelAndView.addObject("objectType", object.getSimpleName());
			modelAndView.addObject("objectBaseType", object.getSuperclass().getSimpleName());
			
			NDatas<IdProvider> tDatas = backOfficeService.findAll(object, pageRequest);

			modelAndView.addObject("objectDatas", tDatas.getObjectDatas());
			modelAndView.addObject("datas", tDatas.getObjectDatas().getContent());
			modelAndView.addObject("fields", tDatas.getFields());

		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}

	
	@RequestMapping(value = BO_REMOVE_URL, method = RequestMethod.POST) 
	public ModelAndView delete(@ModelAttribute("type") String type, @RequestParam("id") Integer id, RedirectAttributes redirectAttributes) throws JspException {
		ModelAndView modelAndView = new ModelAndView("redirect:/" + Common.BASE_BO_VIEWS_PATH + BO_LIST_URL);
		redirectAttributes.addAttribute("type", type);
		try {
			delete(type,  new Integer[]{id});
			redirectAttributes.addFlashAttribute("success", true);
		} catch (ServiceException e) {
			modelAndView = new ModelAndView("redirect:/" + Common.BASE_BO_VIEWS_PATH + BO_VIEW_URL);
			redirectAttributes.addAttribute("id", id);
			redirectAttributes.addFlashAttribute("error", e);
			redirectAttributes.addFlashAttribute("success", false);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = BO_REMOVES_URL, method = RequestMethod.POST) 
	public ModelAndView delete(@RequestParam("type") String type, @RequestParam("id") Integer[] ids, RedirectAttributes redirectAttributes) throws JspException {
		ModelAndView modelAndView = new ModelAndView("redirect:/" + Common.BASE_BO_VIEWS_PATH + BO_LIST_URL);
		try {
			delete(type, ids);
			redirectAttributes.addFlashAttribute("success", true);
		} catch (ServiceException e) {
			redirectAttributes.addFlashAttribute("error", e);
			redirectAttributes.addFlashAttribute("success", false);
		}
		redirectAttributes.addAttribute("type", type);
		return modelAndView;
	}
	
	public void delete(String type, Integer[] ids) throws ServiceException {
		Class<?> object = entityLocator.getEntity(type).getClass();
		List<IdProvider> idProviders = new ArrayList<>();
		
		for (Integer id : ids) {
			IdProvider data = backOfficeService.getData(object, id);
			idProviders.add(data);
		}
		backOfficeService.removeDatas(idProviders);
	}

	
	@RequestMapping(value = BO_EDIT_URL, method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute("type") String type, @ModelAttribute("id") Integer id) throws JspException   {
		return edit(type, id, false);
	}
	
	@RequestMapping(value = BO_EDIT_URL, method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("type") String type, @ModelAttribute("id") Integer id, @Valid @ModelAttribute("object") IdProvider data, BindingResult result, HttpServletRequest request, RedirectAttributes redirectAttributes) throws JspException {
		ModelAndView modelAndView = null;
		if (result.hasErrors()) {
			
			System.out.println("errrrrrrrrrrrrrrrrrrrrrrr" + result.getAllErrors().toString());
			
			
			
			
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
				//data.setId(null);
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
			System.out.println("zzzzzzzzzzzzzzzzzzzzzz type = " + type);
			
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
	

	
	private IdProvider mkIdProvider(String objectTypeId) throws IllegalArgumentException{
		System.out.println("<<<<<<<<<<<< mkIdProvider " + objectTypeId);
		String[] identifier = objectTypeId.split("_");
    	
    	String objectType = identifier[0];
    	Class<?> cls = entityLocator.getEntity(objectType).getClass();
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
	
	
	
	
	@InitBinder("object")
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
	
	
	
	
	
	
	
	
	

	
	
	@RequestMapping(value = BO_BLOCK_LIST_URL, method = RequestMethod.GET)
	public ModelAndView blockList(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("type") String type, Pageable pageRequest) throws JspException {
		
		
		
//		
//		try {
////			List<Template> tps = templateService.test();
////			System.out.println(tps.size());
////			
////			Integer value = 1;
////			List<Template> tps2 = templateService.test2("id", value);
////			System.out.println(tps2.size());
//			
//			
//			
//			
//		} catch (ServiceException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		
		
		
		
		
		
		
		
		
		
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		try {
			String langCode = LocaleContextHolder.getLocale().getLanguage();
			fr.cedricsevestre.entity.engine.translation.objects.Page page = common.getPage(BO_LIST_PAGE, langCode);
			Template block = templateService.findByName(BO_BLOCK_LIST + "_" + langCode.toUpperCase());
			modelAndView = baseView(page, block, folder);
			modelAndView.addObject("page", page);
			modelAndView.addObject("activeBlock", block);
			response.addHeader("Object-Type", "parsedBlock");  

			Class<?> object = entityLocator.getEntity(type).getClass();
			modelAndView.addObject("objectType", object.getSimpleName());
			modelAndView.addObject("objectBaseType", object.getSuperclass().getSimpleName());
			
			NDatas<IdProvider> tDatas = backOfficeService.findAll(object, pageRequest);

			modelAndView.addObject("objectDatas", tDatas.getObjectDatas());
			modelAndView.addObject("datas", tDatas.getObjectDatas().getContent());
			modelAndView.addObject("fields", tDatas.getFields());

		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}
	
	
	
	
	
	
	
	
}
