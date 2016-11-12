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

import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.independant.objects.FolderService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;

@Component
@Scope(value = "singleton")
@PropertySource(value = "classpath:config.properties", name = "config")
public class Common {	
	private Map<String, Page> pages;
	
	private Map<String, Folder> foldersByName;
	private Map<String, Folder> foldersByServerName;
	
	private String applicationFolder;
	private String webInfFolder;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private PageService pageService;

	@Resource
	private Environment environment;
	

	public enum TypeBase {
		VIEWS("views/"),
		COMMON("common/"),
		ADMIN("admin/")
	    ;
	    private final String text;
	    private TypeBase(final String text) {
	        this.text = text;
	    }
	    @Override
	    public String toString() {
	        return text;
	    }
	}
	
	public static final Boolean DEBUG = false;
	
	public static final String BACK = "back";
	public static final String FRONT = "front";
	
	public static final String BASE_WEBINF = "/WEB-INF/";
	
	@Deprecated
	public static final String BASE_BO_VIEWS_PATH = "bo/";
	
	@Deprecated
	public static final String CUSTOM_ENTITY_PACKAGE = "fr.cedricsevestre.entity.custom";
	
	@Deprecated
	public static final String CUSTOM_SERVICE_PACKAGE = "fr.cedricsevestre.service.custom";
	
	public String getWebInfFolder() {
		if (webInfFolder == null) {
			webInfFolder = servletContext.getRealPath(BASE_WEBINF);
		}
		return webInfFolder;
	}
	
	private void addFolder(String serverName) throws ServiceException{
		Folder folder = folderService.findByServerName(serverName);
		String folderName = folder.getName();
		if (foldersByName.containsKey(folderName)){
			folder = foldersByName.get(folderName);
		} else {
			foldersByName.put(folderName, folder);
		}
		foldersByServerName.put(serverName, folder);
	}
	
	public Folder getFolder(String serverName) throws ServiceException {
		if (foldersByName == null){
			foldersByName = new HashMap<>();
			foldersByServerName = new HashMap<>();
		}
		if(!foldersByServerName.containsKey(serverName)){
			addFolder(serverName);
		}
		return foldersByServerName.get(serverName);
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
	
	public String getBasePath(Boolean webInf, Folder folder, TypeBase typeBase){
		return (webInf ? BASE_WEBINF : "") + 
			   (folder != null ? folder.getPath() : "") + 
			   (typeBase.toString());
	}
	
	
	
	
	
	
	
	
		
	public String getApplicationFolder() {
		if (applicationFolder == null) 
			applicationFolder = environment.getProperty("application.folder");
		return applicationFolder;
	}


	
}
