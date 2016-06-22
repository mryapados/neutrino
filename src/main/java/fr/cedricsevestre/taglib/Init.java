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

@Component
@Scope(value = "singleton")
public class Init extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Init.class);

	private Boolean test = null;
	
	public int doStartTag() {
		if (test){
			JspWriter out = pageContext.getOut();
			try {
				pageContext.include(Common.BASEADMINPATH + "components/init.jsp");
				pageContext.setAttribute("initialized", true, PageContext.REQUEST_SCOPE);
			} catch (IOException | ServletException e) {
				try {
					out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
				} catch (IOException ex) {
					logger.error("Erreur Block " + ex.getMessage());
					ex.printStackTrace();
				}
			}
		}
		return EVAL_BODY_INCLUDE;
	}

	public Boolean getTest() {
		return test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}
	
}