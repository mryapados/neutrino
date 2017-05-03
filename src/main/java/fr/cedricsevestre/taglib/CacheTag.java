package fr.cedricsevestre.taglib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.exception.TagException;

@Component
@Scope(value = "singleton")
public class CacheTag extends SimpleTagSupport implements Serializable  {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(CacheTag.class);
		
	private static CommonUtil common;
	@Autowired
	public void Common(CommonUtil common) {
		CacheTag.common = common;
	}

	private String key = null;
	private String lang = null;
	
	protected PageContext pageContext;
	
	public void doTag() throws JspException {	
		logger.debug("Enter in doTag()");
				
		pageContext = (PageContext) getJspContext();
		
		int hashCode = mkHashCode();
		
		String pathDir = common.getWebInfFolder() + "cache/";
		String pathFile = pathDir + hashCode;
		
		System.out.println("pathFile = " + pathFile);

		if (!contentFromCache(pathFile)){
			contentFromBodyTag(pathDir, pathFile);
		}
		
	}	
	
	private int mkHashCode(){
		int hashCode = 0;
		if (key != null){
			hashCode = key.hashCode();
		} else {
			String pageClass = pageContext.getPage().getClass().toString();
			System.out.println("pageClass = " + pageClass);

			final int prime = 31;
			hashCode = 1;
			hashCode = prime * hashCode + ((pageClass == null) ? 0 : pageClass.hashCode());
			hashCode = prime * hashCode + ((lang == null) ? 0 : lang.hashCode());
		}
		return hashCode;
	}
	private Boolean contentFromCache(String pathFile){
		logger.debug("Enter in contentFromCache()");
		JspWriter out = pageContext.getOut();
		try {
			String content = new String(Files.readAllBytes(Paths.get(pathFile)));
			out.print(content);
			return true;
		} catch (NoSuchFileException e) {
			return false;
		} catch (IOException e) {
			throw new TagException(e.getMessage(), e);
		}
	}
	private void contentFromBodyTag(String pathDir, String pathFile){
		logger.debug("Enter in contentFromBodyTag()");
		File file = new File(pathDir);
		file.mkdirs();
		
		file = new File(pathFile);
		try {
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			
			StringWriter sw = new StringWriter();
			getJspBody().invoke(sw);

			fw.write(sw.toString());
			fw.flush();
			
			JspWriter out = pageContext.getOut();
			out.print(sw);

			fw.close();
		} catch (IOException e) {
			//TODO
			e.printStackTrace();
		} catch (JspException e) {
			//TODO
			e.printStackTrace();
		}
	}
	

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}


}