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
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;

@Component
@Scope(value = "singleton")
public class Body extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Body.class);
	
	private static Common common;
	@Autowired
	public void Common(Common common) {
		this.common = common;
	}
	
	private static final String BLOCKPREVIEW = "blockPreview";
	private static final String FOLDER = "folder";
	private static final String SURFER = "surfer";
	private static final String PAGE = "page";
	private static final String ACTIVEOBJECT = "activeObject";
	private static final String NENGINESCRIPT = "NEngineScript";
	
	public int doStartTag() {
		logger.debug("Enter in doStartTag()");
		JspWriter out = pageContext.getOut();
		try {			
			Boolean blockPreview = (Boolean) pageContext.getAttribute(BLOCKPREVIEW, PageContext.REQUEST_SCOPE);
			if (blockPreview){
				User surfer = (User) pageContext.getAttribute(SURFER, PageContext.REQUEST_SCOPE);
				if (surfer.getRole().equals(User.ROLE_ADMIN)){
					Page page = (Page) pageContext.getAttribute(PAGE, PageContext.REQUEST_SCOPE);
					Translation activeObject = (Translation) pageContext.getAttribute(ACTIVEOBJECT, PageContext.REQUEST_SCOPE);
					String initObjects = "'" + page.getName() + "'";
					if (activeObject != null) initObjects += "," + activeObject.getId();
					
					out.println("<body data-ng-app=\"backApp\" data-ng-controller=\"BlockManagementCtrl\" data-ng-init=\"init(" + initObjects + ")\">");
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
			Folder folder = (Folder) pageContext.getAttribute(FOLDER, PageContext.REQUEST_SCOPE);
			pageContext.include(common.getBasePath(true, folder, TypeBase.COMMON) + "components/scripts.jsp");
			String engineScript = (String) pageContext.getAttribute(NENGINESCRIPT, PageContext.REQUEST_SCOPE); 
			if (engineScript != null){
				out.println(engineScript);		
			}
			
			User surfer = (User) pageContext.getAttribute(SURFER, PageContext.REQUEST_SCOPE);
			if (surfer.getRole().equals(User.ROLE_ADMIN)){
				String adminPath = common.getBasePath(true, null, TypeBase.ADMIN);
				pageContext.include(adminPath + "components/blockPreview.jsp");
				Boolean blockPreview = (Boolean) pageContext.getAttribute(BLOCKPREVIEW, PageContext.REQUEST_SCOPE);
				if (blockPreview){
					pageContext.include(adminPath + "components/backManagement.jsp");
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