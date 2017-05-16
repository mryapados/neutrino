package fr.cedricsevestre.service.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.conf.ApplicationProperties;
import fr.cedricsevestre.constants.CacheConst;

@Service
public class CacheService{

	private Logger logger = Logger.getLogger(CacheService.class);

	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Autowired
	protected CommonUtil common;
	
	
    private Path rootLocation;

    @PostConstruct
    private void initialize(){
    	this.rootLocation = Paths.get(common.getWebInfFolder() + applicationProperties.getCacheDir());
    }
    
	@Cacheable(value = CacheConst.JSP, unless = "#result == null")
	public String getContentFromCache(int hashCode) throws IOException {
		if (!applicationProperties.getJspCache()) return null;
		
		logger.debug("Enter in getContentFromCache");
		try {
			return new String(Files.readAllBytes(Paths.get(rootLocation + "/" + hashCode)));
		} catch (NoSuchFileException e) {
			return null;
		}
	}
	
	public void mkCachedFile(int hashCode, String Content) throws IOException{
		if (!applicationProperties.getJspCache()) return;
		
		logger.debug("Enter in mkCachedFile");
		
		File file = new File(rootLocation  + "/");
		file.mkdirs();

		file = new File(file, Integer.toString(hashCode));
		
		file.createNewFile();
		FileWriter fw = new FileWriter(file);

		fw.write(Content);
		fw.flush();

		fw.close();
	}

}
