package fr.cedricsevestre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
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
import fr.cedricsevestre.entity.back.Page;
import fr.cedricsevestre.entity.back.Template;
import fr.cedricsevestre.entity.front.Member;
import fr.cedricsevestre.entity.front.User;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.back.TemplateService;
import fr.cedricsevestre.service.front.SessionService;
import fr.cedricsevestre.service.front.UserService;

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

	public ModelAndView baseView(Page page) throws ServiceException {
		Template model = page.getModel();
		String pathContext = page.getContext();
		ModelAndView modelAndView = baseView(model, pathContext);
		
		modelAndView.addObject("page", page);
		
		return modelAndView;
	}
	
	public ModelAndView baseView(Template template, String pathContext) throws ServiceException {
		ModelAndView modelAndView = null;

		String pathModelAndView = "pages/" + pathContext + "/templates/" + templateService.pathType(template) + "/" + template.getPath() + "/" + template.getName();
		System.out.println(pathModelAndView);
		
		modelAndView = new ModelAndView(pathModelAndView);

		modelAndView.addObject("applicationFolder", common.getApplicationFolder());
		//modelAndView.addObject("pathContext", pathContext);

		//modelAndView.addObject("model", template);
		
		modelAndView.addObject("template", template);
		modelAndView.addObject("context", pathContext);
		modelAndView.addObject("initialized", false);
		
		return modelAndView;
	}
	
	
}
