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
import fr.cedricsevestre.entity.engine.Page;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.entity.engine.User;

@Component
@Scope(value = "singleton")
public class Body extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Body.class);

	public int doStartTag() {
		logger.debug("Enter in doStartTag()");
		JspWriter out = pageContext.getOut();
		try {			
			Boolean blockPreview = (Boolean) pageContext.getAttribute("blockPreview", PageContext.REQUEST_SCOPE);
			if (blockPreview){
				User surfer = (User) pageContext.getAttribute("surfer", PageContext.REQUEST_SCOPE);
				if (surfer.getRole().equals(User.ROLE_ADMIN)){
					Page page = (Page) pageContext.getAttribute("page", PageContext.REQUEST_SCOPE);
					Translation activeObject = (Translation) pageContext.getAttribute("activeObject", PageContext.REQUEST_SCOPE);
					String initObjects = "'" + page.getName() + "'";
					if (activeObject != null) initObjects += "," + activeObject.getId();
					
					out.println("<body data-ng-app=\"backApp\" data-ng-controller=\"BlockManagementCtrl\" data-ng-init=\"init('" + initObjects + "')\">");
				} else {
					out.println("<body data-ng-app=\"frontApp\">");
				}
			} else {
				out.println("<body data-ng-app=\"frontApp\">");
			}
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
			pageContext.include(Common.BASEPAGESPATH + "common/components/scripts.jsp");
			String engineScript = (String) pageContext.getAttribute("NEngineScript", PageContext.REQUEST_SCOPE); 
			if (engineScript != null){
				out.println(engineScript);		
			}
			
			User surfer = (User) pageContext.getAttribute("surfer", PageContext.REQUEST_SCOPE);
			if (surfer.getRole().equals(User.ROLE_ADMIN)){
				pageContext.include(Common.BASEADMINPATH + "components/blockPreview.jsp");
				
				Boolean blockPreview = (Boolean) pageContext.getAttribute("blockPreview", PageContext.REQUEST_SCOPE);
				if (blockPreview){
					pageContext.include(Common.BASEADMINPATH + "components/backManagement.jsp");
				}
			}
			out.println("</body>");
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