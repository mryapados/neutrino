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
@RequestMapping(value = ArticleController.ARTICLE_MAPPING)
public class ArticleController extends AbstractController {
	protected static final String ARTICLE_MAPPING = "/article";
	protected static final String ARTICLE_URL = "/article";
	protected static final String ATTR_NAME = "name";

	@RequestMapping(value = ARTICLE_URL, method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute(ATTR_NAME) String name, Folder folder) throws ResourceNotFoundException, ControllerException {
		return baseView(name, null, folder);
	}
	
}
