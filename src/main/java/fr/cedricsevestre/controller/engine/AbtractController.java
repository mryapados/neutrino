package fr.cedricsevestre.controller.engine;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.custom.Member;
import fr.cedricsevestre.entity.engine.Page;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.entity.engine.User;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.TemplateService;
import fr.cedricsevestre.service.engine.UserService;

@Controller
@Scope("prototype")
@SessionAttributes( value="blockPreview", types={Boolean.class} )
public class AbtractController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TemplateService templateService;
	
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
	public Boolean addBlocPreviewToSessionScope() throws ServiceException {
		System.out.println("Enter in addBlocPreviewToSessionScope()");
		return false;
	}
	
	@Autowired
	protected Common common;

	public ModelAndView baseView(String pageNameWithoutLangCode) throws ServiceException {
		Locale locale = LocaleContextHolder.getLocale();
		return baseView(common.getPage(pageNameWithoutLangCode, locale.getLanguage()));
	}
	
	
	public ModelAndView baseView(Page page) throws ServiceException {
		if (Common.DEBUG) System.out.println(this.getClass() + " - baseview - page : " + page.getName());
		Template model = page.getModel();
		String pathContext = page.getContext();
		ModelAndView modelAndView = baseView(model, pathContext);
		
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}
	
	public ModelAndView baseView(Template template, String pathContext) throws ServiceException {
		String pathModelAndView = "pages/" + pathContext + "/templates/" + templateService.pathType(template) + "/" + template.getPath();
		ModelAndView modelAndView = new ModelAndView(pathModelAndView);
		modelAndView.addObject("applicationFolder", common.getApplicationFolder());
		modelAndView.addObject("template", template);
		modelAndView.addObject("context", pathContext);
		modelAndView.addObject("initialized", false);
		return modelAndView;
	}
	
	
}
