package fr.cedricsevestre.taglib;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.common.Common.TypeBase;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.User;

@Component
@Scope(value = "singleton")
public class Head extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Head.class);
	
	private static final String FOLDER = "folder";
	private static final String SURFER = "surfer";
	private static final String APPLICATIONFOLDER = "applicationFolder";
	
	private static Common common;
	@Autowired
	public void Common(Common common) {
		Head.common = common;
	}
	
	public int doStartTag() {
		logger.debug("Enter in doStartTag()");
		JspWriter out = pageContext.getOut();
		try {
			out.println("<head>");
		} catch (IOException e) {
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		return EVAL_BODY_INCLUDE;
	}

	public int doEndTag() {
		logger.debug("Enter in doEndTag()");
		JspWriter out = pageContext.getOut();
		try {
			Folder folder = (Folder) pageContext.getAttribute(FOLDER, PageContext.REQUEST_SCOPE);
			pageContext.include(common.getBasePath(true, folder, TypeBase.COMMON) + "components/css.jsp");
			User surfer = (User) pageContext.getAttribute(SURFER, PageContext.REQUEST_SCOPE);
			if (surfer.getRole().equals(User.ROLE_ADMIN)){
				//TODO change applicationFolder to c:url
				String applicationFolder = (String) pageContext.getAttribute(APPLICATIONFOLDER, PageContext.REQUEST_SCOPE);
				out.println("<link href=\"" + applicationFolder + "/style/app.css\" rel=\"stylesheet\">");
			} 
			out.println("</head>");
		} catch (IOException | ServletException e) {
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		return EVAL_BODY_INCLUDE;
	}
	

	
}