package fr.cedricsevestre.controller.rest;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.validation.Valid;

import org.hibernate.loader.custom.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.controller.AbtractController;
import fr.cedricsevestre.dao.back.PageDao;
import fr.cedricsevestre.dto.back.BlockDto;
import fr.cedricsevestre.dto.back.LangDto;
import fr.cedricsevestre.dto.back.MapTemplateDto;
import fr.cedricsevestre.dto.back.MapTemplateSimpleDto;
import fr.cedricsevestre.dto.back.PageDto;
import fr.cedricsevestre.dto.back.PositionDto;
import fr.cedricsevestre.dto.back.TemplateDto;
import fr.cedricsevestre.entity.back.Lang;
import fr.cedricsevestre.entity.back.MapTemplate;
import fr.cedricsevestre.entity.back.Page;
import fr.cedricsevestre.entity.back.Position;
import fr.cedricsevestre.entity.back.Template;
import fr.cedricsevestre.entity.back.Template.TemplateType;
import fr.cedricsevestre.exception.FormException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.back.LangService;
import fr.cedricsevestre.service.back.MapTemplateService;
import fr.cedricsevestre.service.back.PageService;
import fr.cedricsevestre.service.back.PositionService;
import fr.cedricsevestre.service.back.TemplateService;

@RestController
@Scope("prototype")
@RequestMapping(value = "/@back")
@Secured({ "ROLE_WEBMASTER", "ROLE_ADMIN" })
public class BackController extends AbtractController {
	
	@Autowired
	private LangService langService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private TemplateService templateService;

	@Autowired
	private PositionService positionService;
	
	@Autowired
	private MapTemplateService mapTemplateService;
	
	@RequestMapping(value = "/templates/exist/", method = RequestMethod.GET)
	public @ResponseBody Boolean checkJSPExist(@ModelAttribute("context") String context, @ModelAttribute("type") String type, @ModelAttribute("path") String path, @ModelAttribute("name") String name) throws ServiceException {
		System.out.println("context = " + context);
		System.out.println("type = " + type);
		System.out.println("path = " + path);
		System.out.println("name = " + name);
		
		TemplateDto templateDto = new TemplateDto();
		templateDto.setType(TemplateType.valueOf(type));
		templateDto.setPath(path);
		templateDto.setName(name);
		Template template = TemplateDto.to(templateDto);
		return templateService.checkJSPExist(common.getWebInfFolder(), context, template);
	}

	@RequestMapping(value = "/positions", method = RequestMethod.GET)
	public @ResponseBody Map<String, PositionDto> getPositions() throws ServiceException {
		List<Position> positions = positionService.findAll();
		Map<String, PositionDto> mapPositions = new HashMap<>();
		for (Position position : positions) {
			mapPositions.put(position.getName(), PositionDto.fromWithoutMapTemplate(position));
		}
		return mapPositions;
	}

	@RequestMapping(value = "models/{model}/positions/{position}/blocks", method = RequestMethod.GET)
	public @ResponseBody List<BlockDto> getBlocksForPosition(
			@PathVariable(value = "model") String modelName, 
			@PathVariable(value = "position") String positionName) throws ServiceException {
		List<BlockDto> blockDtos = new ArrayList<>();
		Template template = templateService.findByName(modelName);
		Position pos = positionService.findByNameForModelWithMaps(template, positionName);
		if (pos != null){
			List<MapTemplate> mapTemplates;
			mapTemplates = pos.getMapTemplates();
			for (MapTemplate mapTemplate : mapTemplates) {
				blockDtos.add(BlockDto.from(mapTemplate));
			}
		}		
		return blockDtos;
	}

	
	


	@RequestMapping(value = "/parsedblock/{pageName}/{blockName}", method = RequestMethod.GET)
	public ModelAndView getParsedBlock(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "pageName") String pageName, @PathVariable(value = "blockName") String blockName) throws ServiceException {
		ModelAndView modelAndView = null;
		try {
			Page page = pageService.findByName(pageName);
			Template block = templateService.findByName(blockName);
			String pathContext = page.getContext();
			
			modelAndView = baseView(block, pathContext);
			modelAndView.addObject("page", page);
			modelAndView.addObject("activeBlock", block);
			response.addHeader("Object-Type", "parsedBlock");  
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@Deprecated
	@RequestMapping(value = "/addmapblock", method = RequestMethod.POST)
	public @ResponseBody MapTemplateDto exSaveMapBlock(@RequestBody MapTemplateSimpleDto mapTemplateSimpleDto) throws ServiceException {
		System.out.println(mapTemplateSimpleDto.toString());
		
		MapTemplate mapTemplate = new MapTemplate();
		mapTemplate.setModel(templateService.findByName(mapTemplateSimpleDto.getModelName()));
		mapTemplate.setBlock(templateService.findByName(mapTemplateSimpleDto.getBlockName()));
		mapTemplate.setPosition(positionService.findByName(mapTemplateSimpleDto.getPositionName()));
		mapTemplate.setOrdered(mapTemplateSimpleDto.getOrdered());
		return MapTemplateDto.from(mapTemplateService.saveAndOrder(mapTemplate));
	}
	
	
	@RequestMapping(value = "/maptemplates", method = RequestMethod.POST)
	public @ResponseBody BlockDto saveMapBlock(@Valid @RequestBody MapTemplateSimpleDto mapTemplateSimpleDto, BindingResult result) throws ServiceException, FormException {
		if (result.hasErrors()){
			List<String> errors = new ArrayList<>();
			for (ObjectError objectError : result.getAllErrors()) {
				errors.add(objectError.getDefaultMessage());
				System.out.println(objectError.getDefaultMessage());
			}
			throw new FormException("form errors", result.getAllErrors());			
		}

		System.out.println(mapTemplateSimpleDto.toString());
		
		MapTemplate mapTemplate = new MapTemplate();
		mapTemplate.setModel(templateService.findByName(mapTemplateSimpleDto.getModelName()));
		mapTemplate.setBlock(templateService.findByName(mapTemplateSimpleDto.getBlockName()));
		mapTemplate.setPosition(positionService.findByName(mapTemplateSimpleDto.getPositionName()));
		mapTemplate.setOrdered(mapTemplateSimpleDto.getOrdered());
		return BlockDto.from(mapTemplateService.saveAndOrder(mapTemplate));
	}
	
	@RequestMapping(value = "maptemplates/{id}", method = RequestMethod.DELETE)
	public BlockDto delete(@PathVariable("id") Integer mapBlockId) throws ServiceException {
		MapTemplate mapTemplate = mapTemplateService.findById(mapBlockId);
		mapTemplateService.removeById(mapBlockId);
		return BlockDto.from(mapTemplate);
	}
	
	// RESTFull (ngResource)
	@RequestMapping(value = "/langs", method = RequestMethod.GET)
	public @ResponseBody List<LangDto> getLangs() throws ServiceException {
		List<Lang> langs = langService.findAll();
		List<LangDto> langsDto = new ArrayList<>();
		for (Lang lang : langs) {
			langsDto.add(LangDto.from(lang));
		}
		return langsDto;
	}
	
	
	
	@RequestMapping(value = "/pages/{name}", method = RequestMethod.GET)
	public @ResponseBody PageDto getPage(@PathVariable(value = "name") String pageName) throws ServiceException {
		Page page = pageService.findByName(pageName);
		return PageDto.from(page);
	}

	@RequestMapping(value = "/templates", method = RequestMethod.GET)
	public @ResponseBody List<TemplateDto> getTemplates() throws ServiceException {
		List<Template> templates = templateService.findAllBlockAndPageBlock();
		List<TemplateDto> templatesDto = new ArrayList<>();
		for (Template template : templates) {
			templatesDto.add(TemplateDto.from(template));
		}
		return templatesDto;
	}
	
	@RequestMapping(value = "/templates/{name}", method = RequestMethod.GET)
	public @ResponseBody TemplateDto getTemplate(@PathVariable(value = "name") String templateName) throws ServiceException {
		Template template = templateService.findByName(templateName);
		return TemplateDto.from(template);
	}
	@RequestMapping(value = "/templates/{name}", method = RequestMethod.PUT)
	public @ResponseBody TemplateDto updateTemplate(@PathVariable(value = "name") String templateName,@Valid @RequestBody TemplateDto templateDto, BindingResult result) throws ServiceException, FormException {
		if (result.hasErrors()){
			List<String> errors = new ArrayList<>();
			for (ObjectError objectError : result.getAllErrors()) {
				errors.add(objectError.getDefaultMessage());
				System.out.println(objectError.getDefaultMessage());
			}
			throw new FormException("form errors", result.getAllErrors());			
		}
		return TemplateDto.from(templateService.save(TemplateDto.to(templateDto)));
	}

	
	
	
}