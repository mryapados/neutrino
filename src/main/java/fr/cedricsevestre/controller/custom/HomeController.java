package fr.cedricsevestre.controller.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.entity.custom.Resume;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.ResumeService;

@Controller
@Scope("prototype")
@RequestMapping(value = HomeController.HOME_MAPPING)
public class HomeController extends AbtractController{
	protected static final String HOME_MAPPING = "";
	protected static final String HOME_URL = "/";
	protected static final String HOME_URL_INDEX = "/index";
	protected static final String HOME_PAGE = "home";
	
	protected static final String PARAM_RESUME = "resume";
	
	protected static final String ATTR_RESUME = "activeResume";
	protected static final String ATTR_CATEGORY = "cat";

	@Autowired
	ResumeService resumeService;

	@RequestMapping(value = HOME_URL, method = RequestMethod.GET)
	public ModelAndView viewHome() {
		return new ModelAndView(HOME_URL_INDEX);
	}

	@RequestMapping(value = HOME_URL_INDEX, method = RequestMethod.GET)
	public ModelAndView view(Folder folder, HttpServletRequest request) throws ResourceNotFoundException, ControllerException {
		try {
			String resumeName = request.getParameter(PARAM_RESUME);
			ModelAndView modelAndView = baseView(HOME_PAGE, null, folder);
			Lang lang = (Lang) modelAndView.getModel().get(ATTR_ACTIVELANG);
			Resume resume = resumeService.identify(folder, resumeName, lang);
			modelAndView.addObject(ATTR_RESUME, resume);
			return modelAndView;
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}
	
}
