package fr.cedricsevestre.controller.engine.bo;

import javax.servlet.jsp.JspException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.bean.NData;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;

@Controller
public class BackOfficeControllerView extends BackOfficeController {

	@RequestMapping(value = BO_VIEW_URL, method = RequestMethod.GET)
	public ModelAndView view(@ModelAttribute("type") String type, @ModelAttribute("id") Integer id) throws JspException   {
		try {
			Folder folder = getBOFolder();
			ModelAndView modelAndView = baseView(BO_VIEW_PAGE, folder);
			Class<?> object = entityLocator.getEntity(type).getClass();
			
			System.out.println("		Superclass = " + object.getSuperclass());
			
			modelAndView.addObject("objectType", object.getSimpleName());
			modelAndView.addObject("objectBaseType", object.getSuperclass().getSimpleName());
			
			NData<IdProvider> tData = backOfficeService.findOne(object, id);
			modelAndView.addObject("fields", tData.getFields());
			modelAndView.addObject("objectView", tData.getObjectData());
			modelAndView.addObject("objectName", tData.getObjectData().getName());
			if (object.getSuperclass().equals(Translation.class)){
				Translation translation = (Translation) tData.getObjectData();
				modelAndView.addObject("objectLang", translation.getLang());
			}

			return modelAndView;
		} catch (ServiceException e) {
			throw new JspException(e);
		} catch (ClassNotFoundException e) {
			throw new ResourceNotFoundException(type + " Not found !", e);
		}
		
	}


}











