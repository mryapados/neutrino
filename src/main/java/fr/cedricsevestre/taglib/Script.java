package fr.cedricsevestre.taglib;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

public class Script implements BodyTag {
	private PageContext pageContext = null;
	private Tag parent = null;
	private BodyContent bodyContent = null;
	private String src;

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	@Override
	public void doInitBody() throws JspException {

	}

	@Override
	public void setBodyContent(BodyContent arg0) {
		bodyContent = arg0;

	}

	@Override
	public int doAfterBody() {
		try {
			String bodyString = bodyContent.getString();
			JspWriter out = bodyContent.getEnclosingWriter();
			
			
//			String engineScript = (String) pageContext.getAttribute("NEngineScript", PageContext.REQUEST_SCOPE);
//			String newLine = System.getProperty("line.separator");
//			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA src = " + src);
//			if (src != null){
//				System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA src = " + src);
//				engineScript += newLine + "<script src=\"" + src + "\"></script>";
//			} else {
//				
//				engineScript += "<script type=\"text/javascript\">" + newLine + bodyString + "</script>";
//				pageContext.setAttribute("NEngineScript", engineScript, PageContext.REQUEST_SCOPE);
//
//			}
//			pageContext.setAttribute("NEngineScript", engineScript, PageContext.REQUEST_SCOPE);
//			
			
			String engineScript = (String) pageContext.getAttribute("NEngineScript", PageContext.REQUEST_SCOPE);
			String newLine = System.getProperty("line.separator");
			SimpleDateFormat frm = new SimpleDateFormat("dd-MMM-yyy EEEE");
			if(src != null)
			{
				System.out.println("ici");
//				out.print((bodyString + frm.format(new Date())).toUpperCase());
				engineScript += newLine + "<script src=\"" + src + "\"></script>";
			}else{

			}
			pageContext.setAttribute("NEngineScript", engineScript, PageContext.REQUEST_SCOPE);
			
			
			bodyContent.clear(); // empty buffer for next evaluation
		} catch (IOException e) {
			System.out.println("Error in BodyContentTag.doAfterBody()" + e.getMessage());
			e.printStackTrace();
		} // end of catch

		return SKIP_BODY;
	}

	@Override
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		return EVAL_BODY_AGAIN;
	}

	@Override
	public Tag getParent() {
		return parent;
	}

	@Override
	public void release() {
		pageContext = null;
		parent = null;

	}

	@Override
	public void setPageContext(PageContext arg0) {
		pageContext = arg0;
	}

	@Override
	public void setParent(Tag arg0) {
		parent = arg0;
	}

}



//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.jsp.JspWriter;
//import javax.servlet.jsp.PageContext;
//import javax.servlet.jsp.tagext.BodyContent;
//import javax.servlet.jsp.tagext.BodyTagSupport;
//import javax.servlet.jsp.tagext.TagSupport;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Component;
//
//import fr.cedricsevestre.common.Common;
//import fr.cedricsevestre.entity.engine.MapTemplate;
//import fr.cedricsevestre.entity.engine.NData;
//import fr.cedricsevestre.entity.engine.NSchema;
//import fr.cedricsevestre.entity.engine.Page;
//import fr.cedricsevestre.entity.engine.Position;
//import fr.cedricsevestre.entity.engine.Template;
//import fr.cedricsevestre.entity.engine.User;
//import fr.cedricsevestre.entity.engine.NSchema.ScopeType;
//import fr.cedricsevestre.entity.engine.Template.TemplateType;
//import fr.cedricsevestre.exception.ServiceException;
//import fr.cedricsevestre.service.engine.NDataService;
//import fr.cedricsevestre.service.engine.PositionService;
//import fr.cedricsevestre.service.engine.TemplateService;
//
//@Component
//@Scope(value = "singleton")
//public class Script extends BodyTagSupport  {
//
//	private static final long serialVersionUID = 1L;
//	private Logger logger = Logger.getLogger(Script.class);
//		
//	private String src = null;
//
//	
//	
//	public int doStartTag() {
//		System.out.println("Enter in doStartTag");
//		String engineScript = (String) pageContext.getAttribute("NEngineScript", PageContext.REQUEST_SCOPE);
//		String newLine = System.getProperty("line.separator");
//		if (src != null){
//			System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAA src = " + src);
//			engineScript += newLine + "<script src=\"" + src + "\"></script>";
//		}
//		pageContext.setAttribute("NEngineScript", engineScript, PageContext.REQUEST_SCOPE);
//		return SKIP_BODY;
//	}
//	
//	public int doAfterBody() {
//		System.out.println("Enter in doAfterBody");
//		String engineScript = (String) pageContext.getAttribute("NEngineScript", PageContext.REQUEST_SCOPE);
//		String newLine = System.getProperty("line.separator");
//		BodyContent bodyContent = getBodyContent();
//		engineScript += "<script type=\"text/javascript\">" + newLine + bodyContent.getString() + "</script>";
//		pageContext.setAttribute("NEngineScript", engineScript, PageContext.REQUEST_SCOPE);
//		return SKIP_BODY;
//	}
//
//	public String getSrc() {
//		return src;
//	}
//
//	public void setSrc(String src) {
//		this.src = src;
//	}
//
//	
//	
//	
//	
//
//}