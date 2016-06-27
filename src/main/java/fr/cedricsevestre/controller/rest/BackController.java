package fr.cedricsevestre.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.dto.engine.TranslationDto;
import fr.cedricsevestre.dto.engine.BlockDto;
import fr.cedricsevestre.dto.engine.LangDto;
import fr.cedricsevestre.dto.engine.MapTemplateDto;
import fr.cedricsevestre.dto.engine.MapTemplateSimpleDto;
import fr.cedricsevestre.dto.engine.PageDto;
import fr.cedricsevestre.dto.engine.PositionDto;
import fr.cedricsevestre.dto.engine.TemplateDto;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.MapTemplate;
import fr.cedricsevestre.entity.engine.Page;
import fr.cedricsevestre.entity.engine.Position;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.entity.engine.Template.TemplateType;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.exception.FormException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.LangService;
import fr.cedricsevestre.service.engine.MapTemplateService;
import fr.cedricsevestre.service.engine.PageService;
import fr.cedricsevestre.service.engine.PositionService;
import fr.cedricsevestre.service.engine.TObjectService;
import fr.cedricsevestre.service.engine.TemplateService;
import fr.cedricsevestre.service.engine.TranslationService;

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
	
	@Autowired
	private TObjectService tObjectService;
	
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

	@RequestMapping(value = "models/{model}/activeobjects/{activeobject}/positions/{position}/blocks", method = RequestMethod.GET)
	public @ResponseBody List<BlockDto> getBlocksForPosition(
			@PathVariable(value = "model") String modelName, 
			@PathVariable(value = "activeobject") Integer activeObjectId, 
			@PathVariable(value = "position") String positionName) throws ServiceException {
		List<BlockDto> blockDtos = new ArrayList<>();
		
		List<Translation> models = new ArrayList<>();
		Template template = templateService.findByName(modelName);
		models.add(template);

		Translation activeObject = null;
		if (activeObjectId > 0){
			activeObject = tObjectService.findById(activeObjectId);
		}
		if (activeObject != null){
			models.add(activeObject);
		}
		Position pos = positionService.findByNameForModelsWithMaps(models, positionName);

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

			String pathContext = Common.BASE_PAGES_VIEWS_PATH + page.getContext();		
			if (!templateService.checkJSPExist(common.getWebInfFolder(), pathContext, block)){
				pathContext = Common.BASE_PAGES_COMMON_PATH;
			}
			
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
	
	@RequestMapping(value = "/tobjects/{id}", method = RequestMethod.GET)
	public @ResponseBody TranslationDto getTObject(@PathVariable(value = "id") Integer id) throws ServiceException {
		Translation translation = tObjectService.findById(id);
		return TranslationDto.from(translation);
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