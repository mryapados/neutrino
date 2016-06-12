package fr.cedricsevestre.common;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.back.Page;
import fr.cedricsevestre.entity.back.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.back.PageService;


@Component
@Scope(value = "singleton")
@PropertySource(value = "classpath:config.properties", name = "config")
public class Common {
	@Deprecated
	private Map<String, Template> modelsByPage;
	@Deprecated
	private Map<String, Page> pagesByModel;
	
	private Map<String, Page> pages;
	
	private String applicationFolder;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private PageService pageService;

	@Resource
	private Environment environment;
	
	public static final Boolean DEBUG = false;
	
	public static final String BACK = "back";
	public static final String FRONT = "front";
	
	public static final String HOMEPAGE = "home";
	public static final String HOMEPROJECTPAGE = "project";
	public static final String LOGINPAGE = "login";
	
	public static final String BASEADMINPATH = "/WEB-INF/admin/";
	public static final String BASEPAGESPATH = "/WEB-INF/pages/";
	
	public String getWebInfFolder() {
		return servletContext.getRealPath("/WEB-INF/");
	}
	
	@Deprecated
	private void setPageAndModel(String pageName) throws ServiceException{
		Page page = pageService.findByName(pageName);
		Template template = page.getModel();
		pagesByModel.put(template.getName(), page);
		modelsByPage.put(pageName, template);
	}
	
	@Deprecated
	private void init() throws ServiceException{
		if (modelsByPage == null || pagesByModel == null){
			pagesByModel = new HashMap<>();
			modelsByPage = new HashMap<>();
		}
	}
	
	public Page getPage(String pageNameWithoutLangCode, String langCode) throws ServiceException {
		String pageName = pageNameWithoutLangCode + "_" + langCode;
		if (pages == null){
			pages = new HashMap<>();
		}
		if(!pages.containsKey(pageName)){
			pages.put(pageName, pageService.findByName(pageName));
		}
		return pages.get(pageName);
	}
	
	@Deprecated
	public Template getModelByPageName(String pageName) throws ServiceException {
		init();
		if(!modelsByPage.containsKey(pageName)){
			setPageAndModel(pageName);
		}
		return modelsByPage.get(pageName);
	}
	
	@Deprecated
	public Page getPageByModelName(String modelName) throws ServiceException {
		init();
		if(!pagesByModel.containsKey(modelName)){
			setPageAndModel(modelName);
		}
		return pagesByModel.get(modelName);
	}
	
	public String getApplicationFolder() {
		// TODO double check lockin
		if (applicationFolder == null) 
			applicationFolder = environment.getProperty("application.folder");
		return applicationFolder;
	}


	
	
	
	
	
	
	
	
	
}
