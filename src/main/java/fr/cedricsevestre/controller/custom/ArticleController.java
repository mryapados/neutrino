package fr.cedricsevestre.controller.custom;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.exception.ServiceException;

@Controller
@Scope("prototype")
@RequestMapping(value = "/article")
public class ArticleController extends AbtractController {
	public static final String HOMEARTICLEPAGE = "article";
	
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute("id") Integer id) {
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(HOMEARTICLEPAGE);

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}

}
