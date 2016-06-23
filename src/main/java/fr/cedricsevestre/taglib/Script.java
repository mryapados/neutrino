package fr.cedricsevestre.taglib;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class Script extends BodyTagSupport  {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Script.class);
		
	private String src = null;
	
	public int doStartTag() {
		logger.debug("Enter in doStartTag()");
		if (src != null){
			String engineScript = (String) pageContext.getAttribute("NEngineScript", PageContext.REQUEST_SCOPE);
			if (engineScript == null) engineScript = "";
			String newLine = System.getProperty("line.separator");
			engineScript += newLine + "<script src=\"" + src + "\"></script>";
			pageContext.setAttribute("NEngineScript", engineScript, PageContext.REQUEST_SCOPE);
			return SKIP_BODY;
		}
		return EVAL_BODY_AGAIN;
	}
	
	public int doAfterBody() {
		logger.debug("Enter in doAfterBody()");
		String engineScript = (String) pageContext.getAttribute("NEngineScript", PageContext.REQUEST_SCOPE);
		if (engineScript == null) engineScript = "";
		String newLine = System.getProperty("line.separator");
		BodyContent bodyContent = getBodyContent();
		engineScript += newLine + "<script type=\"text/javascript\">" + bodyContent.getString() + "</script>";
		pageContext.setAttribute("NEngineScript", engineScript, PageContext.REQUEST_SCOPE);
		return SKIP_BODY;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	
	
	
	

}