package fr.cedricsevestre.controller.engine;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.com.utils.CommonUtil.TypeBase;
import fr.cedricsevestre.entity.custom.Member;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.User;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.JSPNotFoundException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.exception.UtilException;
import fr.cedricsevestre.service.engine.independant.objects.UserService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;

@Controller
@SessionAttributes( value = AbstractController.ATTR_BLOCKPREVIEW, types={Boolean.class} )
public abstract class AbstractController {
	
	protected static final String ATTR_BLOCKPREVIEW = "blockPreview";
	
	protected static final String ATTR_ACTIVELANG = "activeLang";
	protected static final String ATTR_ACTIVEPAGE = "activePage";
	protected static final String ATTR_ACTIVEOBJECT = "activeObject";
	protected static final String ATTR_APPLICATIONFOLDER = "applicationFolder";
	protected static final String ATTR_TEMPLATE = "template";
	protected static final String ATTR_INITIALIZED = "initialized";
	protected static final String ATTR_FOLDER = "folder";
	protected static final String ATTR_LANGUAGE = "language";

	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	protected CommonUtil common;
	
	@ModelAttribute("surfer")
	public User addUserToScope() throws ServiceException {
		System.out.println("Enter in addUserToScope()");
		
		User user = null;
		if (isAuthenticated()){
			System.out.println("user is authenticated");
			org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
			System.out.println("user is null = " + (user == null));
			if (user == null || !user.getLogin().equals(userDetail.getUsername())){
				user = userService.findByLogin(userDetail.getUsername());
				System.out.println("user updated");
			}
		} else {
			user = new Member();
			user.setEnabled(true);
			user.setRole(User.ROLE_PUBLIC);
		}
		return user;
	}
	private boolean isAuthenticated(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
	}
	@ModelAttribute(ATTR_BLOCKPREVIEW)
	public Boolean addBlockPreviewToSessionScope() throws ServiceException {
		System.out.println("Enter in addBlockPreviewToSessionScope()");
		return false;
	}

	public ModelAndView baseView(String pageName, Folder folder) throws ControllerException, ResourceNotFoundException  {
		return baseView(pageName, null, folder);
	}
	public ModelAndView baseView(String pageName, Translation activeObject, Folder folder) throws ControllerException, ResourceNotFoundException {
		try {
			Locale locale = LocaleContextHolder.getLocale();
			return baseView(common.getPage(folder, pageName, common.getLang(locale.getLanguage())), activeObject, folder);
		} catch (UtilException e) {
			throw new ControllerException(e);
		}
	}
	
	public ModelAndView baseView(Page page, Translation activeObject, Folder folder) throws ControllerException, ResourceNotFoundException {
		Template model = page.getModel();
		ModelAndView modelAndView = baseView(page, model, activeObject, folder);
		return modelAndView;
	}
	
	public ModelAndView baseView(Page page, Template template, Translation activeObject, Folder folder) throws ControllerException, ResourceNotFoundException {
		if (CommonUtil.DEBUG) System.out.println(this.getClass() + " - baseview - page : " + page.getName());
		try {
			String pathModelAndView = templateService.getPathJSP(false, folder, page.getContext(), template, false);
			ModelAndView modelAndView = new ModelAndView(pathModelAndView);
			modelAndView.addObject(ATTR_ACTIVEPAGE, page);
			modelAndView.addObject(ATTR_ACTIVEOBJECT, activeObject);
			modelAndView.addObject(ATTR_APPLICATIONFOLDER, common.getApplicationFolder());
			modelAndView.addObject(ATTR_TEMPLATE, template);
			modelAndView.addObject(ATTR_INITIALIZED, false);
			modelAndView.addObject(ATTR_FOLDER, folder);
			Locale locale = LocaleContextHolder.getLocale();
			modelAndView.addObject(ATTR_LANGUAGE, locale.getLanguage());
			modelAndView.addObject(ATTR_ACTIVELANG, common.getLang(locale.getLanguage()));
			return modelAndView;
		} catch (JSPNotFoundException e) {
			throw new ResourceNotFoundException(e);
		} catch (ServiceException | UtilException e) {
			throw new ControllerException(e);
		}
	}
	
}
