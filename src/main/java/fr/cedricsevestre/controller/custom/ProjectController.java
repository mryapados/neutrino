package fr.cedricsevestre.controller.custom;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.exception.UtilException;
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
	public ModelAndView view(@ModelAttribute("p") String project, Folder folder) throws ResourceNotFoundException, ControllerException, ServiceException {
		ModelAndView modelAndView = null;
		modelAndView = baseView(HOMEPROJECTPAGE, getActiveObject(project, folder), folder);
		modelAndView.addObject("project", project);
		return modelAndView;
	}

	private Translation getActiveObject(String projectName, Folder folder) throws ControllerException, ResourceNotFoundException{
		try {
			Locale locale = LocaleContextHolder.getLocale();
			return projectService.identify(folder, projectName, common.getLang(locale.getLanguage()));
		} catch (ServiceException | UtilException e) {
			throw new ControllerException(e);
		}
		
		//if not found or folder not equal to project folder return 404 exception.
		
		
	}

}
