package fr.cedricsevestre.controller.engine.bo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.bean.NData;
import fr.cedricsevestre.bean.NField;
import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.ProjectService;
import fr.cedricsevestre.service.engine.bo.BackOfficeNoTranslationService;
import fr.cedricsevestre.service.engine.bo.BackOfficeService;
import fr.cedricsevestre.service.engine.bo.BackOfficeTranslationService;
import fr.cedricsevestre.service.engine.bo.IBackOfficeObjectService;
import fr.cedricsevestre.service.engine.notranslation.NTObjectService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;
import java.lang.reflect.Field;

@Controller
@Scope("prototype")
@RequestMapping(value = "/bo")
@Secured({ "ROLE_WEBMASTER", "ROLE_ADMIN", "ROLE_BO" })
public class BackOfficeController extends AbtractController {
	
//	@Autowired
//	private ProjectService projectService;
		
//	public static final String HOMEPROJECTPAGE = "project";
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView viewHome(@ModelAttribute("p") String project) {
		ModelAndView modelAndView = null;
//		try {
//			//modelAndView = baseView(HOMEPROJECTPAGE, getActiveObject(project));
//			modelAndView.addObject("project", project);
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return modelAndView;
	}

//	private Translation getActiveObject(String projectName) throws ServiceException{
//		return projectService.findByName(projectName);
//	}
	
	
	
	

	
	@Autowired
	private BackOfficeTranslationService backOfficeTranslationService;

	@Autowired
	private BackOfficeNoTranslationService backOfficeNoTranslationService;
	
	@Autowired
	BackOfficeService backOfficeUtils;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView viewList(@ModelAttribute("type") String type) {
		String pathModelAndView = Common.BASE_BO_VIEWS_PATH + "/list/list";
		ModelAndView modelAndView = new ModelAndView(pathModelAndView);
		
		try {
			Class<?> object = backOfficeUtils.getEntity(type);
			if (object.getSuperclass().equals(Translation.class)){
				NData tData = backOfficeTranslationService.findAll(object);
				modelAndView.addObject("datas", tData);
			} else if (object.getSuperclass().equals(NoTranslation.class)){
				NData tData = backOfficeNoTranslationService.findAll(object);
				modelAndView.addObject("datas", tData);
			}

			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
