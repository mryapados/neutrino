package fr.cedricsevestre.controller.engine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.exception.UtilException;

@Controller
@Scope("prototype")
public class LoginController extends AbtractController {
	public static final String LOGINPAGE = "login";
	public static final String BACKFOLDER = "back";
	
    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView view(Folder folder) throws ControllerException, ResourceNotFoundException  {
		ModelAndView modelAndView = null;
		try {
			try {
				modelAndView = baseView(LOGINPAGE, null, folder);
			} catch (ResourceNotFoundException e1) {
				folder = common.getFolder(CommonUtil.BACK);
				modelAndView = baseView(LOGINPAGE, null, folder);
			}
			return modelAndView;
		} catch (UtilException e) {
			throw new ControllerException(e);
		}
		
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
