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

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.common.Common.TypeBase;
import fr.cedricsevestre.entity.custom.Member;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.User;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.independant.objects.UserService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;

@Controller
@Scope("prototype")
@SessionAttributes( value="blockPreview", types={Boolean.class} )
public abstract class AbtractController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	protected Common common;
	
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
	@ModelAttribute("blockPreview")
	public Boolean addBlockPreviewToSessionScope() throws ServiceException {
		System.out.println("Enter in addBlockPreviewToSessionScope()");
		return false;
	}

	public ModelAndView baseView(String pageNameWithoutLangCode, Translation activeObject, Folder folder) throws ServiceException {
		Locale locale = LocaleContextHolder.getLocale();
		return baseView(common.getPage(pageNameWithoutLangCode, locale.getLanguage()), activeObject, folder);
	}
	
	public ModelAndView baseView(Page page, Translation activeObject, Folder folder) throws ServiceException {
		if (Common.DEBUG) System.out.println(this.getClass() + " - baseview - page : " + page.getName());
		Template model = page.getModel();
		ModelAndView modelAndView = baseView(page, model, folder);
		modelAndView.addObject("page", page);
		modelAndView.addObject("activeObject", activeObject);
		return modelAndView;
	}
	
	public ModelAndView baseView(Page page, Template template, Folder folder) throws ServiceException {
		String pathModelAndView = templateService.getPathJSP(false, folder, page.getContext(), template, false);
		ModelAndView modelAndView = new ModelAndView(pathModelAndView);
		modelAndView.addObject("applicationFolder", common.getApplicationFolder());
		modelAndView.addObject("template", template);
		modelAndView.addObject("initialized", false);
		return modelAndView;
	}
	
}
