package fr.cedricsevestre.common;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.engine.Page;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.PageService;

@Component
@Scope(value = "singleton")
@PropertySource(value = "classpath:config.properties", name = "config")
public class Common {	
	private Map<String, Page> pages;
	
	private String applicationFolder;
	private String webInfFolder;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private PageService pageService;

	@Resource
	private Environment environment;
	
	public static final Boolean DEBUG = false;
	
	public static final String BACK = "back";
	public static final String FRONT = "front";
	
	public static final String BASE_WEBINF = "/WEB-INF/";
	public static final String BASE_PAGES_VIEWS_PATH = "pages/views/";
	public static final String BASE_WEBINF_ADMIN_PATH = BASE_WEBINF + "admin/";
	public static final String BASE_WEBINF_PAGES_VIEWS_PATH = BASE_WEBINF + BASE_PAGES_VIEWS_PATH;
	public static final String BASE_PAGES_COMMON_PATH = "pages/common/";
	public static final String BASE_WEBINF_PAGES_COMMON_PATH = BASE_WEBINF + "pages/common/";
	
	public String getWebInfFolder() {
		if (webInfFolder == null) {
			webInfFolder = servletContext.getRealPath(BASE_WEBINF);
		}
		return webInfFolder;
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
		
	public String getApplicationFolder() {
		if (applicationFolder == null) 
			applicationFolder = environment.getProperty("application.folder");
		return applicationFolder;
	}


	
}
