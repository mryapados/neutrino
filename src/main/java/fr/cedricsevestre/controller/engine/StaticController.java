package fr.cedricsevestre.controller.engine;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.exception.ServiceException;

@Controller
@Scope("prototype")
@RequestMapping(value = "/static")
public class StaticController extends AbtractController {

	@RequestMapping(value = "/static", method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute("p") String page, Folder folder) {
		System.out.println("static controller");
		
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(page, null, folder);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelAndView;
	}

}
