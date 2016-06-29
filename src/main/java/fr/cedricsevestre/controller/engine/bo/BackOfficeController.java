package fr.cedricsevestre.controller.engine.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.NoTranslation;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.ProjectService;
import fr.cedricsevestre.service.engine.NTObjectService;
import fr.cedricsevestre.service.engine.TemplateService;

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
	
	
	
	
//	@Autowired
//	private ObjectService objectService;
	
	@Autowired
	private NTObjectService nTObjectService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView viewList(@ModelAttribute("type") String type) {
		ModelAndView modelAndView = null;
		
		try {
//			
//			List<Object> objects = objectService.findAll();
//			for (Object object : objects) {
//				
//				
//				IdProvider provided = (IdProvider) object;
//				System.out.println(provided.getObjectType() + " - " + provided.getName() + " - " + provided.getId());
//
//				
////				System.out.println(object.toString());
//				
////				System.out.println(noTranslation.getObjectType() + " - " + noTranslation.getName());
//
//				
//			}
			
			
			
			
			List<NoTranslation> noTranslations = nTObjectService.findAll();
			for (NoTranslation noTranslation : noTranslations) {
				System.out.println(noTranslation.getObjectType() + " - " + noTranslation.getName());
			}
//			
			
			
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		try {
//			//modelAndView = baseView(HOMEPROJECTPAGE, getActiveObject(project));
//			modelAndView.addObject("project", project);
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return modelAndView;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

}
