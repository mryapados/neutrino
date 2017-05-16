package fr.cedricsevestre.controller.engine.bo;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.bean.NFile;
import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.exception.ControllerException;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.exception.StorageException;
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
	public @ResponseBody ResultEncapsulator add(MultipartRequest multipartRequest, @RequestParam("destination") String filename) {
		
		boolean success = true;
		String errorMessage = null;
		try {
			Map<String, MultipartFile> files = multipartRequest.getFileMap();
			for (Map.Entry<String, MultipartFile> entry : files.entrySet()){
				fileService.store(entry.getValue());
			}
		} catch (StorageException e) {
			success = false;
			errorMessage = e.getMessage();
		}
		Map<String, Object> result = new HashMap<>();
		result.put("success", success);
		result.put("error", errorMessage);
		return new ResultEncapsulator(result);
	}
	
	@RequestMapping(value = BO_FILE_MOVE_URL, method = RequestMethod.POST)
	public @ResponseBody ResultEncapsulator move(@RequestBody Map<String, String> params) throws ControllerException {
		boolean success = true;
		String errorMessage = null;
		try {
			String item = params.get("item");
			String newItemPath = params.get("item");
			
		} catch (StorageException e) {
			success = false;
			errorMessage = e.getMessage();
		}
		Map<String, Object> result = new HashMap<>();
		result.put("success", success);
		result.put("error", errorMessage);
		return new ResultEncapsulator(result);
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
