package fr.cedricsevestre.taglib;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.back.MapTemplate;
import fr.cedricsevestre.entity.back.Page;
import fr.cedricsevestre.entity.back.Position;
import fr.cedricsevestre.entity.back.Template;
import fr.cedricsevestre.entity.front.User;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.back.PositionService;
import fr.cedricsevestre.service.back.TemplateService;

@Component
@Scope(value = "singleton")
public class Body extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Body.class);

	public int doStartTag() {
		JspWriter out = pageContext.getOut();
		try {			
			Boolean blockPreview = (Boolean) pageContext.getAttribute("blockPreview", PageContext.REQUEST_SCOPE);
			if (blockPreview){
				User surfer = (User) pageContext.getAttribute("surfer", PageContext.REQUEST_SCOPE);
				if (surfer.getRole().equals(User.ROLE_ADMIN)){
					Page page = (Page) pageContext.getAttribute("page", PageContext.REQUEST_SCOPE);
					out.println("<body data-ng-app=\"backApp\" data-ng-controller=\"BlockManagementCtrl\" data-ng-init=\"init('" + page.getName() + "')\">");
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
		JspWriter out = pageContext.getOut();
		try {
			pageContext.include(Common.BASEPAGESPATH + "common/components/scripts.jsp");
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