package fr.cedricsevestre.conf;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser.NumberType;

import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.custom.Album.AlbumType;
import fr.cedricsevestre.entity.custom.Member;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.MapTemplate;
import fr.cedricsevestre.entity.engine.NData;
import fr.cedricsevestre.entity.engine.NSchema;
import fr.cedricsevestre.entity.engine.NType;
import fr.cedricsevestre.entity.engine.Page;
import fr.cedricsevestre.entity.engine.Position;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.entity.engine.TranslationProvider;
import fr.cedricsevestre.entity.engine.NType.ValueType;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.custom.AlbumService;
import fr.cedricsevestre.service.custom.MemberService;
import fr.cedricsevestre.service.custom.ProjectService;
import fr.cedricsevestre.service.engine.LangService;
import fr.cedricsevestre.service.engine.MapTemplateService;
import fr.cedricsevestre.service.engine.NDataService;
import fr.cedricsevestre.service.engine.NSchemaService;
import fr.cedricsevestre.service.engine.PageService;
import fr.cedricsevestre.service.engine.PositionService;
import fr.cedricsevestre.service.engine.TemplateService;
import fr.cedricsevestre.service.engine.TranslationService;


@Component
public class InitialisationBase {
	public InitialisationBase() {

	}
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private AlbumService albumService;

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
	private MapTemplateService mapService;
	
	@Autowired
	private PageService pageService;
	
	public void run() throws ServiceException, InstantiationException, IllegalAccessException {
		System.out.println("init");
		initLangs();
		initUsers();
		initNSchemas();
		initTemplates();
		initPages();
		initPositions();
		initBlocs();
		initPageBlocs();
		initMapTemplates();

		initProject();
		initAlbum();
		
		initNDatas();
		
	}

	private Lang langEN;
	private Lang langFR;
	public void initLangs() throws ServiceException{
		System.out.println("init langs");
		langEN = new Lang("en");
		langService.save(langEN);
		
		langFR = new Lang("fr");
		langService.save(langFR);
	}
	
	
	public void initUsers() throws ServiceException{
		System.out.println("init users");
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
		System.out.println("init templates");
				
		Template loginEN = templateService.translate(new Template(), langEN);
		loginEN.setName("login_" + langEN.getCode().toUpperCase());
		loginEN.setDescription("login description en");
		loginEN.setMetaTitle("login");
		loginEN.setMetaDescription("MetaDescription");
		loginEN.setPath("login/login");
		loginEN.setType(Template.TemplateType.PAGE);
		templateService.save(loginEN);
		
		Template loginFr = templateService.translate(loginEN, langFR);
		loginFr.setName("login_" + langFR.getCode().toUpperCase());
		loginFr.setDescription("login description fr");
		templateService.save(loginFr);

		
		
		Template homeEN = templateService.translate(new Template(), langEN);
		homeEN.setName("home_" + langEN.getCode().toUpperCase());
		homeEN.setDescription("home description en");
		homeEN.setMetaTitle("home");
		homeEN.setMetaDescription("MetaDescription");
		homeEN.setPath("home/home");
		homeEN.setType(Template.TemplateType.PAGE);
		templateService.save(homeEN);
		
		Template homeFr = templateService.translate(homeEN, langFR);
		homeFr.setName("home_" + langFR.getCode().toUpperCase());
		homeFr.setDescription("home description fr");
		templateService.save(homeFr);

		
		
		Template homeProjectEN = templateService.translate(new Template(), langEN);
		homeProjectEN.setName("homeProject_" + langEN.getCode().toUpperCase());
		homeProjectEN.setDescription("homeProject description en");
		homeProjectEN.setMetaTitle("{0}");
		homeProjectEN.setMetaDescription("MetaDescription");
		homeProjectEN.setPath("home/homeProject");
		homeProjectEN.setType(Template.TemplateType.PAGE);
		templateService.save(homeProjectEN);
		
		Template homeProjectFr = templateService.translate(homeProjectEN, langFR);
		homeProjectFr.setName("homeProject_" + langFR.getCode().toUpperCase());
		homeProjectFr.setDescription("homeProject description fr");
		templateService.save(homeProjectFr);

		
		
		Template homeArticleEN = templateService.translate(new Template(), langEN);
		homeArticleEN.setName("homeArticle_" + langEN.getCode().toUpperCase());
		homeArticleEN.setDescription("homeArticle description en");
		homeArticleEN.setMetaTitle("{0}");
		homeArticleEN.setMetaDescription("MetaDescription");
		homeArticleEN.setPath("home/homeArticle");
		homeArticleEN.setType(Template.TemplateType.PAGE);
		templateService.save(homeArticleEN);
		
		Template homeArticleFr = templateService.translate(homeArticleEN, langFR);
		homeArticleFr.setName("homeArticle_" + langFR.getCode().toUpperCase());
		homeArticleFr.setDescription("homeArticle description fr");
		templateService.save(homeArticleFr);

	}
	
	public void initPages() throws ServiceException{
		System.out.println("init templates");
		
		Page loginEN = pageService.translate(new Page(), langEN);
		loginEN.setName("login_" + langEN.getCode().toUpperCase());
		loginEN.setContext("static");
		loginEN.setDescription("login description en");
		loginEN.setModel(templateService.findByNameWithAllExceptData("login_" + langEN.getCode().toUpperCase()));
		pageService.save(loginEN);
		
		Page loginFr = pageService.translate(loginEN, langFR);
		loginFr.setName("login_" + langFR.getCode().toUpperCase());
		loginFr.setDescription("login description fr");
		pageService.save(loginFr);
		
		
		Page homeEN = pageService.translate(new Page(), langEN);
		homeEN.setName("home_" + langEN.getCode().toUpperCase());
		homeEN.setContext("home");
		homeEN.setDescription("home description en");
		homeEN.setModel(templateService.findByNameWithAllExceptData("home_" + langEN.getCode().toUpperCase()));
		pageService.save(homeEN);
		
		Page homeFr = pageService.translate(homeEN, langFR);
		homeFr.setName("home_" + langFR.getCode().toUpperCase());
		homeFr.setDescription("home description fr");
		pageService.save(homeFr);
		
		
		Page projectEN = pageService.translate(new Page(), langEN);
		projectEN.setName("project_" + langEN.getCode().toUpperCase());
		projectEN.setContext("project");
		projectEN.setDescription("project description en");
		projectEN.setModel(templateService.findByNameWithAllExceptData("homeProject_" + langEN.getCode().toUpperCase()));
		pageService.save(projectEN);
		
		Page projectFr = pageService.translate(projectEN, langFR);
		projectFr.setName("project_" + langFR.getCode().toUpperCase());
		projectFr.setDescription("project description fr");
		pageService.save(projectFr);
		
		
		Page articleEN = pageService.translate(new Page(), langEN);
		articleEN.setName("article_" + langEN.getCode().toUpperCase());
		articleEN.setContext("article");
		articleEN.setDescription("article description en");
		articleEN.setModel(templateService.findByNameWithAllExceptData("homeArticle_" + langEN.getCode().toUpperCase()));
		pageService.save(articleEN);
		
		Page articleFr = pageService.translate(articleEN, langFR);
		articleFr.setName("article_" + langFR.getCode().toUpperCase());
		articleFr.setDescription("article description fr");
		pageService.save(articleFr);
		
	}

	public List<Position> initPositions() throws ServiceException{
		System.out.println("init positions");
		
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
	public void initBlocs() throws ServiceException{
		System.out.println("init blocs");
		String name = "";
		
		Template templateEN = templateService.translate(new Template(), langEN);
		name = "headerProject";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " description en");
		templateEN.setPath("header/headerProject");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		Template templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " description fr");
		templateService.save(templateFR);
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "headerProjectH2";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " description en");
		templateEN.setPath("header/headerProjectH2");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " description fr");
		templateService.save(templateFR);
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "socialNetwork";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " Twitter, Facebook, Google+, ... en");
		templateEN.setPath("socialnetwork/socialNetwork");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " Twitter, Facebook, Google+, ... fr");
		templateService.save(templateFR);
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "advertisement";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " advertisement description en");
		templateEN.setPath("advertisement/advertisement");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " advertisement description fr");
		templateService.save(templateFR);
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "articleTitle";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " articleTitle description en");
		templateEN.setPath("article/title/articleTitle");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " articleTitle description fr");
		templateService.save(templateFR);
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "articleContent";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " advertisement articleContent en");
		templateEN.setPath("article/content/articleContent");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " articleContent description fr");
		templateService.save(templateFR);
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "album";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " album articleContent en");
		templateEN.setPath("nav/album/album");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " album description fr");
		templateService.save(templateFR);		
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "blockTest1";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " blockTest1 articleContent en");
		templateEN.setPath("test/blockTest1");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " blockTest1 description fr");
		templateService.save(templateFR);		
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "blockTest2";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " blockTest2 articleContent en");
		templateEN.setPath("test/blockTest2");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " blockTest2 description fr");
		templateService.save(templateFR);	
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "blockTest3";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " blockTest3 articleContent en");
		templateEN.setPath("test/subtest/blockTest3");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " blockTest3 description fr");
		templateService.save(templateFR);	
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "map";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " description en");
		templateEN.setPath("map/map");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " description fr");
		templateService.save(templateFR);
		
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "horizontal";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " description en");
		templateEN.setPath("nav/horizontal/horizontal");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " description fr");
		templateService.save(templateFR);
		
		
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "vectormapfra";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " description en");
		templateEN.setPath("vectormap/fra/vectorMapFra");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " description fr");
		templateService.save(templateFR);
	}
	public void initPageBlocs() throws ServiceException{
		System.out.println("init Page blocs");
		Template templateEN = null;
		Template templateFR = null;
		String name = null;
		
		templateEN = templateService.translate(new Template(), langEN);
		name = "article";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " Page Block article description en");
		templateEN.setPath("article/article");
		templateEN.setType(Template.TemplateType.PAGEBLOCK);
		templateEN.setSchema(nSchemaService.findById(1));
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFR.setDescription(name + " Page Block article description fr");
		templateService.save(templateFR);	
		

		templateEN = templateService.translate(new Template(), langEN);
		name = "article2";
		templateEN.setName(name + "_" + langEN.getCode().toUpperCase());
		templateEN.setDescription(name + " Page Block article2 description en");
		templateEN.setPath("article/article2");
		templateEN.setType(Template.TemplateType.PAGEBLOCK);
		templateEN.setSchema(nSchemaService.findById(1));
		templateService.save(templateEN);
		
		templateFR = templateService.translate(templateEN, langFR);
		templateFR.setName(name + "_" + langFR.getCode().toUpperCase());
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
	
	public MapTemplate addMapTemplate(String model, String block, String position, Integer ordered) throws ServiceException{
		MapTemplate mapTemplate = new MapTemplate();
		mapTemplate.setModel(templateService.findByName(model));
		mapTemplate.setBlock(templateService.findByName(block));
		mapTemplate.setPosition(positionService.findByName(position));
		mapTemplate.setOrdered(ordered);
		mapService.save(mapTemplate);
		return mapTemplate;
	}
	
	
	
	public List<MapTemplate> initMapTemplates() throws ServiceException{
		System.out.println("init maps");
		
		ArrayList<MapTemplate> mapTemplates = new ArrayList<>();
		
		mapTemplates.add(addMapTemplate("homeProject_EN","headerProject_EN", "header", 10));
		mapTemplates.add(addMapTemplate("homeProject_EN","socialNetwork_EN", "aside", 10));
		mapTemplates.add(addMapTemplate("homeProject_EN","album_EN", "nav", 10));
		mapTemplates.add(addMapTemplate("homeProject_EN","blockTest1_EN", "nav", 20));
		mapTemplates.add(addMapTemplate("homeProject_EN","blockTest2_EN", "nav", 30));
//		mapTemplates.add(addMapTemplate("homeProject_EN","articleContent", "article", 10));
//		mapTemplates.add(addMapTemplate("homeProject_EN","articleTitle", "article", 10));
		
		mapTemplates.add(addMapTemplate("article_EN","articleTitle_EN", "title", 10));
		mapTemplates.add(addMapTemplate("article_EN","articleContent_EN", "content", 20));
		mapTemplates.add(addMapTemplate("homeProject_EN","article_EN", "article", 10));
		
		mapTemplates.add(addMapTemplate("article2_EN","socialNetwork_EN", "title", 10));
		mapTemplates.add(addMapTemplate("homeProject_EN","article2_EN", "article", 10));
		
		
		mapTemplates.add(addMapTemplate("home_EN","blockTest3_EN", "article", 10));
		mapTemplates.add(addMapTemplate("home_EN","socialNetwork_EN", "article", 20));
		
		return mapTemplates;
	}
	
	public void initNSchemas() throws ServiceException{
		System.out.println("init nShemas");
		
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
		System.out.println("init project");
		
		
		Project project = projectService.findByName("tesage");
		
		
		
		String name = "";
		
		Project projectEN =   projectService.translate(new Project(), langEN, Project.class);
		name = "testproject";
		projectEN.setName(name + "_" + langEN.getCode().toUpperCase());
		projectEN.setDescription(name + " description en");
		projectService.save(projectEN);
		
		Project projectFR = projectService.translate(projectEN, langFR, Project.class);
		projectFR.setName(name + "_" + langFR.getCode().toUpperCase());
		projectFR.setDescription(name + " description fr");
		projectService.save(projectFR);
	
	
	
	}
	
	
	public void initAlbum() throws ServiceException, InstantiationException, IllegalAccessException{
		System.out.println("init album");
		String name = "";
		
		Album albumEN = albumService.translate(new Album(), langEN, Album.class);
		name = "testalbum";
		albumEN.setName(name + "_" + langEN.getCode().toUpperCase());
		albumEN.setDescription(name + " description en");
		albumEN.setProject(projectService.findByName("testproject_EN"));
		albumEN.setType(AlbumType.DEFAULT);
		albumService.save(albumEN);
		
		Album albumFR = albumService.translate(albumEN, langFR, Album.class);
		albumFR.setName(name + "_" + langFR.getCode().toUpperCase());
		albumFR.setDescription(name + " description fr");
		albumFR.setProject(projectService.findByName("testproject_FR"));
		albumFR.setType(AlbumType.DEFAULT);
		albumService.save(albumFR);

	
		System.out.println("AAAAAAAAAAAAAAAAa testalbum_FR");
		Album test = albumService.findByName("testalbum_FR");
		
		
	}
	
	

	public void initNDatas() throws ServiceException{
		System.out.println("init nDatas");
		
		Template template = templateService.findByName("article_EN");
		
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
		nData.setvTObject(albumService.findByName("testalbum_EN"));
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
		nDataCollectionItem.setvTObject(albumService.findByName("testalbum_EN"));
		nDataCollectionItem.setVarType(ValueType.TOBJECT);
		nDataCollectionItem.setOrdered(5);
		nDataCollectionItem.setData(nDataCollection);
		nDataService.save(nDataCollectionItem);
		
		nDataCollectionItem = new NData();
		nDataCollectionItem.setvTObject(albumService.findByName("testalbum_FR"));
		nDataCollectionItem.setVarType(ValueType.TOBJECT);
		nDataCollectionItem.setOrdered(2);
		nDataCollectionItem.setData(nDataCollection);
		nDataService.save(nDataCollectionItem);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
