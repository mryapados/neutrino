package fr.cedricsevestre.controller.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.ProjectService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/project")
public class ProjectController extends AbtractController {
	
	@Autowired
	private ProjectService projectService;
		
	public static final String HOMEPROJECTPAGE = "project";
	
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute("p") String project, Folder folder) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(HOMEPROJECTPAGE, getActiveObject(project));
			modelAndView.addObject("project", project);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}

	private Translation getActiveObject(String projectName) throws ServiceException{
		return projectService.findByName(projectName);
	}

}
