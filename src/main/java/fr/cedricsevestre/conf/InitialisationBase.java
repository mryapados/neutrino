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

import fr.cedricsevestre.entity.back.Base;
import fr.cedricsevestre.entity.back.Lang;
import fr.cedricsevestre.entity.back.MapTemplate;
import fr.cedricsevestre.entity.back.NData;
import fr.cedricsevestre.entity.back.NSchema;
import fr.cedricsevestre.entity.back.NType;
import fr.cedricsevestre.entity.back.NType.ValueType;
import fr.cedricsevestre.entity.back.Page;
import fr.cedricsevestre.entity.back.Position;
import fr.cedricsevestre.entity.back.Template;
import fr.cedricsevestre.entity.back.Translation;
import fr.cedricsevestre.entity.front.Member;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.back.LangService;
import fr.cedricsevestre.service.back.MapTemplateService;
import fr.cedricsevestre.service.back.NDataService;
import fr.cedricsevestre.service.back.NSchemaService;
import fr.cedricsevestre.service.back.PageService;
import fr.cedricsevestre.service.back.PositionService;
import fr.cedricsevestre.service.back.TemplateService;
import fr.cedricsevestre.service.back.TranslationService;
import fr.cedricsevestre.service.front.MemberService;


@Component
public class InitialisationBase {
	public InitialisationBase() {

	}
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
	
	@Autowired
	private TranslationService translationService;
	
	public void run() throws ServiceException {
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
		
		
		
		
		
		
		
		
		
//		Template template = new Template();
//		template.setDateAdd(new Date());
//		template.setName("home");
//		template.setDescription("Page d'accueil");
//		template.setMetaTitle("home");
//		template.setMetaDescription("MetaDescription");
//		template.setPath("home/home");
//		template.setType(Template.TemplateType.PAGE);
//		templateService.save(template);
//		
//		template = new Template();
//		template.setDateAdd(new Date());
//		template.setName("homeProject");
//		template.setDescription("Page d'accueil projets");
//		template.setMetaTitle("{0}");
//		template.setMetaDescription("MetaDescription");
//		template.setPath("home/homeProject");
//		template.setType(Template.TemplateType.PAGE);
//		templateService.save(template);

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
		templateEN.setMetaTitle("MetaTitle");
		templateEN.setMetaDescription("MetaDescription");
		templateEN.setPath("header/headerProject");
		templateEN.setType(Template.TemplateType.BLOCK);
		templateService.save(templateEN);
		
		Template templateFr = templateService.translate(templateEN, langFR);
		templateFr.setName(name + "_" + langFR.getCode().toUpperCase());
		templateFr.setDescription(name + " description fr");
		templateService.save(templateFr);
		
		
		
		
		
		
		Template template = new Template();
//		template.setDateAdd(new Date());
//		template.setName("headerProject");
//		template.setDescription("Block header");
//		template.setPath("header/headerProject");
//		template.setType(Template.TemplateType.BLOCK);
//		templateService.save(template);
		
		template = new Template();
		template.setDateAdd(new Date());
		template.setName("headerProjectH2");
		template.setDescription("Block header");
		template.setPath("header/headerProjectH2");
		template.setType(Template.TemplateType.BLOCK);
		templateService.save(template);
		
		
		template = new Template();
		template.setDateAdd(new Date());
		template.setName("socialNetwork");
		template.setDescription("Twitter, Facebook, Google+, ...");
		template.setPath("socialnetwork/socialNetwork");
		template.setType(Template.TemplateType.BLOCK);
		templateService.save(template);
		
		template = new Template();
		template.setDateAdd(new Date());
		template.setName("advertisement");
		template.setDescription("Here your advertisement block");
		template.setPath("advertisement/advertisement");
		template.setType(Template.TemplateType.BLOCK);
		templateService.save(template);
		
		template = new Template();
		template.setDateAdd(new Date());
		template.setName("articleTitle");
		template.setDescription("Here define the article title");
		template.setPath("article/title/articleTitle");
		template.setType(Template.TemplateType.BLOCK);
		templateService.save(template);
		
		template = new Template();
		template.setDateAdd(new Date());
		template.setName("articleContent");
		template.setDescription("Here define the article content");
		template.setPath("article/content/articleContent");
		template.setType(Template.TemplateType.BLOCK);
		templateService.save(template);
		
		template = new Template();
		template.setDateAdd(new Date());
		template.setName("album");
		template.setDescription("Block list albums");
		template.setPath("nav/album/album");
		template.setType(Template.TemplateType.BLOCK);
		templateService.save(template);
		
		template = new Template();
		template.setDateAdd(new Date());
		template.setName("blockTest1");
		template.setDescription("Block pannel test 1");
		template.setPath("test/blockTest1");
		template.setType(Template.TemplateType.BLOCK);
		templateService.save(template);
		
		template = new Template();
		template.setDateAdd(new Date());
		template.setName("blockTest2");
		template.setDescription("Block pannel test 2");
		template.setPath("test/blockTest2");
		template.setType(Template.TemplateType.BLOCK);
		templateService.save(template);		
		
		template = new Template();
		template.setDateAdd(new Date());
		template.setName("blockTest3");
		template.setDescription("Block Test");
		template.setPath("test/subtest/blockTest3");
		template.setType(Template.TemplateType.BLOCK);
		templateService.save(template);
		
		
		template = new Template();
		template.setDateAdd(new Date());
		template.setName("Block Test 4");
		template.setDescription("Z Block Test");
		template.setPath("b/test/test.jsp");
		template.setType(Template.TemplateType.BLOCK);
		templateService.save(template);
		
		template = new Template();
		template.setDateAdd(new Date());
		template.setName("Block Test 5");
		template.setDescription("Block Test");
		template.setPath("a/test/test.jsp");
		template.setType(Template.TemplateType.BLOCK);
		templateService.save(template);
		

		
	}
	public void initPageBlocs() throws ServiceException{
		System.out.println("init Page blocs");
		Template template = new Template();
		template.setDateAdd(new Date());
		template.setName("article");
		template.setDescription("Page Block article");
		template.setPath("article/article");
		template.setType(Template.TemplateType.PAGEBLOCK);
		
		template.setSchema(nSchemaService.findById(1));
		templateService.save(template);
		
		template = new Template();
		template.setDateAdd(new Date());
		template.setName("article2");
		template.setDescription("Page Block article 2");
		template.setPath("article/article2");
		template.setType(Template.TemplateType.PAGEBLOCK);
		templateService.save(template);
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
		mapTemplates.add(addMapTemplate("homeProject_EN","socialNetwork", "aside", 10));
		mapTemplates.add(addMapTemplate("homeProject_EN","album", "nav", 10));
		mapTemplates.add(addMapTemplate("homeProject_EN","blockTest1", "nav", 20));
		mapTemplates.add(addMapTemplate("homeProject_EN","blockTest2", "nav", 30));
//		mapTemplates.add(addMapTemplate("homeProject_EN","articleContent", "article", 10));
//		mapTemplates.add(addMapTemplate("homeProject_EN","articleTitle", "article", 10));
		
		mapTemplates.add(addMapTemplate("article","articleTitle", "title", 10));
		mapTemplates.add(addMapTemplate("article","articleContent", "content", 20));
		mapTemplates.add(addMapTemplate("homeProject_EN","article", "article", 10));
		
		mapTemplates.add(addMapTemplate("article2","socialNetwork", "title", 10));
		mapTemplates.add(addMapTemplate("homeProject_EN","article2", "article", 10));
		
		
		mapTemplates.add(addMapTemplate("home_EN","blockTest3", "article", 10));
		mapTemplates.add(addMapTemplate("home_EN","socialNetwork", "article", 20));
		
		return mapTemplates;
	}
	
	public void initNSchemas() throws ServiceException{
		System.out.println("init nShemas");
		
		NSchema nSchema = new NSchema();
		Map<String, NType> columns = new HashMap<>();
		columns.put("title", new NType(NType.ValueType.VARCHAR255));
		columns.put("content", new NType(NType.ValueType.HTML));
		columns.put("numbers", new NType(NType.ValueType.COLLECTION, NType.ValueType.INTEGER));
		nSchema.setColumns(columns);
		nSchema.setScope(NSchema.ScopeType.ALL);
		nSchemaService.save(nSchema);
	}

	public void initNDatas() throws ServiceException{
		System.out.println("init nDatas");
		
		Template template = templateService.findByName("article");
		
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
		
		
	}
	
}
