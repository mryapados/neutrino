package fr.cedricsevestre.controller.custom;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.controller.engine.AbstractController;
import fr.cedricsevestre.entity.custom.Resume;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.ResumeService;

@Controller
@RequestMapping(value = CategoryController.CATEGORY_MAPPING)
public class CategoryController extends AbstractController {
	protected static final String CATEGORY_MAPPING = "/category";
	protected static final String CATEGORY_URL = "/category";
	protected static final String ATTR_CATEGORY = "name";
		
	@Autowired
	ResumeService resumeService;
	
	@RequestMapping(value = CATEGORY_URL, method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute(ATTR_CATEGORY) String category, Folder folder, HttpServletRequest request) throws ResourceNotFoundException, ControllerException {
		return baseView(category, null, folder);
	}

}
