package fr.cedricsevestre.conf;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.custom.Album.AlbumType;
import fr.cedricsevestre.entity.custom.Category;
import fr.cedricsevestre.entity.custom.Icon;
import fr.cedricsevestre.entity.custom.Job;
import fr.cedricsevestre.entity.custom.Experience;
import fr.cedricsevestre.entity.custom.Media;
import fr.cedricsevestre.entity.custom.Media.FileType;
import fr.cedricsevestre.entity.custom.Member;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.custom.Resume;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.independant.objects.NData;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema.ScopeType;
import fr.cedricsevestre.entity.engine.independant.objects.NType;
import fr.cedricsevestre.entity.engine.independant.objects.NType.ValueType;
import fr.cedricsevestre.entity.engine.independant.objects.Position;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Link;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.AlbumService;
import fr.cedricsevestre.service.custom.CategoryService;
import fr.cedricsevestre.service.custom.ExperienceService;
import fr.cedricsevestre.service.custom.IconService;
import fr.cedricsevestre.service.custom.MarkerService;
import fr.cedricsevestre.service.custom.MediaService;
import fr.cedricsevestre.service.custom.MemberService;
import fr.cedricsevestre.service.custom.ProjectService;
import fr.cedricsevestre.service.custom.ResumeService;
import fr.cedricsevestre.service.custom.TagService;
import fr.cedricsevestre.service.engine.bo.LinkService;
import fr.cedricsevestre.service.engine.independant.objects.FolderService;
import fr.cedricsevestre.service.engine.independant.objects.MapTemplateService;
import fr.cedricsevestre.service.engine.independant.objects.NDataService;
import fr.cedricsevestre.service.engine.independant.objects.NSchemaService;
import fr.cedricsevestre.service.engine.independant.objects.PositionService;
import fr.cedricsevestre.service.engine.independant.objects.StorageService;
import fr.cedricsevestre.service.engine.translation.LangService;
import fr.cedricsevestre.service.engine.translation.TObjectService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;


@Component
public class InitialisationBase {
	private Logger logger = Logger.getLogger(InitialisationBase.class);
	
	public InitialisationBase() {

	}
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private AlbumService albumService;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private StorageService fileService;
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private LinkService linkService;
	
	@Autowired
	private MarkerService markerService;
	
	@Autowired
	private LangService langService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private NSchemaService nSchemaService;
	
	@Autowired
	private NDataService nDataService;
	
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private MapTemplateService mapTemplateService;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private ResumeService resumeService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private IconService iconService;
	
	
	@Autowired
	private FolderService folderService;

	@Autowired
	private TObjectService tObjectService;
	

	@Autowired
	private ExperienceService experienceService;
	
	Map<String, Folder> mapfolders;
	
	public void run() throws ServiceException, InstantiationException, IllegalAccessException {
		logger.debug("init");
		initFolders();
		
		initLangs();
		initUsers();
		initNSchemas();
		initTemplates();
		
		initPages();
		initPositions();
		initBlocks();
		initPageBlocs();
		
		initProject();
		
		initMapTemplates();

		initAlbums();
		initTags();
		initMedias();
		initTags2();
		
		initMarkers();
		
		initNDatas();
		
		
		
		initBo();
		initCv();
	}

	private Lang langEN;
	private Lang langFR;
	private List<Lang> langs;
	public void initLangs() throws ServiceException{
		logger.debug("init langs");
		langs = new ArrayList<>();

		langEN = new Lang("en", "English");
		langs.add(langEN);
		langService.save(langEN);
		
		langFR = new Lang("fr", "Français");
		langs.add(langFR);
		langService.save(langFR);
		
	}
	
	
	public void initUsers() throws ServiceException{
		logger.debug("init users");
		Member member = new Member();
		member.setLogin("admin");
		ShaPasswordEncoder sha = new ShaPasswordEncoder();
		member.setEncryptPassword(sha.encodePassword("852963", null));
		member.setEnabled(true);
		member.setRole("ROLE_ADMIN");
		member.setFirstName("Super");
		member.setLastName("Boss");
		member.setMail("postmaster@cedric-sevestre.fr");
		memberService.save(member);

		member = new Member();
		member.setLogin("mrYapados");
		sha = new ShaPasswordEncoder();
		member.setEncryptPassword(sha.encodePassword("852963", null));
		member.setEnabled(true);
		member.setRole("ROLE_USER");
		member.setFirstName("Cédric");
		member.setLastName("Sevestre");
		member.setMail("info@cedric-sevestre.fr");
		memberService.save(member);
		
	}

	public void initTemplates() throws ServiceException{
		logger.debug("init templates");
				
		Template homeEN = templateService.translate(new Template(), langEN);
		homeEN.setName("home");
		homeEN.setDescription("home description en");
		homeEN.setMetaTitle("home");
		homeEN.setMetaDescription("MetaDescription");
		homeEN.setPath("home/home");
		homeEN.setKind(Template.TemplateKind.PAGE);
		templateService.save(homeEN);
		
		Template homeFr = templateService.translate(homeEN, langFR);
		homeFr.setName("home");
		homeFr.setDescription("home description fr");
		templateService.save(homeFr);

		
		
		Template homeProjectEN = templateService.translate(new Template(), langEN);
		homeProjectEN.setName("homeProject");
		homeProjectEN.setDescription("homeProject description en");
		homeProjectEN.setMetaTitle("{0}");
		homeProjectEN.setMetaDescription("MetaDescription");
		homeProjectEN.setPath("home/homeProject");
		homeProjectEN.setKind(Template.TemplateKind.PAGE);
		templateService.save(homeProjectEN);
		
		Template homeProjectFr = templateService.translate(homeProjectEN, langFR);
		homeProjectFr.setName("homeProject");
		homeProjectFr.setDescription("homeProject description fr");
		templateService.save(homeProjectFr);

		
		
		Template homeArticleEN = templateService.translate(new Template(), langEN);
		homeArticleEN.setName("homeArticle");
		homeArticleEN.setDescription("homeArticle description en");
		homeArticleEN.setMetaTitle("{0}");
		homeArticleEN.setMetaDescription("MetaDescription");
		homeArticleEN.setPath("home/homeArticle");
		homeArticleEN.setKind(Template.TemplateKind.PAGE);
		templateService.save(homeArticleEN);
		
		Template homeArticleFr = templateService.translate(homeArticleEN, langFR);
		homeArticleFr.setName("homeArticle");
		homeArticleFr.setDescription(" description fr");
		templateService.save(homeArticleFr);
		
		
		
//		String name = "";
//		
//		name = "@bo_list";
//		Template bo_listEN = templateService.translate(new Template(), langEN);
//		bo_listEN.setName(name + "");
//		bo_listEN.setDescription(name + " description en");
//		bo_listEN.setMetaTitle("{0}");
//		bo_listEN.setMetaDescription("MetaDescription");
//		bo_listEN.setPath("home/home");
//		bo_listEN.setType(Template.TemplateType.PAGE);
//		templateService.save(bo_listEN);
//		
//		Template bo_listFR = templateService.translate(bo_listEN, langFR);
//		bo_listFR.setName(name + "");
//		bo_listFR.setDescription(name + " description fr");
//		templateService.save(bo_listFR);
		
		
		
		
		
		
		

	}

	public void initFolders() throws ServiceException{
		logger.debug("init initFolders");
		
		mapfolders = new HashMap<>();

		List<String> serverNames = null;
		Folder folder = null;
		
		folder = new Folder();
		folder.setName("front");
		serverNames = new ArrayList<>();
		serverNames.add("front");
		folder.setServerName(serverNames);
		folder.setPath("pages/");
		folderService.save(folder);
		mapfolders.put(folder.getName(), folder);
	}
	
	
	
	public void initPages() throws ServiceException{
		logger.debug("init templates");		
		List<Folder> folders = new ArrayList<>();
		folders.add(mapfolders.get("front"));
		
		Page homeEN = pageService.translate(new Page(), langEN);
		homeEN.setName("home");
		homeEN.setContext("home");
		homeEN.setDescription("home description en");
		homeEN.setModel(templateService.identifyWithAllExceptData(null, "home", langEN));
		homeEN.setFolders(folders);
		pageService.save(homeEN);
		
		Page homeFR = pageService.translate(homeEN, langFR);
		homeFR.setName("home");
		homeFR.setDescription("home description fr");
		homeEN.setFolders(folders);
		pageService.save(homeFR);
		
		
		Page projectEN = pageService.translate(new Page(), langEN);
		projectEN.setName("project");
		projectEN.setContext("project");
		projectEN.setDescription("project description en");
		projectEN.setModel(templateService.identifyWithAllExceptData(null, "homeProject", langEN));
		pageService.save(projectEN);
		
		Page projectFr = pageService.translate(projectEN, langFR);
		projectFr.setName("project");
		projectFr.setDescription("project description fr");
		pageService.save(projectFr);
		
		
		Page articleEN = pageService.translate(new Page(), langEN);
		articleEN.setName("article");
		articleEN.setContext("article");
		articleEN.setDescription("article description en");
		articleEN.setModel(templateService.identifyWithAllExceptData(null, "homeArticle", langEN));
		pageService.save(articleEN);
		
		Page articleFr = pageService.translate(articleEN, langFR);
		articleFr.setName("article");
		articleFr.setDescription("article description fr");
		pageService.save(articleFr);
		
		

//		String name = "";
//		
//		name = "@bo_list";
//		Page bo_listEN = pageService.translate(new Page(), langEN);
//		bo_listEN.setName(name + "");
//		bo_listEN.setContext("list");
//		bo_listEN.setDescription(name + " description en");
//		bo_listEN.setModel(templateService.findByNameWithAllExceptData(name + ""));
//		pageService.save(bo_listEN);
//		
//		Page bo_listFR = pageService.translate(bo_listEN, langFR);
//		bo_listFR.setName("article");
//		bo_listFR.setDescription("article description fr");
//		pageService.save(bo_listFR);

		
		
		
		
		
		
	}

	public List<Position> initPositions() throws ServiceException{
		logger.debug("init positions");
		
		ArrayList<Position> positions = new ArrayList<>();
		Position position = new Position();
		
		position.setName("header");
		positionService.save(position);
		positions.add(position);
		
		position = new Position();
		position.setName("nav");
		positionService.save(position);
		positions.add(position);
		
		position = new Position();
		position.setName("aside");
		positionService.save(position);
		positions.add(position);
		
		position = new Position();
		position.setName("article");
		positionService.save(position);
		positions.add(position);
		
		position = new Position();
		position.setName("footer");
		positionService.save(position);
		positions.add(position);
		
		position = new Position();
		position.setName("map");
		positionService.save(position);
		positions.add(position);
		
		
		
		
		position = new Position();
		position.setName("title");
		positionService.save(position);
		positions.add(position);
		
		position = new Position();
		position.setName("content");
		positionService.save(position);
		positions.add(position);
		
		
		
		
		
		return positions;
	}
	public void initBlocks() throws ServiceException{
		logger.debug("init blocks");
		String name = "";
		
		Template templateEN = templateService.translate(new Template(), langEN);
		name = "headerProject";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " description en");
		templateEN.setPath("header/headerProject");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		
		
		
		
		Template templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " description fr");
		templateService.save(templateFR);
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "headerProjectH2";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " description en");
		templateEN.setPath("header/headerProjectH2");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " description fr");
		templateService.save(templateFR);
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "socialNetwork";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " Twitter, Facebook, Google+, ... en");
		templateEN.setPath("socialnetwork/socialNetwork");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " Twitter, Facebook, Google+, ... fr");
		templateService.save(templateFR);
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "advertisement";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " advertisement description en");
		templateEN.setPath("advertisement/advertisement");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " advertisement description fr");
		templateService.save(templateFR);
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "articleTitle";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " articleTitle description en");
		templateEN.setPath("article/title/articleTitle");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " articleTitle description fr");
		templateService.save(templateFR);
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "articleContent";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " advertisement articleContent en");
		templateEN.setPath("article/content/articleContent");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " articleContent description fr");
		templateService.save(templateFR);
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "album";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " album articleContent en");
		templateEN.setPath("nav/album/album");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " album description fr");
		templateService.save(templateFR);		
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "blockTest1";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " blockTest1 articleContent en");
		templateEN.setPath("test/blockTest1");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " blockTest1 description fr");
		templateService.save(templateFR);		
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "blockTest2";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " blockTest2 articleContent en");
		templateEN.setPath("test/blockTest2");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " blockTest2 description fr");
		templateService.save(templateFR);	
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "blockTest3";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " blockTest3 articleContent en");
		templateEN.setPath("test/subtest/blockTest3");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " blockTest3 description fr");
		templateService.save(templateFR);	
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "map";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " description en");
		templateEN.setPath("map/map");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " description fr");
		templateService.save(templateFR);
		
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "horizontal";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " description en");
		templateEN.setPath("nav/horizontal/horizontal");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " description fr");
		templateService.save(templateFR);
		
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "vectormapfra";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " description en");
		templateEN.setPath("vectormap/fra/vectorMapFra");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " description fr");
		templateService.save(templateFR);
		
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "testcache";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " description en");
		templateEN.setPath("tests/cache/cache");
		templateEN.setKind(Template.TemplateKind.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " description fr");
		templateService.save(templateFR);
		
		

		
		
	}

	public void initPageBlocs() throws ServiceException{
		logger.debug("init Page blocs");
		Template templateEN = null;
		Template templateFR = null;
		String name = null;
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "article";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " Page Block article description en");
		templateEN.setPath("article/article");
		templateEN.setKind(Template.TemplateKind.PAGEBLOCK);
		templateEN.setSchema(nSchemaService.findOne(1));
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " Page Block article description fr");
		templateService.save(templateFR);	
		

		templateEN = templateService.translate(new Template(), langEN);
		name = "article2";
		templateEN.setName(name + "");
		templateEN.setDescription(name + " Page Block article2 description en");
		templateEN.setPath("article/article2");
		templateEN.setKind(Template.TemplateKind.PAGEBLOCK);
		templateEN.setSchema(nSchemaService.findOne(1));
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "");
		templateFR.setDescription(name + " Page Block article2 description fr");
		templateService.save(templateFR);	
		
//		Template template = new Template();
//		template.setDateAdd(new Date());
//		template.setName("article");
//		template.setDescription("Page Block article");
//		template.setPath("article/article");
//		template.setType(Template.TemplateType.PAGEBLOCK);
//		
//		template.setSchema(nSchemaService.findById(1));
//		templateService.save(template);
//		
//		template = new Template();
//		template.setDateAdd(new Date());
//		template.setName("article2");
//		template.setDescription("Page Block article 2");
//		template.setPath("article/article2");
//		template.setType(Template.TemplateType.PAGEBLOCK);
//		templateService.save(template);
	}
	
	public MapTemplate addMapTemplate(Translation model, String block, String position, Integer ordered) throws ServiceException{
		MapTemplate mapTemplate = new MapTemplate();
		mapTemplate.setModel(model);
		mapTemplate.setBlock(templateService.identify(null, block, model.getLang()));

		mapTemplate.setPosition(positionService.findByName(position));
		mapTemplate.setOrdered(ordered);
		mapTemplateService.save(mapTemplate);
		return mapTemplate;
	}
	

	
	public List<MapTemplate> initMapTemplates() throws ServiceException{
		logger.debug("init maps");
		
		ArrayList<MapTemplate> mapTemplates = new ArrayList<>();
		

		
		Translation homeProject_EN = templateService.identify(null, "homeProject", langEN);
		Translation article_EN = templateService.identify(null, "article", langEN);
		Translation article2_EN = templateService.identify(null, "article2", langEN);
		Translation testproject_EN = projectService.identify(mapfolders.get("front"), "testproject", langEN);
		Translation testproject_FR = projectService.identify(mapfolders.get("front"), "testproject", langFR);
		Translation home_EN = templateService.identify(null, "home", langEN);
		
		
		mapTemplates.add(addMapTemplate(homeProject_EN,"headerProject", "header", 10));
		mapTemplates.add(addMapTemplate(homeProject_EN,"socialNetwork", "aside", 10));
		mapTemplates.add(addMapTemplate(homeProject_EN,"album", "nav", 10));
		mapTemplates.add(addMapTemplate(homeProject_EN,"blockTest1", "nav", 20));
		mapTemplates.add(addMapTemplate(homeProject_EN,"blockTest2", "nav", 30));
//		mapTemplates.add(addMapTemplate(model,"articleContent", "article", 10));
//		mapTemplates.add(addMapTemplate(model,"articleTitle", "article", 10));
		
		mapTemplates.add(addMapTemplate(article_EN,"articleTitle", "title", 10));
		mapTemplates.add(addMapTemplate(article_EN,"articleContent", "content", 20));
		mapTemplates.add(addMapTemplate(homeProject_EN,"article", "article", 10));
		
		mapTemplates.add(addMapTemplate(article2_EN,"socialNetwork", "title", 10));
		mapTemplates.add(addMapTemplate(homeProject_EN,"article2", "article", 10));
		
		
		mapTemplates.add(addMapTemplate(testproject_EN,"map", "article", 10));
		mapTemplates.add(addMapTemplate(testproject_FR,"vectormapfra", "article", 10));
		
		
		mapTemplates.add(addMapTemplate(home_EN,"blockTest3", "article", 10));
		mapTemplates.add(addMapTemplate(home_EN,"socialNetwork", "article", 20));
		mapTemplates.add(addMapTemplate(home_EN,"testcache", "article", 10));
		
		
		
		
		return mapTemplates;
	}
	
	public void initNSchemas() throws ServiceException{
		logger.debug("init nShemas");
		
		NSchema nSchema = new NSchema();
		Map<String, NType> columns = new HashMap<>();
		columns.put("title", new NType(NType.ValueType.VARCHAR255));
		columns.put("content", new NType(NType.ValueType.HTML));
		columns.put("numbers", new NType(NType.ValueType.COLLECTION, NType.ValueType.INTEGER));
		
		columns.put("album", new NType(NType.ValueType.TOBJECT));
		columns.put("albums", new NType(NType.ValueType.COLLECTION, NType.ValueType.TOBJECT));
		
		nSchema.setColumns(columns);
		nSchema.setScope(NSchema.ScopeType.ALL);
		nSchemaService.save(nSchema);
	}

	
	
	// FRONT DATAS
	public void initProject() throws ServiceException, InstantiationException, IllegalAccessException{
		logger.debug("init project");
		List<Folder> folders = new ArrayList<>();
		folders.add(mapfolders.get("front"));
		
		String name = "";
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 1);
		
		Project projectEN =   projectService.translate(new Project(), langEN);
		name = "testproject";
		projectEN.setDateAdded(c.getTime());
		projectEN.setDateUpdated(c.getTime());
		projectEN.setName(name + "");
		projectEN.setDescription(name + " description en");
		projectEN.setFolders(folders);
		projectService.save(projectEN);
		
		Project projectFR = projectService.translate(projectEN, langFR);
		projectFR.setName(name + "");
		projectFR.setDateAdded(c.getTime());
		projectFR.setDateUpdated(c.getTime());
		projectFR.setDescription(name + " description fr");
		projectFR.setFolders(folders);
		projectService.save(projectFR);
	
		Integer max = 100;
		for (int i = 0; i < max; i++) {
			projectEN =   projectService.translate(new Project(), langEN);
			name = "generatedProject_" + i;
			projectEN.setName(name + "");
			projectEN.setDescription(name + " description en");
			projectService.save(projectEN);
			
			projectFR = projectService.translate(projectEN, langFR);
			projectFR.setName(name + "");
			projectFR.setDescription(name + " description fr");
			projectService.save(projectFR);
		}
		
	
	}
	
	
	public void initAlbums() throws ServiceException, InstantiationException, IllegalAccessException{
		logger.debug("init albums");
		String name = "";


		
		
		Album albumNoProjectEN = albumService.translate(new Album(), langEN);
		name = "testAlbumNoProject";
		albumNoProjectEN.setName(name + "");
		albumNoProjectEN.setDescription(name + " description en");
		albumNoProjectEN.setAlbumType(AlbumType.DEFAULT);
		albumService.save(albumNoProjectEN);
		
		Album albumNoProjectFR = albumService.translate(albumNoProjectEN, langFR);
		albumNoProjectFR.setName(name + "");
		albumNoProjectFR.setDescription(name + " description fr");
		albumNoProjectFR.setAlbumType(AlbumType.DEFAULT);
		albumService.save(albumNoProjectFR);
		
		
		
		Project ProjectEN = projectService.identify(mapfolders.get("front"), "testproject", langEN);
		Project ProjectFR = projectService.identify(mapfolders.get("front"), "testproject", langFR);
				
		Album albumEN = albumService.translate(new Album(), langEN);
		name = "testAlbum";
		albumEN.setName(name + "");
		albumEN.setDescription(name + " description en");
		albumEN.setProject(ProjectEN);
		albumEN.setAlbumType(AlbumType.DEFAULT);
		albumService.save(albumEN);
		
		Album albumFR = albumService.translate(albumEN, langFR);
		albumFR.setName(name + "");
		albumFR.setDescription(name + " description fr");
		albumFR.setProject(ProjectFR);
		albumFR.setAlbumType(AlbumType.DEFAULT);
		albumService.save(albumFR);
		
		Integer max = 20;
		for (int i = 0; i < max; i++) {
			albumEN =   albumService.translate(new Album(), langEN);
			name = "generatedAlbum_" + i;
			albumEN.setName(name + "");
			albumEN.setDescription(name + " description en");
			albumEN.setProject(ProjectEN);
			albumEN.setAlbumType(AlbumType.DEFAULT);
			albumService.save(albumEN);
			
			albumFR = albumService.translate(albumEN, langFR);
			albumFR.setName(name + "");
			albumFR.setDescription(name + " description fr");
			albumFR.setProject(ProjectFR);
			albumFR.setAlbumType(AlbumType.DEFAULT);
			albumService.save(albumFR);
		}
		
	}
	
	public void initTags() throws ServiceException, InstantiationException, IllegalAccessException{
		logger.debug("init tags");
		
		Tag tag = new Tag();
		tag.setName("testtag");
		tag.setDescription("description tag");
		tagService.save(tag);
			
		tag = new Tag();
		tag.setName("testtag2");
		tag.setDescription("description tag2");
		tagService.save(tag);

		Integer max = 30;
		for (int i = 0; i < max; i++) {
			tag = new Tag();
			tag.setName("testTag_" + i);
			tag.setDescription("description tag" + i);
			tagService.save(tag);
		}
		
			
	}
	
	public void initMedias() throws ServiceException, InstantiationException, IllegalAccessException{
		logger.debug("init files");
		
		
		
//		File file = new File();
//		file.setName("test1");
//		file.setFileSize(1024L);
//		file.setFileName("test1.test");
//		file.setPath("/");
//		fileService.save(file);
//		
//		file = new File();
//		file.setName("test2");
//		file.setFileSize(1024L);
//		file.setFileName("test2.test");
//		file.setPath("/");
//		fileService.save(file);
//		
//		file = new File();
//		file.setName("test3");
//		file.setFileSize(1024L);
//		file.setFileName("test3.test");
//		file.setPath("/test3");
//		fileService.save(file);
//		
//		file = new File();
//		file.setName("test4");
//		file.setFileSize(1024L);
//		file.setFileName("test4.test");
//		file.setPath("/test3");
//		fileService.save(file);
//		
//		file = new File();
//		file.setName("test5");
//		file.setFileSize(1024L);
//		file.setFileName("test5.test");
//		file.setPath("/test3");
//		fileService.save(file);
//		
//		file = new File();
//		file.setName("test6");
//		file.setFileSize(1024L);
//		file.setFileName("test6.test");
//		file.setPath("/test3/test5");
//		fileService.save(file);
//		
//		file = new File();
//		file.setName("test7");
//		file.setFileSize(1024L);
//		file.setFileName("test7.test");
//		file.setPath("/test3/test5");
//		fileService.save(file);	
//		
//		file = new File();
//		file.setName("test8");
//		file.setFileSize(1024L);
//		file.setFileName("test8.test");
//		file.setPath("/test3/test5");
//		fileService.save(file);
//		
//		file = new File();
//		file.setName("sam");
//		file.setFileSize(1024L);
//		file.setFileName("sam.png");
//		file.setPath("/media");
//		fileService.save(file);
		
		Album AlbumEN = albumService.identify(null, "testalbum", langEN);
		Album AlbumFR = albumService.identify(null, "testalbum", langFR);
		
		Media media = new Media();
		media.setName("testfile");
		media.setDescription("description file");
		media.setFileType(FileType.IMAGE);
		media.setAlbum(AlbumEN);
		
//		file = new File();
//		file.setName("file1");
//		file.setFileSize(1024L);
//		file.setFileName("cerfa.pdf");
//		file.setPath("/media");
//		fileService.save(file);
//		media.setFile(file);
		
		media.setFile("/media/cerfa.pdf");
		
		Set<String> files = new HashSet<>();
		files.add("/media/c,erfa.pdf");
		files.add("/media/cerfa2.pdf");
		media.setFiles(files);
		mediaService.save(media);
		
		
		Tag tag = tagService.findByName("testtag");
		Tag tag2 = tagService.findByName("testtag2");
		Media media2 = new Media();
		media2.setName("testfile2");
		media2.setDescription("description file2");
		media2.setFileType(FileType.VIDEO);
		media2.setAlbum(AlbumEN);
		
//		File file2 = new File();
//		file2.setName("file2");
//		file2.setFileSize(1024L);
//		file2.setFileName("cerfa2.pdf");
//		file2.setPath("/media");
//		media2.setFile(file2);
//		fileService.save(file2);
		
		Set<Tag> tags = new HashSet<>();
		tags.add(tag);
		//tags.add(tag2);
		
		media2.setTags(tags);
		mediaService.save(media2);
		
		
		
		
						
	}
	
	
	
	
	public void initTags2() throws ServiceException, InstantiationException, IllegalAccessException{
		logger.debug("init tags2");
		
		Media file = mediaService.identify(null, "testfile", langEN);
	
		
		Tag tag = new Tag();
		tag.setName("testtagWithFile");
		tag.setDescription("description tag");
		
		List<Media> files = new ArrayList<>();
		files.add(file);
		
		tag.setFiles(files);
		tagService.save(tag);
			

		
			
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public void initMarkers() throws ServiceException, InstantiationException, IllegalAccessException{
		logger.debug("init markers");
		
//		Marker marker = new Marker();
//		marker.setName("testmarker");
//		marker.setDescription("description marker");
//		markerService.save(marker);
//			
//		marker = new Marker();
//		marker.setName("testmarker2");
//		marker.setDescription("description marker2");
//		markerService.save(marker);
			
	}
	public void initNDatas() throws ServiceException{
		logger.debug("init nDatas");
		
		Template template = templateService.identify(null, "article", langEN);
		
		NData nData = new NData();
		nData.setvVarchar255("data article title");
		nData.setVarType(ValueType.VARCHAR255);
		nData.setPropertyName("title");
		nData.setTemplate(template);
		nDataService.save(nData);
		
		nData = new NData();
		nData.setvInteger(8);
		nData.setVarType(ValueType.INTEGER);
		nData.setPropertyName("number");
		nData.setTemplate(template);
		nDataService.save(nData);
		
		NData nDataCollection = new NData();
		nDataCollection.setvCollection(true);
		nDataCollection.setVarType(ValueType.COLLECTION);
		nDataCollection.setPropertyName("numbers");
		nDataCollection.setTemplate(template);
		nDataService.save(nDataCollection);
		
		NData nDataCollectionItem = new NData();
		nDataCollectionItem.setvInteger(3);
		nDataCollectionItem.setVarType(ValueType.INTEGER);
		nDataCollectionItem.setOrdered(5);
		nDataCollectionItem.setData(nDataCollection);
		//nDataCollectionItem.setTemplate(template);
		nDataService.save(nDataCollectionItem);
		
		nDataCollectionItem = new NData();
		nDataCollectionItem.setvInteger(5);
		nDataCollectionItem.setVarType(ValueType.INTEGER);
		nDataCollectionItem.setOrdered(2);
		nDataCollectionItem.setData(nDataCollection);
		//nDataCollectionItem.setTemplate(template);
		nDataService.save(nDataCollectionItem);
		
		
		
		nData = new NData();
		
		Album testalbum_EN = albumService.identify(null, "testalbum", langEN);
		
		nData.setvTObject(testalbum_EN);
		nData.setVarType(ValueType.TOBJECT);
		nData.setPropertyName("album");
		nData.setTemplate(template);
		nDataService.save(nData);
		
		
		
		nDataCollection = new NData();
		nDataCollection.setvCollection(true);
		nDataCollection.setVarType(ValueType.COLLECTION);
		nDataCollection.setPropertyName("albums");
		nDataCollection.setTemplate(template);
		nDataService.save(nDataCollection);
		
		nDataCollectionItem = new NData();
		nDataCollectionItem.setvTObject(testalbum_EN);
		nDataCollectionItem.setVarType(ValueType.TOBJECT);
		nDataCollectionItem.setOrdered(5);
		nDataCollectionItem.setData(nDataCollection);
		nDataService.save(nDataCollectionItem);
		
		nDataCollectionItem = new NData();
		nDataCollectionItem.setvTObject(testalbum_EN);
		nDataCollectionItem.setVarType(ValueType.TOBJECT);
		nDataCollectionItem.setOrdered(2);
		nDataCollectionItem.setData(nDataCollection);
		nDataService.save(nDataCollectionItem);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	public Position mkPosition(String name) throws ServiceException{
		Position position = new Position();
		position.setName(name);
		positionService.save(position);
		return position;
	}
	public Position addPosition(Map<String, Position> mapPosition, String name) throws ServiceException {
		Position position = mkPosition(name);
		mapPosition.put(name, position);
		return position;
	}
	
	public MapTemplate addMapTemplate(Translation model, Translation block, Position position, Integer ordered) throws ServiceException{
		MapTemplate mapTemplate = new MapTemplate();
		mapTemplate.setModel(model);
		mapTemplate.setBlock((Template) block);
		mapTemplate.setPosition(position);
		mapTemplate.setOrdered(ordered);
		mapTemplateService.save(mapTemplate);
		return mapTemplate;
	}
	public Map<Lang, MapTemplate> addMapTemplate(Map<Lang, Translation> models, Map<Lang, Translation> blocks, Position position) throws ServiceException{
		Map<Lang, MapTemplate> mapTemplates = new HashMap<>();
		for (Lang lang : langs) {
			mapTemplates.put(lang, addMapTemplate(models.get(lang), blocks.get(lang), position, orderBlock(position)));
		}
		return mapTemplates;
	}
	
	private Map<Position, Integer> countPosition;
	public Integer orderBlock(Position position){
		if (countPosition == null) countPosition = new HashMap<>();
		Integer count;
		if (!countPosition.containsKey(position)){
			countPosition.put(position, 0);
		}
		count = countPosition.get(position) + 10;
		countPosition.put(position, count);
		return count;
	}
	
	

	public Map<Lang, Translation> mkPage(Page base, Folder folder, String name, String context, Map<Lang, Translation> models) throws ServiceException{
		List<Folder> folders = new ArrayList<>();
		folders.add(folder);
		return mkPage(base, folders, name, context, models);
	}
	public Map<Lang, Translation> mkPage(Page base, List<Folder> folders, String name, String context, Map<Lang, Translation> models) throws ServiceException{
		Map<Lang, Translation> map = new HashMap<>();
		Page first = null;
		for (Lang lang : langs) {
			Page page = null;
			if (first == null){
				page = pageService.translate(base, lang);
				page.setName(name);
				page.setDescription(name + " Page description " + lang.getCode());
				page.setContext(context);
				page.setModel((Template) models.get(lang));
				page.setFolders(folders);
				first = page;
			} else {
				page = pageService.translate(first, lang);
				page.setFolders(folders);
				page.setName(name);
				page.setDescription(name + " Page description " + lang.getCode());
			}
			
			if (base instanceof Category){
				Category cat = (Category) page;
				cat.setMenuColor(((Category) base).getMenuColor());
				cat.setTitle(((Category) base).getTitle());
				cat.setIcon(((Category) base).getIcon());
				cat.setOrdered(((Category) base).getOrdered());
				cat.setInMenu(((Category) base).getInMenu());
				cat.setDescription(((Category) base).getDescription());
				//page = cat;
			} else {
				
			}
			
			pageService.save(page);
			map.put(lang, page);
		}
		return map;
	}

	
	public Map<Lang, Translation> mkModel(Folder folder, String name) throws ServiceException{
		List<Folder> folders = new ArrayList<>();
		folders.add(folder);
		return mkModel(folders, name);
	}
	public Map<Lang, Translation> mkModel(List<Folder> folders, String name) throws ServiceException{
		return mkModel(folders, name, name + "/" + name);
	}
	public Map<Lang, Translation> mkModel(Folder folder, String name, String path) throws ServiceException{
		List<Folder> folders = new ArrayList<>();
		folders.add(folder);
		return mkModel(folders, name, path);
	}
	public Map<Lang, Translation> mkModel(List<Folder> folders, String name, String path) throws ServiceException{
		Map<Lang, Translation> map = new HashMap<>();
		Template first = null;
		for (Lang lang : langs) {
			Template template = null;
			if (first == null){
				template = templateService.translate(new Template(), lang);
				template.setName(name);
				template.setDescription(name + " Model description " + lang.getCode());
				template.setMetaTitle("{0}");
				template.setMetaDescription("MetaDescription");
				template.setPath(path);
				template.setKind(Template.TemplateKind.PAGE);
				template.setFolders(folders);
				templateService.save(template);
				first = template;
			} else {
				template = templateService.translate(first, lang);
				template.setFolders(folders);
				template.setName(name);
				template.setDescription(name + " Model description " + lang.getCode());
				templateService.save(template);
			}
			map.put(lang, template);
		}
		return map;

	}
	
	


	
	
	public Map<Lang, Translation> mkPageBlock(Folder folder, String name) throws ServiceException{
		List<Folder> folders = new ArrayList<>();
		folders.add(folder);
		return mkPageBlock(folders, name);
	}
	public Map<Lang, Translation> mkPageBlock(Folder folder, String name, String path) throws ServiceException{
		List<Folder> folders = new ArrayList<>();
		folders.add(folder);
		return mkPageBlock(folders, name, path);
	}
	public Map<Lang, Translation> mkPageBlock(Folder folder, String name, String path, NSchema nSchema) throws ServiceException{
		List<Folder> folders = new ArrayList<>();
		folders.add(folder);
		return mkPageBlock(folders, name, path, nSchema);
	}
	public Map<Lang, Translation> mkPageBlock(List<Folder> folders, String name) throws ServiceException{
		return mkPageBlock(folders, name, name + "/" + name, null);
	}
	public Map<Lang, Translation> mkPageBlock(List<Folder> folders, String name, String path) throws ServiceException{
		return mkPageBlock(folders, name, path, null);
	}
	public Map<Lang, Translation> mkPageBlock(List<Folder> folders, String name, String path, NSchema nSchema) throws ServiceException{
		Map<Lang, Translation> map = new HashMap<>();
		Template first = null;
		for (Lang lang : langs) {
			Template template = null;
			if (first == null){
				template = templateService.translate(new Template(), lang);
				template.setName(name);
				template.setDescription(name + " PageBlock description " + lang.getCode());
				template.setPath(path);
				template.setKind(Template.TemplateKind.PAGEBLOCK);
				template.setSchema(nSchema);
				template.setFolders(folders);
				templateService.save(template);
				first = template;
			} else {
				template = templateService.translate(first, lang);
				template.setFolders(folders);
				template.setName(name);
				template.setDescription(name + " PageBlock description " + lang.getCode());
				templateService.save(template);
			}
			map.put(lang, template);
		}
		return map;
	}
	
	
	public Map<Lang, Translation> mkBlock(Folder folder, String name) throws ServiceException{
		List<Folder> folders = new ArrayList<>();
		folders.add(folder);
		return mkBlock(folders, name);
	}
	public Map<Lang, Translation> mkBlock(Folder folder, String name, String path) throws ServiceException{
		List<Folder> folders = new ArrayList<>();
		folders.add(folder);
		return mkBlock(folders, name, path);
	}
	public Map<Lang, Translation> mkBlock(Folder folder, String name, String path, NSchema nSchema) throws ServiceException{
		List<Folder> folders = new ArrayList<>();
		folders.add(folder);
		return mkBlock(folders, name, path, nSchema);
	}
	public Map<Lang, Translation> mkBlock(List<Folder> folders, String name) throws ServiceException{
		return mkBlock(folders, name, name + "/" + name, null);
	}
	public Map<Lang, Translation> mkBlock(List<Folder> folders, String name, String path) throws ServiceException{
		return mkBlock(folders, name, path, null);
	}
	public Map<Lang, Translation> mkBlock(List<Folder> folders, String name, String path, NSchema nSchema) throws ServiceException{
		Map<Lang, Translation> map = new HashMap<>();
		Template first = null;
		for (Lang lang : langs) {
			Template template = null;
			if (first == null){
				template = templateService.translate(new Template(), lang);
				template.setName(name);
				template.setDescription(name + " Block description " + lang.getCode());
				template.setPath(path);
				template.setKind(Template.TemplateKind.BLOCK);
				template.setSchema(nSchema);
				template.setFolders(folders);
				first = template;
			} else {
				template = templateService.translate(first, lang);
				template.setFolders(folders);
				template.setName(name);
				template.setDescription(name + " Block description " + lang.getCode());
				
			}
			template.setController(name);
			templateService.save(template);
			map.put(lang, template);
		}
		return map;
	}

	
	public Icon mkIcon(Icon icon) throws ServiceException{
		return iconService.save(icon);
	}
	public Resume mkResume(Resume resume) throws ServiceException{
		return resumeService.save(resume);
	}
	
	public NSchema mkNSchema(Map<String, NType> columns, NSchema.ScopeType scopeType) throws ServiceException{
		NSchema nSchema = new NSchema();
		nSchema.setColumns(columns);
		nSchema.setScope(scopeType);
		nSchemaService.save(nSchema);
		return nSchema;
	}
	
	public Link addLink(Link master, Lang lang, String name, String title, String description, String url, String picto) throws ServiceException{
			Link link = linkService.translate(master, lang);
			link.setName(name);
			link.setDescription(description);
			link.setTitle(title);
			link.setUrl(url);
			link.setPicto(picto);
			link.setFolders(new ArrayList<>());
			linkService.save(link);
			return link;
	}
	
	public void generateMenu(Folder folder, Map<Lang, Translation> models, Position position) throws ServiceException{
		List<Folder> folders = new ArrayList<>();
		folders.add(folder);
		generateMenu(folders, models, position);
	}
	public void generateMenu(List<Folder> folders, Map<Lang, Translation> models, Position position) throws ServiceException{
		Map<String, NType> columns = new HashMap<>();
		columns.put("title", new NType(NType.ValueType.VARCHAR50));
		columns.put("links", new NType(NType.ValueType.COLLECTION, NType.ValueType.TOBJECT));
		Map<Lang, Translation> bMenuFree = mkBlock(folders, "@bo_block_menu_free", "header/menu/headerFree", mkNSchema(columns, ScopeType.ONE));
		
		Map<Lang, Translation> bMenuObjects = mkBlock(folders, "@bo_block_menu_objects", "header/menu/headerObjects");
		
		
		
		
		
		
		NData nData = null;
		NData nDataCollection = null;
		NData nDataCollectionItem = null;
		
		Map<Lang, MapTemplate> mtHeaderMenu1 = addMapTemplate(models, bMenuFree, position);
		
		Map<Lang, MapTemplate> mtHeaderMenu2 = addMapTemplate(models, bMenuObjects, position);
		
		//Menu Translated objects
		//EN
		nData = new NData();
		nData.setvVarchar50("Translated objects");
		nData.setVarType(ValueType.VARCHAR50);
		nData.setPropertyName("title");
		nData.setMapTemplate(mtHeaderMenu1.get(langEN));
		nDataService.save(nData);
		
		nDataCollection = new NData();
		nDataCollection.setvCollection(true);
		nDataCollection.setVarType(ValueType.COLLECTION);
		nDataCollection.setPropertyName("links");
		nDataCollection.setMapTemplate(mtHeaderMenu1.get(langEN));
		nDataService.save(nDataCollection);
		
		Link lkProjectEN = addLink(new Link(), langEN, "@bo_link_1", "Projects", null, null, null);
		nDataCollectionItem = new NData();
		nDataCollectionItem.setvTObject(lkProjectEN);
		nDataCollectionItem.setVarType(ValueType.TOBJECT);
		nDataCollectionItem.setOrdered(5);
		nDataCollectionItem.setData(nDataCollection);
		nDataService.save(nDataCollectionItem);
		
		Link lkAlbumEN = addLink(new Link(), langEN, "@bo_link_2", "Albums", null, null, null);
		nDataCollectionItem = new NData();
		nDataCollectionItem.setvTObject(lkAlbumEN);
		nDataCollectionItem.setVarType(ValueType.TOBJECT);
		nDataCollectionItem.setOrdered(10);
		nDataCollectionItem.setData(nDataCollection);
		nDataService.save(nDataCollectionItem);
		
		//FR
		nData = new NData();
		nData.setvVarchar50("Objets traduisibles");
		nData.setVarType(ValueType.VARCHAR50);
		nData.setPropertyName("title");
		nData.setMapTemplate(mtHeaderMenu1.get(langFR));
		nDataService.save(nData);
		
		nDataCollection = new NData();
		nDataCollection.setvCollection(true);
		nDataCollection.setVarType(ValueType.COLLECTION);
		nDataCollection.setPropertyName("links");
		nDataCollection.setMapTemplate(mtHeaderMenu1.get(langFR));
		nDataService.save(nDataCollection);
		
		Link lkProjectFR = addLink(new Link(), langFR, "@bo_link_1", "Projets", null, null, null);
		nDataCollectionItem = new NData();
		nDataCollectionItem.setvTObject(lkProjectFR);
		nDataCollectionItem.setVarType(ValueType.TOBJECT);
		nDataCollectionItem.setOrdered(5);
		nDataCollectionItem.setData(nDataCollection);
		nDataService.save(nDataCollectionItem);
		
		Link lkAlbumFR = addLink(new Link(), langFR, "@bo_link_2", "Albums", null, null, null);
		nDataCollectionItem = new NData();
		nDataCollectionItem.setvTObject(lkAlbumFR);
		nDataCollectionItem.setVarType(ValueType.TOBJECT);
		nDataCollectionItem.setOrdered(10);
		nDataCollectionItem.setData(nDataCollection);
		nDataService.save(nDataCollectionItem);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		nData = new NData();
//		nData.setvVarchar50("Objects traduits");
//		nData.setVarType(ValueType.VARCHAR50);
//		nData.setPropertyName("title");
//		nData.setMapTemplate(mtHeaderMenu1.get(langFR));
//		nDataService.save(nData);
//		
//		
//		
//		
//		Link lkProjectFR = addLink(lkProjectEN, langFR, LinkName, "Projets", null, null, null);
//		Link lkAlbumFR = addLink(lkAlbumEN, langFR, LinkName, "Albums", null, null, null);
//		
//		
//		
//		NData ndMenu1Link1 = new NData();
//		ndMenu1Link1.setvTObject(lkProjectEN);
//		ndMenu1Link1.setVarType(ValueType.TOBJECT);
//		ndMenu1Link1.setPropertyName("links");
//		ndMenu1Link1.setMapTemplate(mtHeaderMenu1.get(langEN));
//		nDataService.save(nData);
//		
//		Map<Lang, MapTemplate> mtHeaderMenu2 = addMapTemplate(models, bMenu, position);
//		Map<Lang, MapTemplate> mtHeaderMenu3 = addMapTemplate(models, bMenu, position);
	}
	
	
	
	

	@SuppressWarnings("unused")
	private void initBo() throws ServiceException{
		//Folder
		Folder folder = new Folder();
		folder.setName("back");
		List<String> serverNames = new ArrayList<>();
		serverNames.add("back");
		folder.setServerName(serverNames);
		folder.setPath("back/");
		folderService.save(folder);
		mapfolders.put(folder.getName(), folder);
		
		Map<Lang, Translation> mlogin = mkModel(folder, "login", "login/login");
		Map<Lang, Translation> pgLogin = mkPage(new Page(), folder, "login", "static", mlogin);
				
		// Positions
		Map<String, Position> mapPosition = new HashMap<>();
		Position pHeader = addPosition(mapPosition, "@bo_header");
		Position pheaderMenu = addPosition(mapPosition, "@bo_headerMenu");
		Position pNav = addPosition(mapPosition, "@bo_nav");
		Position pAside = addPosition(mapPosition, "@bo_aside");
		Position pArticle = addPosition(mapPosition, "@bo_article");
		Position pFooter = addPosition(mapPosition, "@bo_footer");
		
		// Models
		Map<Lang, Translation> mHome = mkModel(folder, "@bo_model_home", "home/home");
		Map<Lang, Translation> mList = mkModel(folder, "@bo_model_list", "default/default");
		Map<Lang, Translation> mView = mkModel(folder, "@bo_model_view", "default/default");
		Map<Lang, Translation> mEdit = mkModel(folder, "@bo_model_edit", "default/default");
		Map<Lang, Translation> mFile = mkModel(folder, "@bo_model_file", "file/file");
		Map<Lang, Translation> mFileSingle = mkModel(folder, "@bo_model_file_single", "file/single");
		
		// Pages
		Map<Lang, Translation> pgHome = mkPage(new Page(), folder, "@bo_page_home", "home", mHome);
		Map<Lang, Translation> pgList = mkPage(new Page(), folder, "@bo_page_list", "default", mList);
		Map<Lang, Translation> pgView = mkPage(new Page(), folder, "@bo_page_view", "default", mView);
		Map<Lang, Translation> pgEdit = mkPage(new Page(), folder, "@bo_page_edit", "default", mEdit);
		Map<Lang, Translation> pgFile = mkPage(new Page(), folder, "@bo_page_file", "default", mFile);
		Map<Lang, Translation> pgFileSingle = mkPage(new Page(), folder, "@bo_page_file_single", "default", mFileSingle);
		
		// PageBlocks
		Map<Lang, Translation> pbHeader = mkPageBlock(folder, "@bo_pageblock_header", "header/header");
		
		// PageBlocks
		Map<Lang, Translation> pbFooter = mkPageBlock(folder, "@bo_pageblock_footer", "footer/footer");
				
		// Blocks
		generateMenu(folder, pbHeader, pheaderMenu);
		
		
		
		
		
		
		Map<Lang, Translation> bList = mkBlock(folder, "@bo_block_list", "list/list");
		Map<Lang, Translation> bView = mkBlock(folder, "@bo_block_view", "view/view");
		Map<Lang, Translation> bEdit = mkBlock(folder, "@bo_block_edit", "edit/edit");
		Map<Lang, Translation> bFile = mkBlock(folder, "@bo_block_file", "file/file");
		
		Map<Lang, Translation> bNgList = mkBlock(folder, "@bo_ng_block_list", "list/nglist");
		
		
		
		// Set MapTemplate
		Map<Lang, MapTemplate> mtHeaderList = addMapTemplate(mList, pbHeader, pHeader);
		Map<Lang, MapTemplate> mtArticleList = addMapTemplate(mList, bList, pArticle);
		Map<Lang, MapTemplate> mtFooterList = addMapTemplate(mList, pbFooter, pFooter);
		
		Map<Lang, MapTemplate> mtHeaderView = addMapTemplate(mView, pbHeader, pHeader);
		Map<Lang, MapTemplate> mtArticleView = addMapTemplate(mView, bView, pArticle);
		Map<Lang, MapTemplate> mtFooterView = addMapTemplate(mView, pbFooter, pFooter);
		
		Map<Lang, MapTemplate> mtHeaderEdit = addMapTemplate(mEdit, pbHeader, pHeader);
		Map<Lang, MapTemplate> mtArticleEdit = addMapTemplate(mEdit, bEdit, pArticle);
		Map<Lang, MapTemplate> mtFooterEdit = addMapTemplate(mEdit, pbFooter, pFooter);
		
//		Map<Lang, MapTemplate> mtHeaderFile = addMapTemplate(mFile, pbHeader, pHeader);
//		Map<Lang, MapTemplate> mtArticleFile = addMapTemplate(mFile, bFile, pArticle);
//		Map<Lang, MapTemplate> mtFooterFile = addMapTemplate(mFile, pbFooter, pFooter);
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public Map<Lang, Translation> mkExperience(Experience base, Folder folder, String name, String context, Map<Lang, Translation> jobs, Map<Lang, Translation> resumes) throws ServiceException{
		List<Folder> folders = new ArrayList<>();
		folders.add(folder);
		return mkExperience(base, folders, name, jobs, resumes);
	}
	public Map<Lang, Translation> mkExperience(Experience base, List<Folder> folders, String name, Map<Lang, Translation> jobs, Map<Lang, Translation> resumes) throws ServiceException{
		Map<Lang, Translation> map = new HashMap<>();
		Experience first = null;
		for (Lang lang : langs) {
			Experience experience = null;
			if (first == null){
				experience = experienceService.translate(base, lang);
				experience.setName(name);
				experience.setDescription(name + " Page description " + lang.getCode());
				experience.setFolders(folders);
				experience.setJob((Job) jobs.get(lang));
				experience.setResume((Resume) resumes.get(lang));
				first = experience;
			} else {
				experience = experienceService.translate(first, lang);
				experience.setFolders(folders);
				experience.setName(name);
				experience.setDescription(name + " Page description " + lang.getCode());
				experience.setJob((Job) jobs.get(lang));
				experience.setResume((Resume) resumes.get(lang));
			}
						
			tObjectService.save(experience);
			map.put(lang, experience);
		}
		return map;
	}
	
	
	public Map<Lang, Translation> mkTranslation(Translation base, Folder folder, String name, String context) throws ServiceException{
		List<Folder> folders = new ArrayList<>();
		folders.add(folder);
		return mkTranslation(base, folders, name);
	}
	public Map<Lang, Translation> mkTranslation(Translation base, List<Folder> folders, String name) throws ServiceException{
		Map<Lang, Translation> map = new HashMap<>();
		Translation first = null;
		for (Lang lang : langs) {
			Translation translation = null;
			if (first == null){
				translation = tObjectService.translate(base, lang);
				translation.setName(name);
				translation.setDescription(name + " Page description " + lang.getCode());
				translation.setFolders(folders);
				first = translation;
			} else {
				translation = tObjectService.translate(first, lang);
				translation.setFolders(folders);
				translation.setName(name);
				translation.setDescription(name + " Page description " + lang.getCode());
			}
						
			tObjectService.save(translation);
			map.put(lang, translation);
		}
		return map;
	}
	
	
	private Date mkDate(int year, int month, int day){
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(year, month, day);
		return calendar.getTime();
	}
	
	@SuppressWarnings("unused")
	private void initCv() throws ServiceException{
		
		
		
		//Folder
		List<Folder> fldsResume = new ArrayList<>();
		List<Folder> fldsNull = null;
		
		Folder fldSamuel = new Folder();
		fldSamuel.setName("samuel");
		List<String> serverNames = new ArrayList<>();
		serverNames.add("localhost");
		serverNames.add("127.0.0.1");
		serverNames.add("samuel");
		fldSamuel.setServerName(serverNames);
		fldSamuel.setPath("resume/samuel/");
		folderService.save(fldSamuel);
		mapfolders.put(fldSamuel.getName(), fldSamuel);
		fldsResume.add(fldSamuel);
		
		Folder fldSurzilGeek = new Folder();
		fldSurzilGeek.setName("surzilgeek");
		serverNames = new ArrayList<>();
		serverNames.add("surzilgeek");
		fldSurzilGeek.setServerName(serverNames);
		fldSurzilGeek.setPath("resume/surzilgeek/");
		folderService.save(fldSurzilGeek);
		mapfolders.put(fldSurzilGeek.getName(), fldSurzilGeek);
		fldsResume.add(fldSurzilGeek);

		// Resumes
		Map<Lang, Translation> rCS = mkTranslation(new Resume("Cédric", "Sevestre", "Developper JAVA JEE", "info@cedric-sevestre.com", "+33 07 68 61 62 54", mkDate(1984, 7, 21), "903 Chemin de la croix des Banquets 84300 Cavaillon"), fldsResume, "cedricsevestre");
		Map<Lang, Translation> rJP = mkTranslation(new Resume("Justine", "Puiffe", "Project Manager", "justinepuiffe@gmail.com", "+33 06 16 03 08 84", mkDate(1990, 0, 30), "903 Chemin de la croix des Banquets 84300 Cavaillon"), fldsResume, "justinepuiffe");

		// Jobs
		Map<Lang, Translation> jWebDesigner = mkTranslation(new Job("Web Designer"), fldsResume, "webdesigner");
		Map<Lang, Translation> jDevelopper = mkTranslation(new Job("Developper"), fldsResume, "developper");

		// Experiences
		mkExperience(new Experience("Senior Graphic Designer", "MyCompany", mkDate(2012, 07, 12), null), fldsResume, "expCS2", jWebDesigner, rCS);
		mkExperience(new Experience("Former Graphic Designer", "MyCompany", mkDate(2011, 07, 10), mkDate(2012, 06, 25)), fldsResume, "expCS1", jDevelopper, rCS);
		
		mkExperience(new Experience("Senior Graphic Designer", "MyCompany", mkDate(2012, 07, 12), null), fldsResume, "expJP2", jWebDesigner, rJP);
		mkExperience(new Experience("Former Graphic Designer", "MyCompany", mkDate(2011, 07, 10), mkDate(2012, 06, 25)), fldsResume, "expJP1", jDevelopper, rJP);
		
		
		
		
		// Icons
		Icon iHome = mkIcon(new Icon("home", "flaticon-insignia", "fa fa-hand-peace-o", ""));
		Icon iResume = mkIcon(new Icon("resume", "flaticon-graduation61", "", ""));
		Icon iAboutMe = mkIcon(new Icon("aboutme", "", "fa fa-user-secret", ""));
		Icon iSkills = mkIcon(new Icon("skills", "", "fa fa-sliders", ""));
		Icon iExperiences = mkIcon(new Icon("experience", "", "fa fa-briefcase", ""));
		Icon iEducation = mkIcon(new Icon("education", "", "fa fa-leanpub", ""));
		Icon iPortfolio = mkIcon(new Icon("portfolio", "flaticon-book-bag2", "fa fa-picture-o", ""));
		Icon iContact = mkIcon(new Icon("contact", "flaticon-placeholders4", "fa fa-volume-control-phone", ""));
		Icon iFeedBack = mkIcon(new Icon("feedback", "flaticon-earphones18", "", ""));
		Icon iBlog = mkIcon(new Icon("blog", "flaticon-pens15", "fa fa-file-text-o", ""));
		Icon iSave = mkIcon(new Icon("save", "", "fa fa-floppy-o", ""));


		// Positions
		Map<String, Position> mapPosition = new HashMap<>();
		Position pHeader = addPosition(mapPosition, "resume_header");
		Position pheaderMenu = addPosition(mapPosition, "resume_headerMenu");
		Position pNav = addPosition(mapPosition, "resume_nav");
		Position pAside = addPosition(mapPosition, "resume_aside");
		Position pArticle = addPosition(mapPosition, "resume_article");
		Position pFooter = addPosition(mapPosition, "resume_footer");
		
		Position pAboutMe = addPosition(mapPosition, "resume_aboutMe");
		
		// Models
		Map<Lang, Translation> mHome = mkModel(fldsResume, "resume_model_home", "default/default");
		Map<Lang, Translation> mDefault = mkModel(fldsResume, "resume_model_default", "default/default");

		// Pages
		
		// Pages communes aux deux modèles
		Map<Lang, Translation> pgPortfolio = mkPage(new Category("#9b59b6", "Portfolio", iPortfolio, true, 40), fldsResume, "portfolio", "portfolio", mDefault);
		Map<Lang, Translation> pgContact = mkPage(new Category("#e67e22", "Contact", iContact, true, 30), fldsResume, "contact", "contact", mDefault);
		Map<Lang, Translation> pgBlog = mkPage(new Category("#d9a81d", "Blog", iBlog, true, 70), fldsResume, "blog", "blog", mDefault);

		// Pages dédiés à l'un ou l'autre
		Map<Lang, Translation> pgHomeSamuel = mkPage(new Category("#1abc9c", "Home", iHome, true, 10), fldSamuel, "home", "home", mHome);
		Map<Lang, Translation> pgResume = mkPage(new Category("#3498db", "Resume", iResume, true, 20), fldSamuel, "resume", "resume", mDefault);
		Map<Lang, Translation> pgFeedBack = mkPage(new Category("#e74c3c", "Feedback", iFeedBack, true, 50), fldSamuel, "feedback", "feedback", mDefault);
		
		Map<Lang, Translation> pgHomeSurzilGeek = mkPage(new Category("#1abc9c", "Home", iHome, true, 10), fldSurzilGeek, "home", "home", mHome);
		Map<Lang, Translation> pgAboutMe = mkPage(putCategoryAboutMe(new Category(null, "About me", iAboutMe, true, 12)), fldSurzilGeek, "aboutme", "aboutme", mDefault);
		Map<Lang, Translation> pgSkills = mkPage(new Category(null, "Skills", iSkills, true, 14), fldSurzilGeek, "skills", "skills", mDefault);
		Map<Lang, Translation> pgExperiences = mkPage(new Category(null, "Experiences", iExperiences, true, 16), fldSurzilGeek, "experiences", "experiences", mDefault);
		Map<Lang, Translation> pgEducation = mkPage(new Category(null, "Education", iEducation, true, 18), fldSurzilGeek, "education", "education", mDefault);
		Map<Lang, Translation> pgResumeSurzilGeek = mkPage(new Category(null, "My resume PDF", iSave, true, 60), fldSurzilGeek, "resume", "resume", mDefault);
		
		

		
		// PageBlocks
		Map<Lang, Translation> pbHeader = mkPageBlock(fldsResume, "resume_pageblock_header", "header/header");
		Map<Lang, Translation> pbFooter = mkPageBlock(fldsResume, "resume_pageblock_footer", "footer/footer");
		Map<Lang, Translation> pbListPage = mkPageBlock(fldSurzilGeek, "resume_pageblock_listpage", "listpage/listpage");
		Map<Lang, Translation> pbAboutMe = mkPageBlock(fldsResume, "resume_pageblock_aboutme", "aboutme/aboutme");
		
		
		// Blocks
		Map<Lang, Translation> bNav = mkBlock(fldsResume, "resume_block_nav", "nav/nav");
		Map<Lang, Translation> bSkills = mkBlock(fldsResume, "resume_block_skills", "skills/skills");
		Map<Lang, Translation> bExperiences = mkBlock(fldsResume, "resume_block_experiences", "experience/experience");
		Map<Lang, Translation> bEducation = mkBlock(fldsResume, "resume_block_education", "education/education");
		Map<Lang, Translation> bPortfolio = mkBlock(fldsResume, "resume_block_portfolio", "portfolio/portfolio");
		Map<Lang, Translation> bContact = mkBlock(fldsResume, "resume_block_contact", "contact/contact");
		Map<Lang, Translation> bBlog = mkBlock(fldsResume, "resume_block_blog", "blog/blog");
		Map<Lang, Translation> bResume = mkBlock(fldsResume, "resume_block_resume", "resume/resume");

		Map<Lang, Translation> bAchievement = mkBlock(fldSurzilGeek, "resume_block_achievement", "achievement/achievement");
		
		// Set MapTemplate
		
		// Header on model Home and Default - All pages model
		Map<Lang, MapTemplate> mtHeaderMHome = addMapTemplate(mHome, pbHeader, pHeader);
		Map<Lang, MapTemplate> mtHeaderMDefault = addMapTemplate(mDefault, pbHeader, pHeader);
		
		// Footer on model Home and Default - All pages model
		Map<Lang, MapTemplate> mtFooterMHome = addMapTemplate(mHome, pbFooter, pFooter);
		Map<Lang, MapTemplate> mtFooterMDefault = addMapTemplate(mDefault, pbFooter, pFooter);
		
		// Nav on model Home and Default and pb Header - All pages model
		Map<Lang, MapTemplate> mtNavMHome = addMapTemplate(mHome, bNav, pNav);
		Map<Lang, MapTemplate> mtNavMDefault = addMapTemplate(mDefault, bNav, pNav);
		Map<Lang, MapTemplate> mtNavpbHeader = addMapTemplate(pbHeader, bNav, pNav);
		
		// Listpage on page Home for folder SurzilGeek only
		Map<Lang, MapTemplate> mtPgHomeSurzilGeek = addMapTemplate(mHome, pbListPage, pArticle);
		
		// blocks on pages
		Map<Lang, MapTemplate> mtAboutmePgAboutMe = addMapTemplate(pgAboutMe, pbAboutMe, pArticle);
		Map<Lang, MapTemplate> mtAchievementPgAboutMe = addMapTemplate(pgAboutMe, bAchievement, pAboutMe);
		
		Map<Lang, MapTemplate> mtSkillsPgSkills = addMapTemplate(pgSkills, bSkills, pArticle);
		Map<Lang, MapTemplate> mtExperiencesPgExperiences = addMapTemplate(pgExperiences, bExperiences, pArticle);
		Map<Lang, MapTemplate> mtEducationPgEducation = addMapTemplate(pgEducation, bEducation, pArticle);
		Map<Lang, MapTemplate> mtPortfolioPgPortfolio = addMapTemplate(pgPortfolio, bPortfolio, pArticle);
		Map<Lang, MapTemplate> mtContactPgContact = addMapTemplate(pgContact, bContact, pArticle);
		Map<Lang, MapTemplate> mtBlogPgBlog = addMapTemplate(pgBlog, bBlog, pArticle);
		Map<Lang, MapTemplate> mtResumePgResume = addMapTemplate(pgResumeSurzilGeek, bResume, pArticle);
		
		
	}
	
	private Page putCategoryAboutMe(Page page){
		String description = "<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>";
		page.setDescription(description);
		return page;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
