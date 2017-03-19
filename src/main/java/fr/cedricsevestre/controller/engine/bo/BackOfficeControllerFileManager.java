package fr.cedricsevestre.controller.engine.bo;

import javax.servlet.jsp.JspException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.independant.objects.FileService;

@Controller
@RequestMapping(value = Common.BO_URL + Common.BO_FILE_URL)
public class BackOfficeControllerFileManager extends BackOfficeController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value = BO_FILE_HOME_URL, method = RequestMethod.GET)
	public ModelAndView home() throws JspException   {
		try {
			Folder folder = getBOFolder();
			ModelAndView modelAndView = baseView(BO_FILE_HOME_PAGE, folder);
			return modelAndView;
		} catch (ServiceException e) {
			throw new JspException(e);
		}
	}
	
	
	
	@RequestMapping(value = BO_FILE_ADD_URL, method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		fileService.store(file);
		redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");

		return "redirect:/";
	}

}
