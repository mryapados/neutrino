package fr.cedricsevestre.com.utils;

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
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.exception.UtilException;
import fr.cedricsevestre.service.engine.independant.objects.FolderService;
import fr.cedricsevestre.service.engine.translation.LangService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;

@Component
@Scope(value = "singleton")
public class CommonUtil {	
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
	
	public static final String BO_URL = "@bo/";
	public static final String BO_FILE_URL = "file/";
	
	@Deprecated
	public static final String CUSTOM_ENTITY_PACKAGE = "fr.cedricsevestre.entity.custom";
	
	@Deprecated
	public static final String CUSTOM_SERVICE_PACKAGE = "fr.cedricsevestre.service.custom";
	
	
	public void init(){
		foldersByName = new HashMap<>();
		foldersByServerName = new HashMap<>();
		pages = new HashMap<>();
		langs = new HashMap<>();
	}
	
	public String getWebInfFolder() {
		if (webInfFolder == null) {
			webInfFolder = servletContext.getRealPath(BASE_WEBINF);
		}
		return webInfFolder;
	}
	
	private void addFolder(String serverName) throws UtilException {
		try {
			Folder folder = folderService.findByServerName(serverName);
			String folderName = folder.getName();
			if (foldersByName.containsKey(folderName)) {
				folder = foldersByName.get(folderName);
			} else {
				foldersByName.put(folderName, folder);
			}
			foldersByServerName.put(serverName, folder);
		} catch (ServiceException e) {
			throw new UtilException(e);
		}
	}

	public Folder getFolder(String serverName) throws UtilException, ResourceNotFoundException {
		if (foldersByName == null) {
			foldersByName = new HashMap<>();
			foldersByServerName = new HashMap<>();
		}
		if (!foldersByServerName.containsKey(serverName)) {
			addFolder(serverName);
		}
		Folder folder = foldersByServerName.get(serverName);
		if (folder == null) throw new ResourceNotFoundException("Folder not found from servername " + serverName + " !");
		return folder;
	}

	public Lang getLang(String langCode) throws UtilException, ResourceNotFoundException {
		try {
			if (langs == null) {
				langs = new HashMap<>();
			}
			if (!langs.containsKey(langCode)) {
				Lang lang = langService.findByCode(langCode);
				if (lang != null)
					langs.put(langCode, lang);
			}
			Lang lang = langs.get(langCode);
			if (lang == null) throw new ResourceNotFoundException("lang not found from code " + langCode + " !");
			return lang;
		} catch (ServiceException e) {
			throw new UtilException(e);
		}
	}

	public Page getPage(Folder folder, String pageName, Lang lang) throws UtilException, ResourceNotFoundException {
		try {
			String pageNameLong = (folder.getName() + "_" + pageName + "_" + lang.getCode()).toUpperCase();
			if (pages == null) {
				pages = new HashMap<>();
			}
			if (!pages.containsKey(pageNameLong)) {
				Page page = pageService.identify(folder, pageName, lang);
				if (page != null)
					pages.put(pageNameLong, page);
			}
			Page page = pages.get(pageNameLong);
			if (page == null) {
				String folderString = "";
				if (folder != null) folderString = " folder = " + folder.getName();

				String langString = "";
				if (lang != null) langString = " lang = " + lang.getName();
				throw new ResourceNotFoundException("Page " + pageName + " not found ! " + folderString + " " + langString);
			}
			return page;
		} catch (ServiceException e) {
			throw new UtilException(e);
		}
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
