package fr.cedricsevestre.controller.custom;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.exception.ServiceException;

@Controller
@Scope("prototype")
@RequestMapping(value = "/project")
public class ProjectController extends AbtractController {
	public static final String HOMEPROJECTPAGE = "project";
	
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute("p") String project) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(HOMEPROJECTPAGE);
			modelAndView.addObject("project", project);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}

}
