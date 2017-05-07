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
import fr.cedricsevestre.service.engine.CacheService;

//@Component
//public class CacheSupport extends BodyTagSupport implements Serializable  {
//
//	private static final long serialVersionUID = 1L;
//	private Logger logger = Logger.getLogger(CacheSupport.class);
//		
//	private static CommonUtil common;
//	@Autowired
//	public void Common(CommonUtil common) {
//		CacheSupport.common = common;
//	}
//	
//	private static CacheService cacheService;
//	@Autowired
//	public void CacheService(CacheService cacheService) {
//		CacheSupport.cacheService = cacheService;
//	}
//
//	protected String key = null;
//	
//	private int hashCode = 0;
//	private String pathDir = null;
//	private String pathFile = null;
//	private String content = null;
//	private boolean done = false;
//	
//	@Override
//    public void doInitBody() throws JspException {
//		
//    }
//	
//	@Override
//	public int doStartTag() throws JspException {
//		logger.debug("Enter in doStartTag()");
//		try {
//			done = false;
//			mkHashCode();
//			pathDir = common.getWebInfFolder() + "cache/";
//			pathFile = pathDir + hashCode;
//			contentFromCache();
//			if (content != null) {
//				pageContext.getOut().print(content);
//				done = true;
//				return SKIP_BODY;
//			}
//			return EVAL_BODY_BUFFERED;
//		} catch (IOException e) {
//			throw new JspTagException(e);
//		}
//	}
//	
//	@Override
//	public int doEndTag() throws JspException {
//		logger.debug("Enter in doEndTag()");
//		try {
//			if (!done){
//				contentAndCache();
//				pageContext.getOut().print(content);
//			}
//			return super.doEndTag();
//		} catch (IOException e) {
//			throw new JspTagException(e);
//		}
//		
//	}
//
//	
//	private void mkHashCode(){
//		if (key != null){
//			hashCode = key.hashCode();
//		} else {
//			String pageClass = pageContext.getPage().getClass().toString();
//			Lang lang = (Lang) pageContext.getAttribute(AttributeConst.ACTIVELANG, PageContext.REQUEST_SCOPE);
//			
//			final int prime = 31;
//			hashCode = 1;
//			hashCode = prime * hashCode + ((pageClass == null) ? 0 : pageClass.hashCode());
//			hashCode = prime * hashCode + ((lang == null) ? 0 : lang.hashCode());
//		}
//	}
//	private void contentFromCache() throws JspException {
//		logger.debug("Enter in contentFromCache");
//		try {
//			content = cacheService.getContentFromCache(pathFile);
//		} catch (IOException e) {
//			throw new JspTagException(e);
//		}
//	}
//	private void contentAndCache() throws JspException {
//		content = getBodyContent().getString();
//		mkCachedFile();
//	}
//	private void mkCachedFile() throws JspException{
//		logger.debug("Enter in contentFromBodyTag");
//		try {
//			cacheService.mkCachedFile(pathDir, pathFile, content);
//		} catch (IOException e) {
//			throw new JspTagException(e);
//		}
//	}
//	
//	public void setKey(String key) {
//		this.key = key;
//	}
//}















@Component
public class CacheSupport extends SimpleTagSupport implements Serializable  {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(CacheSupport.class);
		
	private static CommonUtil common;
	@Autowired
	public void Common(CommonUtil common) {
		CacheSupport.common = common;
	}
	
	private static CacheService cacheService;
	@Autowired
	public void CacheService(CacheService cacheService) {
		CacheSupport.cacheService = cacheService;
	}

	protected String key = null;
	protected PageContext pageContext;
	protected JspFragment jspBody;
	
	
	@Override
	public void doTag() throws JspException {
		logger.debug("Enter in doTag()");
		try {
			pageContext = (PageContext) getJspContext();
			jspBody = getJspBody();
			JspWriter out = pageContext.getOut();
			out.print(getContentAndCache(mkHashCode()));
		} catch (IOException e) {
			throw new JspTagException(e);
		}
	}

	private int mkHashCode(){
		int hashCode = 0;
		if (key != null){
			hashCode = key.hashCode();
		} else {
			String pageClass = pageContext.getPage().getClass().toString();
			Lang lang = (Lang) pageContext.getAttribute(AttributeConst.ACTIVELANG, PageContext.REQUEST_SCOPE);
			
			final int prime = 31;
			hashCode = 1;
			hashCode = prime * hashCode + ((pageClass == null) ? 0 : pageClass.hashCode());
			hashCode = prime * hashCode + ((lang == null) ? 0 : lang.hashCode());
		}
		return hashCode;
	}

	
	
	private String getContentAndCache(int hashCode) throws JspException {
		String pathDir = common.getWebInfFolder() + "cache/";
		String pathFile = pathDir + hashCode;
		String content = contentFromCache(pathDir + hashCode);
		if (content == null){
			content = contentFromBodyTag(pathDir, pathFile);
			mkCachedFile(pathDir, pathFile, content);
		}
		return content;
	}
	
	
	private String contentFromCache(String pathFile) throws JspException {
		logger.debug("Enter in contentFromCache");
		try {
			String r = cacheService.getContentFromCache(pathFile);
			return r;
		} catch (IOException e) {
			throw new JspTagException(e);
		}
	}

	private String contentFromBodyTag(String pathDir, String pathFile) throws JspException {
		logger.debug("Enter in contentFromBodyTag");
		try {
			StringWriter sw = new StringWriter();
			jspBody.invoke(sw);
			return sw.toString();
		} catch (IOException e) {
			throw new JspTagException(e);
		}
	}
	
	private void mkCachedFile(String pathDir, String pathFile, String Content) throws JspException{
		logger.debug("Enter in contentFromBodyTag");
		try {
			cacheService.mkCachedFile(pathDir, pathFile, Content);
		} catch (IOException e) {
			throw new JspTagException(e);
		}
	}

	public void setKey(String key) {
		this.key = key;
	}
}













