package fr.cedricsevestre.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class Cache extends BodyTagSupport  {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Cache.class);
		
	private String key = null;
	private String lang = null;
	
	public int doStartTag() {
		System.out.println("enter in Cache doStartTag");
//		JspWriter out = pageContext.getOut();
//		String path = "cache/" + hashcode;
//		if fileExist(path){
//			String html = getfile(path);
//			out.print(html);
//			return SKIP_BODY;
//		} else{
			return EVAL_BODY_INCLUDE;
//		}
	}
	
	public int doAfterBody() {
		System.out.println("enter in Cache doAfterBody");
		BodyContent bodyContent = getBodyContent();
		if (bodyContent != null){
			System.out.println(bodyContent.getString());
			
			bodyContent.clearBody();
		}
		
		
		//
		
//		JspWriter out = pageContext.getOut();
//		try {
//			out.print(bodyContent.getString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//set string in file "cache/" + hashcode
		
		
		
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