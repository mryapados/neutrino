package fr.cedricsevestre.controller;

import java.text.MessageFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.back.Page;
import fr.cedricsevestre.entity.back.Template;
import fr.cedricsevestre.entity.front.User;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.back.PageService;
import fr.cedricsevestre.service.front.UserService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/project")
public class ProjectController extends AbtractController {

	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute("p") String project) {
		ModelAndView modelAndView = null;
		
		try {

//			String pageName = Common.HOMEPROJECTPAGE;
//			Template model = common.getModelByPageName(pageName);
//			String pathContext = pageName;
//			modelAndView = baseView(model, pathContext);

			modelAndView = baseView(common.getPage(Common.HOMEPROJECTPAGE));
			
			modelAndView.addObject("project", project);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelAndView;
	}

}
