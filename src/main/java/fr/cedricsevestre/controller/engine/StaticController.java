package fr.cedricsevestre.controller.engine;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.exception.ServiceException;

@Controller
@Scope("prototype")
@RequestMapping(value = "/static")
public class StaticController extends AbtractController {

	@RequestMapping(value = "/static", method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute("p") String page) {
		System.out.println("static controller");
		
		ModelAndView modelAndView = null;
		
		try {

//			String pageName = page;
//			Page page2 = common.getPage(pageName);
//			Template model = page2.getModel();
//			modelAndView = baseView(model, page2.getContext());
//			modelAndView.addObject("page", page);
			
			modelAndView = baseView(page);
			

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelAndView;
	}

}
