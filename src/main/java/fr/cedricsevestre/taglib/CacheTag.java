package fr.cedricsevestre.taglib;

import java.io.Serializable;

public class CacheTag extends CacheSupport implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
}
//public class CacheTag extends BodyTagSupport implements Serializable {
//
//	private static final long serialVersionUID = 1L;
//	private Logger logger = Logger.getLogger(CacheSupport.class);
//
//	protected String key = null;
//
//	private CommonUtil commonUtil;
//	private CacheService cacheService;
//
//	private int hashCode = 0;
//	private String pathDir = null;
//	private String pathFile = null;
//	private String content = null;
//	private boolean done = false;
//
//	private void init() {
//		commonUtil = (CommonUtil) pageContext.getAttribute(AttributeConst.COMMON_UTIL_BEAN, PageContext.APPLICATION_SCOPE);
//		cacheService = (CacheService) pageContext.getAttribute(AttributeConst.CACHE_SERVICE_BEAN, PageContext.APPLICATION_SCOPE);
//
//		done = false;
//		mkHashCode();
//		pathDir = commonUtil.getWebInfFolder() + "cache/";
//		pathFile = pathDir + hashCode;
//	}
//
//	@Override
//	public void doInitBody() throws JspException {
//
//	}
//
//	@Override
//	public int doStartTag() throws JspException {
//		logger.debug("Enter in doStartTag()");
//		try {
//			init();
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
//			if (!done) {
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
//	private void mkHashCode() {
//		if (key != null) {
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
//
//	private void contentFromCache() throws JspException {
//		logger.debug("Enter in contentFromCache");
//		try {
//			content = cacheService.getContentFromCache(pathFile);
//		} catch (IOException e) {
//			throw new JspTagException(e);
//		}
//	}
//
//	private void contentAndCache() throws JspException {
//		content = getBodyContent().getString();
//		mkCachedFile();
//	}
//
//	private void mkCachedFile() throws JspException {
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