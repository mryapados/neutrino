package fr.cedricsevestre.controller.engine;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;

@Controller
@Scope("prototype")
public class HomeController extends AbtractController{
	public static final String HOMEPAGE = "home";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView viewHome() {
		return new ModelAndView("/index");
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView view(Folder folder) throws ResourceNotFoundException, ControllerException {
		return baseView(HOMEPAGE, null, folder);
	}
	
}
