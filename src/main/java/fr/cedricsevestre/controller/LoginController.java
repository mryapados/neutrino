package fr.cedricsevestre.controller;

import java.text.MessageFormat;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.back.Page;
import fr.cedricsevestre.entity.back.Template;
import fr.cedricsevestre.entity.front.Member;
import fr.cedricsevestre.entity.front.User;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.back.PageService;
import fr.cedricsevestre.service.front.UserService;

@Controller
@Scope("prototype")
public class LoginController extends AbtractController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView view() {
		System.out.println("login controller");
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(Common.LOGINPAGE);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelAndView;
	}
    
    
    
    
    
    
    
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){    
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
	
	
	

}
