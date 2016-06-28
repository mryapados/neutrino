package fr.cedricsevestre.controller.engine.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.ProjectService;
import fr.cedricsevestre.service.engine.TemplateService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/bo")
@Secured({ "ROLE_WEBMASTER", "ROLE_ADMIN", "ROLE_BO" })
public class BackOfficeController extends AbtractController {
	
//	@Autowired
//	private ProjectService projectService;
		
//	public static final String HOMEPROJECTPAGE = "project";
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView viewHome(@ModelAttribute("p") String project) {
		ModelAndView modelAndView = null;
//		try {
//			//modelAndView = baseView(HOMEPROJECTPAGE, getActiveObject(project));
//			modelAndView.addObject("project", project);
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return modelAndView;
	}

//	private Translation getActiveObject(String projectName) throws ServiceException{
//		return projectService.findByName(projectName);
//	}

}
