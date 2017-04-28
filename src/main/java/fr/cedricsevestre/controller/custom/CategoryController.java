package fr.cedricsevestre.controller.custom;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping(value = CategoryController.CATEGORY_MAPPING)
public class CategoryController extends AbtractController {
	protected static final String CATEGORY_MAPPING = "/category";
	protected static final String CATEGORY_URL = "/category";

	protected static final String PARAM_RESUME = "resume";
	
	protected static final String ATTR_RESUME = "activeResume";
	protected static final String ATTR_CATEGORY = "cat";
	
	
	@Autowired
	ResumeService resumeService;
	
	@RequestMapping(value = CATEGORY_URL, method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute(ATTR_CATEGORY) String category, Folder folder, HttpServletRequest request) throws ResourceNotFoundException, ControllerException {
		try {
			String resumeName = request.getParameter(PARAM_RESUME);
			ModelAndView modelAndView = baseView(category, null, folder);
			Lang lang = (Lang) modelAndView.getModel().get(ATTR_ACTIVELANG);
			Resume resume = resumeService.identify(folder, resumeName, lang);
			modelAndView.addObject(ATTR_RESUME, resume);
			return modelAndView;
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}

}
