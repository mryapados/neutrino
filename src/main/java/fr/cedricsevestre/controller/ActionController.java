package fr.cedricsevestre.controller;

import java.text.MessageFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.entity.engine.User;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.PageService;
import fr.cedricsevestre.service.engine.UserService;

@Controller
//@Scope("prototype")
@RequestMapping(value = "/@action")
public class ActionController extends AbtractController {

	@Secured({ "ROLE_WEBMASTER", "ROLE_ADMIN", "ROLE_USER" })
	@RequestMapping(value = "/setpreview", method = RequestMethod.POST)
	public ModelAndView setPreview() throws ServiceException {
		return new ModelAndView("admin/action/setPreview");
    }
	

}
