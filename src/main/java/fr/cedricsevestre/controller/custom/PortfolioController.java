package fr.cedricsevestre.controller.custom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.controller.engine.AbstractController;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.ResourceNotFoundException;

@Controller
@RequestMapping(value = PortfolioController.PORTFOLIO_MAPPING)
public class PortfolioController extends AbstractController {
	protected static final String PORTFOLIO_MAPPING = "/portfolio";
	protected static final String PORTFOLIO_URL = "/portfolio";
	protected static final String ATTR_NAME = "name";

	@RequestMapping(value = PORTFOLIO_URL, method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute(ATTR_NAME) String name, Folder folder) throws ResourceNotFoundException, ControllerException {
		return baseView(name, null, folder);
	}

}
