package fr.cedricsevestre.taglib;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.constants.AttributeConst;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.service.engine.CacheService;

public class CacheTag extends BodyTagSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(CacheSupport.class);

	protected String key = null;

	private CommonUtil commonUtil;
	private CacheService cacheService;

	private int hashCode = 0;
	private String pathDir = null;
	private String pathFile = null;
	private String content = null;
	private boolean done = false;

	private void init() {
		commonUtil = (CommonUtil) pageContext.getAttribute(AttributeConst.COMMON_UTIL_BEAN, PageContext.APPLICATION_SCOPE);
		cacheService = (CacheService) pageContext.getAttribute(AttributeConst.CACHE_SERVICE_BEAN, PageContext.APPLICATION_SCOPE);

		done = false;
		mkHashCode();
		pathDir = commonUtil.getWebInfFolder() + "cache/";
		pathFile = pathDir + hashCode;
	}

	@Override
	public void doInitBody() throws JspException {

	}

	@Override
	public int doStartTag() throws JspException {
		logger.debug("Enter in doStartTag()");
		try {
			init();
			contentFromCache();
			if (content != null) {
				pageContext.getOut().print(content);
				done = true;
				return SKIP_BODY;
			}
			return EVAL_BODY_BUFFERED;
		} catch (IOException e) {
			throw new JspTagException(e);
		}
	}

	@Override
	public int doEndTag() throws JspException {
		logger.debug("Enter in doEndTag()");
		try {
			if (!done) {
				contentAndCache();
				pageContext.getOut().print(content);
			}
			return super.doEndTag();
		} catch (IOException e) {
			throw new JspTagException(e);
		}

	}

	private void mkHashCode() {
		if (key != null) {
			hashCode = key.hashCode();
		} else {
			String pageClass = pageContext.getPage().getClass().toString();
			Lang lang = (Lang) pageContext.getAttribute(AttributeConst.ACTIVELANG, PageContext.REQUEST_SCOPE);

			final int prime = 31;
			hashCode = 1;
			hashCode = prime * hashCode + ((pageClass == null) ? 0 : pageClass.hashCode());
			hashCode = prime * hashCode + ((lang == null) ? 0 : lang.hashCode());
		}
	}

	private void contentFromCache() throws JspException {
		logger.debug("Enter in contentFromCache");
		try {
			content = cacheService.getContentFromCache(pathFile);
		} catch (IOException e) {
			throw new JspTagException(e);
		}
	}

	private void contentAndCache() throws JspException {
		content = getBodyContent().getString();
		mkCachedFile();
	}

	private void mkCachedFile() throws JspException {
		logger.debug("Enter in contentFromBodyTag");
		try {
			cacheService.mkCachedFile(pathDir, pathFile, content);
		} catch (IOException e) {
			throw new JspTagException(e);
		}
	}

	public void setKey(String key) {
		this.key = key;
	}
}













//public class CacheTag extends SimpleTagSupport implements Serializable  {
//
//	private static final long serialVersionUID = 1L;
//	private Logger logger = Logger.getLogger(CacheSupport.class);
//		
////	private static CommonUtil common;
////	@Autowired
////	public void Common(CommonUtil common) {
////		CacheTag.common = common;
////	}
////	
//	private static CacheService cacheService;
//	@Autowired
//	public void CacheService(CacheService cacheService) {
//		CacheTag.cacheService = cacheService;
//	}
//	
//	@Autowired
//	private  CommonUtil common;
//	
////	@Autowired
////	private  CacheService cacheService;
//	
//	protected String key = null;
//	
//	@Override
//	public void doTag() throws JspException {
//		logger.debug("Enter in doTag()");
//		try {
//			JspContext jspContext = getJspContext();
//			ApplicationProperties applicationProperties = (ApplicationProperties) jspContext.getAttribute("APPLICATION_PROPERTIES_BEAN", PageContext.APPLICATION_SCOPE);
//			jspContext.getOut().print(getContentAndCache(mkHashCode())); 
//		} catch (IOException e) {
//			throw new JspTagException(e);
//		}
//	}
//
//	private int mkHashCode(){
//		int hashCode = 0;
//		if (key != null){
//			hashCode = key.hashCode();
//		} else {
//			JspContext jspContext = getJspContext();
//			
//			String pageClass = ((PageContext) jspContext).getPage().getClass().toString();
//			Lang lang = (Lang) jspContext.getAttribute(AttributeConst.ACTIVELANG, PageContext.REQUEST_SCOPE);
//			
//			final int prime = 31;
//			hashCode = 1;
//			hashCode = prime * hashCode + ((pageClass == null) ? 0 : pageClass.hashCode());
//			hashCode = prime * hashCode + ((lang == null) ? 0 : lang.hashCode());
//		}
//		return hashCode;
//	}
//
//	
//	
//	private String getContentAndCache(int hashCode) throws JspException {
//		String pathDir = common.getWebInfFolder() + "cache/";
//		String pathFile = pathDir + hashCode;
//		String content = contentFromCache(pathDir + hashCode);
//		if (content == null){
//			content = contentFromBodyTag();
//			mkCachedFile(pathDir, pathFile, content);
//		}
//		return content;
//	}
//	
//	
//	private String contentFromCache(String pathFile) throws JspException {
//		logger.debug("Enter in contentFromCache");
//		try {
//			String r = cacheService.getContentFromCache(pathFile);
//			return r;
//		} catch (IOException e) {
//			throw new JspTagException(e);
//		}
//	}
//
//	private String contentFromBodyTag() throws JspException {
//		logger.debug("Enter in contentFromBodyTag");
//		try {
//			StringWriter sw = new StringWriter();
//			getJspBody().invoke(sw);
//			return sw.toString();
//		} catch (IOException e) {
//			throw new JspTagException(e);
//		}
//	}
//	
//	private void mkCachedFile(String pathDir, String pathFile, String Content) throws JspException{
//		logger.debug("Enter in contentFromBodyTag");
//		try {
//			cacheService.mkCachedFile(pathDir, pathFile, Content);
//		} catch (IOException e) {
//			throw new JspTagException(e);
//		}
//	}
//
//	public void setKey(String key) {
//		this.key = key;
//	}
//}






//public class CacheTag extends CacheSupport  {

//	private static final long serialVersionUID = 1L;
//	private Logger logger = Logger.getLogger(CacheTag.class);
//		
//	private static CommonUtil common;
//	@Autowired
//	public void Common(CommonUtil common) {
//		CacheTag.common = common;
//	}
//
//	private String key = null;
//	
//	protected PageContext pageContext;
//	
//	public void doTag() throws JspException {	
//		logger.debug("Enter in doTag()");
//				
//		pageContext = (PageContext) getJspContext();
//		
//		int hashCode = mkHashCode();
//		
//		String pathDir = common.getWebInfFolder() + "cache/";
//		String pathFile = pathDir + hashCode;
//		
//		System.out.println("pathFile = " + pathFile);
//
//		if (!contentFromCache(pathFile)){
//			contentFromBodyTag(pathDir, pathFile);
//		}
//		
//	}	
//	
//	private int mkHashCode(){
//		int hashCode = 0;
//		if (key != null){
//			hashCode = key.hashCode();
//		} else {
//			String pageClass = pageContext.getPage().getClass().toString();
//			System.out.println("pageClass = " + pageClass);
//
//			final int prime = 31;
//			hashCode = 1;
//			hashCode = prime * hashCode + ((pageClass == null) ? 0 : pageClass.hashCode());
//			hashCode = prime * hashCode + ((lang == null) ? 0 : lang.hashCode());
//		}
//		return hashCode;
//	}
//	private Boolean contentFromCache(String pathFile){
//		logger.debug("Enter in contentFromCache()");
//		JspWriter out = pageContext.getOut();
//		try {
//			String content = new String(Files.readAllBytes(Paths.get(pathFile)));
//			out.print(content);
//			return true;
//		} catch (NoSuchFileException e) {
//			return false;
//		} catch (IOException e) {
//			throw new TagException(e.getMessage(), e);
//		}
//	}
//	private void contentFromBodyTag(String pathDir, String pathFile){
//		logger.debug("Enter in contentFromBodyTag()");
//		File file = new File(pathDir);
//		file.mkdirs();
//		
//		file = new File(pathFile);
//		try {
//			file.createNewFile();
//			FileWriter fw = new FileWriter(file);
//			
//			StringWriter sw = new StringWriter();
//			getJspBody().invoke(sw);
//
//			fw.write(sw.toString());
//			fw.flush();
//			
//			JspWriter out = pageContext.getOut();
//			out.print(sw);
//
//			fw.close();
//		} catch (IOException e) {
//			//TODO
//			e.printStackTrace();
//		} catch (JspException e) {
//			//TODO
//			e.printStackTrace();
//		}
//	}
	


//}