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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.controller.engine.AbtractController;
import fr.cedricsevestre.dto.engine.TranslationDto;
import fr.cedricsevestre.dto.engine.BlockDto;
import fr.cedricsevestre.dto.engine.FolderDto;
import fr.cedricsevestre.dto.engine.LangDto;
import fr.cedricsevestre.dto.engine.MapTemplateDto;
import fr.cedricsevestre.dto.engine.MapTemplateSimpleDto;
import fr.cedricsevestre.dto.engine.PageDto;
import fr.cedricsevestre.dto.engine.PositionDto;
import fr.cedricsevestre.dto.engine.TemplateDto;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.independant.objects.Position;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.entity.engine.translation.objects.Template.TemplateKind;
import fr.cedricsevestre.exception.FormException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.independant.objects.FolderService;
import fr.cedricsevestre.service.engine.independant.objects.MapTemplateService;
import fr.cedricsevestre.service.engine.independant.objects.PositionService;
import fr.cedricsevestre.service.engine.translation.LangService;
import fr.cedricsevestre.service.engine.translation.TObjectService;
import fr.cedricsevestre.service.engine.translation.TranslationService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;

@RestController
@Scope("prototype")
@RequestMapping(value = "/@back")
@Secured({ "ROLE_WEBMASTER", "ROLE_ADMIN" })
public class BackController extends AbtractController {
	
	@Autowired
	private LangService langService;
	
	@Autowired
	private FolderService folderService;
	
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
	
	@Deprecated
	@RequestMapping(value = "/templates/exist/", method = RequestMethod.GET)
	public @ResponseBody Boolean checkJSPExist(@ModelAttribute("context") String context, @ModelAttribute("type") String type, @ModelAttribute("path") String path, @ModelAttribute("name") String name) throws ServiceException {
		System.out.println("context = " + context);
		System.out.println("type = " + type);
		System.out.println("path = " + path);
		System.out.println("name = " + name);
		
		TemplateDto templateDto = new TemplateDto();
		templateDto.setKind(TemplateKind.valueOf(type));
		templateDto.setPath(path);
		templateDto.setName(name);
		Template template = TemplateDto.to(templateDto);
		return templateService.checkJSPExist(common.getWebInfFolder(), context, template);
	}

	@Deprecated
	@RequestMapping(value = "/positions", method = RequestMethod.GET)
	public @ResponseBody Map<String, PositionDto> getPositions() throws ServiceException {
		Iterable<Position> positions = positionService.findAll();
		Map<String, PositionDto> mapPositions = new HashMap<>();
		for (Position position : positions) {
			mapPositions.put(position.getName(), PositionDto.fromWithoutMapTemplate(position));
		}
		return mapPositions;
	}



	
	// RESTFull (ngResource)	


	
	@Deprecated
	@RequestMapping(value = "/templates/{name}", method = RequestMethod.GET)
	public @ResponseBody TemplateDto getTemplate(@PathVariable(value = "name") String templateName) throws ServiceException {
		Template template = templateService.findByName(templateName);
		return TemplateDto.from(template);
	}
	@Deprecated
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

	
	
	
	
	
	@RequestMapping(value = "/parsedblock/{pageId}/{blockId}/{activeObjectId}", method = RequestMethod.GET)
	public ModelAndView getParsedBlockWithActiveObject(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "pageId") Integer pageId, @PathVariable(value = "blockId") Integer blockId, @PathVariable(value = "activeObjectId") Integer activeObjectId, @RequestParam(value = "folderId", required = false) Integer folderId, Folder folder) throws ServiceException {
		return getParsedBlock(request, response, pageId, blockId, activeObjectId, folderId, folder);
	}
	@RequestMapping(value = "/parsedblock/{pageId}/{blockId}/", method = RequestMethod.GET)
	public ModelAndView getParsedBlockWithoutActiveObject(HttpServletRequest request, HttpServletResponse response, @PathVariable(value = "pageId") Integer pageId, @PathVariable(value = "blockId") Integer blockId, @RequestParam(value = "folderId", required = false) Integer folderId, Folder folder) throws ServiceException {
		return getParsedBlock(request, response, pageId, blockId, null, folderId, folder);
	}
	public ModelAndView getParsedBlock(HttpServletRequest request, HttpServletResponse response, Integer pageId, Integer blockId, Integer activeObjectId, Integer folderId, Folder folder) throws ServiceException {
		ModelAndView modelAndView = null;
		try {
			if (folderId != null) folder = folderService.findOne(folderId);
			Page page = pageService.findOne(pageId);	
			Template block = templateService.findOne(blockId);
			
			Translation activeObject = null;
			if (activeObjectId != null){
				activeObject = tObjectService.findOne(activeObjectId);
			}
			
			modelAndView = baseView(page, block, activeObject, folder);
			
			modelAndView.addObject("page", page);
			modelAndView.addObject("activeBlock", block);
			response.addHeader("Object-Type", "parsedBlock");  
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	
	
	
	

	@RequestMapping(value = "/tobject", method = RequestMethod.GET, params={"id"})
	public @ResponseBody TranslationDto getTObject(@RequestParam(value = "id", required = true) Integer id) throws ServiceException {
		Translation translation = tObjectService.findOne(id);
		return TranslationDto.from(translation);
	}
	
	
	@RequestMapping(value = "/folder", method = RequestMethod.GET)
	public @ResponseBody List<FolderDto> getFolders() throws ServiceException {
		List<Folder> folders = (List<Folder>) folderService.findAll();
		List<FolderDto> foldersDto = new ArrayList<>();
		for (Folder folder : folders) {
			foldersDto.add(FolderDto.from(folder));
		}
		return foldersDto;
	}
	@RequestMapping(value = "/folder", method = RequestMethod.GET, params={"id"})
	public @ResponseBody FolderDto getFolder(@RequestParam(value = "id", required = true) Integer id) throws ServiceException {
		Folder folder = folderService.findOne(id);
		return FolderDto.from(folder);
	}
	@RequestMapping(value = "/folder", method = RequestMethod.GET, params={"name"})
	public @ResponseBody FolderDto getFolderByName(@RequestParam(value = "name", required = true) String name) throws ServiceException {
		Folder folder = folderService.findByName(name);
		return FolderDto.from(folder);
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public @ResponseBody PageDto getPage(@RequestParam(value = "id", required = true) Integer id) throws ServiceException {
		Page page = pageService.findOne(id);
		return PageDto.from(page);
	}
	
	@RequestMapping(value = "/template", method = RequestMethod.GET)
	public @ResponseBody List<TemplateDto> getTemplates() throws ServiceException {
		List<Template> templates = templateService.findAllBlockAndPageBlock();
		List<TemplateDto> templatesDto = new ArrayList<>();
		for (Template template : templates) {
			templatesDto.add(TemplateDto.from(template));
		}
		return templatesDto;
	}
	@RequestMapping(value = "/template", method = RequestMethod.GET, params={"id"})
	public @ResponseBody TemplateDto getTemplate(@RequestParam(value = "id", required = true) Integer id) throws ServiceException {
		Template template = templateService.findOne(id);
		return TemplateDto.from(template);
	}
	
	
	@RequestMapping(value = "/lang", method = RequestMethod.GET)
	public @ResponseBody List<LangDto> getLangs() throws ServiceException {
		Iterable<Lang> langs = langService.findAll();
		List<LangDto> langsDto = new ArrayList<>();
		for (Lang lang : langs) {
			langsDto.add(LangDto.from(lang));
		}
		return langsDto;
	}
	@RequestMapping(value = "/lang", method = RequestMethod.GET, params={"id"})
	public @ResponseBody LangDto getLang(@RequestParam(value = "id", required = true) Integer id) throws ServiceException {
		Lang lang = langService.findOne(id);
		return LangDto.from(lang);
	}
	@RequestMapping(value = "/lang", method = RequestMethod.GET, params={"code"})
	public @ResponseBody LangDto getLangByCode(@RequestParam(value = "code", required = true) String code) throws ServiceException {
		Lang lang = langService.findByCode(code);
		return LangDto.from(lang);
	}

	
	//Model : Page ou PageBlock
	//ActiveObject : ActiveObject
	//Position : Position
	@RequestMapping(value = "/block", method = RequestMethod.GET, params={"modelId", "activeObjectId", "positionId"})
	public @ResponseBody List<BlockDto> getBlocksForPosition(
			@RequestParam(value = "modelId", required = true) Integer modelId, 
			@RequestParam(value = "activeObjectId", required = true) Integer activeObjectId, 
			@RequestParam(value = "positionId", required = true) Integer positionId) throws ServiceException {
		List<BlockDto> blockDtos = new ArrayList<>();
		
		List<Translation> models = new ArrayList<>();
		Template template = templateService.findOne(modelId);
		models.add(template);

		if (activeObjectId != null){
			models.add(tObjectService.findOne(activeObjectId));
		}

		Position pos = positionService.findOneForModelsWithMaps(models, positionId);

		if (pos != null){
			List<MapTemplate> mapTemplates;
			mapTemplates = pos.getMapTemplates();
			for (MapTemplate mapTemplate : mapTemplates) {
				blockDtos.add(BlockDto.from(mapTemplate));
			}
		}		
		return blockDtos;
	}
	
	@RequestMapping(value = "/mapTemplate", method = RequestMethod.POST)
	public @ResponseBody BlockDto saveMapTemplate(@Valid @RequestBody MapTemplateSimpleDto mapTemplateSimpleDto, BindingResult result) throws ServiceException, FormException {
		if (result.hasErrors()){
			List<String> errors = new ArrayList<>();
			for (ObjectError objectError : result.getAllErrors()) {
				errors.add(objectError.getDefaultMessage());
				System.out.println(objectError.getDefaultMessage());
			}
			throw new FormException("form errors", result.getAllErrors());			
		}

		MapTemplate mapTemplate = new MapTemplate();
		mapTemplate.setModel(templateService.findOne(mapTemplateSimpleDto.getModelId()));
		mapTemplate.setBlock(templateService.findOne(mapTemplateSimpleDto.getBlockId()));
		mapTemplate.setPosition(positionService.findOne(mapTemplateSimpleDto.getPositionId()));
		mapTemplate.setOrdered(mapTemplateSimpleDto.getOrdered());
		return BlockDto.from(mapTemplateService.saveAndOrder(mapTemplate));
	}
	@RequestMapping(value = "/mapTemplate", method = RequestMethod.DELETE, params={"id"})
	public BlockDto deleteMapTemplate(@RequestParam(value = "id", required = true) Integer id) throws ServiceException {
		MapTemplate mapTemplate = mapTemplateService.findOne(id);
		mapTemplateService.removeById(id);
		return BlockDto.from(mapTemplate);
	}
	
	
}