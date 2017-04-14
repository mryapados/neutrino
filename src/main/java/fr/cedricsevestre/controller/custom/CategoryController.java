package fr.cedricsevestre.controller.custom;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;

@Controller
@Scope("prototype")
@RequestMapping(value = "/category")
public class CategoryController extends AbtractController {

	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute("cat") String category, Folder folder) throws ResourceNotFoundException, ControllerException {
		return baseView(category, null, folder);
	}

}
