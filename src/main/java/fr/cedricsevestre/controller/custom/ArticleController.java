package fr.cedricsevestre.controller.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.ProjectService;

@Controller
@Scope("prototype")
@RequestMapping(value = "/article")
public class ArticleController extends AbtractController {
	public static final String HOMEARTICLEPAGE = "article";
	
	@RequestMapping(value = "/article", method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute("id") Integer id, Folder folder) throws ResourceNotFoundException, ControllerException {
		return baseView(HOMEARTICLEPAGE, null, folder);
	}
	
}
