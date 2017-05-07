package fr.cedricsevestre.service.engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;

import org.apache.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.constants.CacheConst;

@Service
public class CacheService{

	private Logger logger = Logger.getLogger(CacheService.class);

	@Cacheable(value = CacheConst.JSP, unless = "#result == null")
	public String getContentFromCache(String pathFile) throws IOException {
		logger.debug("Enter in getContentFromCache");
		try {
			return new String(Files.readAllBytes(Paths.get(pathFile)));
		} catch (NoSuchFileException e) {
			return null;
		}
	}
	
	public void mkCachedFile(String pathDir, String pathFile, String Content) throws IOException{
		logger.debug("Enter in mkCachedFile");
		File file = new File(pathDir);
		file.mkdirs();

		file = new File(pathFile);
		
		file.createNewFile();
		FileWriter fw = new FileWriter(file);

		fw.write(Content);
		fw.flush();

		fw.close();
	}

}
