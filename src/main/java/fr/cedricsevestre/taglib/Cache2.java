package fr.cedricsevestre.taglib;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.exception.TagException;

@Component
@Scope(value = "singleton")
public class Cache2 extends BodyTagSupport  {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Cache2.class);
		
	private static Common common;
	@Autowired
	public void Common(Common common) {
		System.out.println("enter in Cache Common");
		Cache2.common = common;
	}

	private String key = null;
	private String lang = null;
	
	public int doStartTag() {
		System.out.println("enter in Cache doStartTag");
		
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
		System.out.println("hashCode = " + hashCode);
		
		String path = common.getWebInfFolder() + "cache/" + hashCode;
		try {
			String content = new String(Files.readAllBytes(Paths.get(path)));
			JspWriter out = pageContext.getOut();
			out.print(content);
			return SKIP_BODY;
		} catch (NoSuchFileException e) {
			return EVAL_BODY_AGAIN;
		} catch (IOException e) {
			throw new TagException(e.getMessage(), e);
		}
	}	
	
	public int doAfterBody() {
		System.out.println("enter in Cache doAfterBody");


		
		BodyContent bodyContent = getBodyContent();
		if (bodyContent != null){
			System.out.println("ICI");
			System.out.println(bodyContent.getString());
			
			//bodyContent.clearBody();
			
		}
		
		

		
		
		
		return SKIP_BODY;
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