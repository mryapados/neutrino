package fr.cedricsevestre.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.Page;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.exception.ServiceException;

@Controller
@Scope("prototype")
//@RequestMapping(value = "/")
public class HomeController extends AbtractController{

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView viewHome() {
		ModelAndView modelAndView = new ModelAndView("/index");
		return modelAndView;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView view() {
		System.out.println("home controller");
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(Common.HOMEPAGE);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}
	
}
