package fr.cedricsevestre.controller.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.controller.engine.AbstractController;
import fr.cedricsevestre.dto.engine.BlockDto;
import fr.cedricsevestre.dto.engine.MapTemplateSimpleDto;
import fr.cedricsevestre.entity.custom.Contact;
import fr.cedricsevestre.entity.custom.Resume;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.FormException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.ContactService;
import fr.cedricsevestre.service.custom.ResumeService;

@Controller
@RequestMapping(value = ContactFormController.CONTACTFORM_MAPPING)
public class ContactFormController extends AbstractController {
	protected static final String CONTACTFORM_MAPPING = "/contact";
	protected static final String CONTACTFORM_SAVE_URL = "/save";

	@Autowired
	ResumeService resumeService;
	
	@Autowired
	ContactService contactService;
	
	@RequestMapping(value = CONTACTFORM_SAVE_URL, method = RequestMethod.POST)
	public @ResponseBody Contact save(@Valid @ModelAttribute Contact contact, BindingResult result, Folder folder) throws ControllerException, ResourceNotFoundException, FormException {
		try {
			if (result.hasErrors()){
				List<String> errors = new ArrayList<>();
				for (ObjectError objectError : result.getAllErrors()) {
					errors.add(objectError.getDefaultMessage());
					System.out.println(objectError.getDefaultMessage());
				}
				throw new FormException("form errors", result.getAllErrors());			
			}
			contact.setFolder(folder);
			return contactService.save(contact);
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}

}
