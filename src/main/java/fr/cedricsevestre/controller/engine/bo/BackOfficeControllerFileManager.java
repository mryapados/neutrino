package fr.cedricsevestre.controller.engine.bo;

import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.bean.NFile;
import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.independant.objects.StorageService;

@Controller
@RequestMapping(value = CommonUtil.BO_URL + CommonUtil.BO_FILE_URL)
public class BackOfficeControllerFileManager extends BackOfficeController {

	private class ResultEncapsulator{
		private Object result;
		public ResultEncapsulator(Object result) {
			super();
			this.result = result;
		}
		public Object getResult() {
			return result;
		}
	}
	
	@Autowired
	private StorageService fileService;

	@RequestMapping(value = BO_FILE_HOME_URL, method = RequestMethod.GET)
	public ModelAndView home() throws ControllerException, ResourceNotFoundException   {
		Folder folder = getBOFolder();
		return baseView(BO_FILE_HOME_PAGE, folder);
	}
	
	@RequestMapping(value = BO_FILE_SINGLE_URL, method = RequestMethod.GET)
	public ModelAndView single(@RequestParam(value = "navbar", required = false, defaultValue = "true") Boolean navbar, @RequestParam(value = "multi", required = false, defaultValue = "true") Boolean multi, @RequestParam(value = "sidebar", required = false, defaultValue = "true") Boolean sidebar) throws ControllerException, ResourceNotFoundException   {
		Folder folder = getBOFolder();
		ModelAndView modelAndView = baseView(BO_FILE_SINGLE_PAGE, folder);
		modelAndView.addObject("navbar", navbar);
		modelAndView.addObject("multi", multi);
		modelAndView.addObject("sidebar", sidebar);
		return modelAndView;
	}
	
	@RequestMapping(value = BO_FILE_ADD_URL, method = RequestMethod.POST)
	public @ResponseBody String add(@RequestParam("file-0") MultipartFile file, @RequestParam("filename") String filename) {
		fileService.store(file);
		
		return "gggggggggggggg";
	}
	
	
	

	@RequestMapping(value = BO_FILE_LIST_URL, method = RequestMethod.POST)
	public @ResponseBody ResultEncapsulator list(@RequestBody Map<String, String> params) throws ControllerException {
		try {
			String path = params.get("path");
			boolean onlyFolders = params.get("onlyFolders") == "true";

			List<NFile> list = fileService.list(path, onlyFolders);
			return new ResultEncapsulator(list);
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}
	}



	
	
	
	
	
	
	
	
}
