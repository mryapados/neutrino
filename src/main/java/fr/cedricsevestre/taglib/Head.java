package fr.cedricsevestre.taglib;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.User;

@Component
@Scope(value = "singleton")
public class Head extends TagSupport {
	
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Head.class);

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
			pageContext.include(Common.BASEPAGESPATH + "common/components/css.jsp");
			User surfer = (User) pageContext.getAttribute("surfer", PageContext.REQUEST_SCOPE);
			if (surfer.getRole().equals(User.ROLE_ADMIN)){
				String applicationFolder = (String) pageContext.getAttribute("applicationFolder", PageContext.REQUEST_SCOPE);
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