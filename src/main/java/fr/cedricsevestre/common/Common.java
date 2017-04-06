package fr.cedricsevestre.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.conf.ApplicationProperties;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.independant.objects.FolderService;
import fr.cedricsevestre.service.engine.translation.LangService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;

@Component
@Scope(value = "singleton")
public class Common {	
	private Map<String, Page> pages;
	
	private Map<String, Folder> foldersByName;
	private Map<String, Folder> foldersByServerName;
	
	private Map<String, Lang> langs;
		
	private String applicationFolder;
	private String webInfFolder;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private PageService pageService;

	@Autowired
	protected LangService langService;
	
	@Autowired
	private ApplicationProperties applicationProperties;	

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
	
	public static final String BO_URL = "bo/";
	public static final String BO_FILE_URL = "file/";
	
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
	
	public Lang getLang(String langCode) throws ServiceException {
		if (langs == null){
			langs = new HashMap<>();
		}
		if(!langs.containsKey(langCode)){
			Lang lang = langService.findByCode(langCode);
			if (lang != null) langs.put(langCode, lang);
		}
		return langs.get(langCode);
	}

	public Page getPage(Folder folder, String pageName, Lang lang) throws ServiceException {
		String pageNameLong = (folder.getName() + "_" + pageName + "_" + lang.getCode()).toUpperCase();
		if (pages == null){
			pages = new HashMap<>();
		}
		if(!pages.containsKey(pageNameLong)){
			Page page = pageService.identify(folder.getId(), pageName, lang.getId());
			if (page != null) pages.put(pageNameLong, page);
		}
		return pages.get(pageNameLong);
	}
	
	public String getBasePath(Boolean webInf, Folder folder, TypeBase typeBase){
		return (webInf ? BASE_WEBINF : "") + 
			   (folder != null ? folder.getPath() : "") + 
			   (typeBase.toString());
	}
	
	
	
	
	
	
	
	
		
	public String getApplicationFolder() {
		if (applicationFolder == null) 
			applicationFolder = applicationProperties.getApplicationFolder();
		return applicationFolder;
	}


	
}
