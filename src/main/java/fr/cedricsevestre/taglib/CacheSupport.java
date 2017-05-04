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
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.constants.AttributeConst;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.exception.TagException;

@Component
public class CacheSupport extends SimpleTagSupport implements ICacheSupport, Serializable  {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(CacheSupport.class);
		
	private static CommonUtil common;
	@Autowired
	public void Common(CommonUtil common) {
		CacheSupport.common = common;
	}

	protected String key = null;
	protected PageContext pageContext;
	
	public void doTag() throws JspException {	
		logger.debug("Enter in doTag");
		try {
			pageContext = (PageContext) getJspContext();
			JspWriter out = pageContext.getOut();
			int hashCode = mkHashCode();
			out.print(getCachedValue(hashCode));
		} catch (IOException e) {
			throw new JspTagException(e);
		}
	}	
	
	@Override
	public StringWriter getCachedValue(int hashCode) throws JspException, IOException{
		logger.debug("Enter in getCachedValue");
		StringWriter sw = new StringWriter();
		getJspBody().invoke(sw);
		return sw;
	}

	private int mkHashCode(){
		int hashCode = 0;
		if (key != null){
			hashCode = key.hashCode();
		} else {
			String pageClass = pageContext.getPage().getClass().toString();
			System.out.println("pageClass = " + pageClass);
			
			Lang lang = (Lang) pageContext.getAttribute(AttributeConst.ACTIVELANG, PageContext.REQUEST_SCOPE);
			
			final int prime = 31;
			hashCode = 1;
			hashCode = prime * hashCode + ((pageClass == null) ? 0 : pageClass.hashCode());
			hashCode = prime * hashCode + ((lang == null) ? 0 : lang.hashCode());
		}
		return hashCode;
	}

	public void setKey(String key) {
		this.key = key;
	}
}