package fr.cedricsevestre.controller.engine.bo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.exception.ServiceException;

@Controller
public class BackOfficeControllerHome extends BackOfficeController {

	@RequestMapping(value = BO_HOME_URL, method = RequestMethod.GET)
	public ModelAndView home() throws JspException   {
		Folder folder = getBOFolder();
		ModelAndView modelAndView = null;
		try {
			modelAndView = baseView(BO_HOME_PAGE, folder);
		} catch (ServiceException e) {
			throw new JspException(e);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = BO_LANGUAGE_URL, method = RequestMethod.GET)
	public ModelAndView language(HttpServletRequest request, RedirectAttributes redirectAttributes) throws JspException {
		String referer = request.getHeader("Referer");
		ModelAndView modelAndView = new ModelAndView("redirect:" + referer);
		return modelAndView;
	}
	
}












