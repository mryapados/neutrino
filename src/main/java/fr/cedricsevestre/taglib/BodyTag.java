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

import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.com.utils.IdProviderUtil;
import fr.cedricsevestre.com.utils.CommonUtil.TypeBase;
import fr.cedricsevestre.constants.AttributeConst;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.User;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;

public class BodyTag extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(BodyTag.class);
	
	private CommonUtil commonUtil;
	
	private String id;
	private String cssClass;
	
	private static final String BLOCKPREVIEW = "blockPreview";
	private static final String FOLDER = "folder";
	private static final String SURFER = "surfer";
	private static final String ACTIVEPAGE = "activePage";
	private static final String ACTIVEOBJECT = "activeObject";
	private static final String NENGINESCRIPT = "NEngineScript";
	
	public int doStartTag() {
		logger.debug("Enter in doStartTag()");
		JspWriter out = pageContext.getOut();
		try {
			commonUtil = (CommonUtil) pageContext.getAttribute(AttributeConst.COMMON_UTIL_BEAN, PageContext.APPLICATION_SCOPE);
			
			String bodyCssClass = "";
			if (cssClass != null) bodyCssClass = " class=\"" + cssClass + "\"";
			
			String bodyId = "";
			if (id != null) bodyId = " id=\"" + id + "\"";
			
			Boolean blockPreview = (Boolean) pageContext.getAttribute(BLOCKPREVIEW, PageContext.REQUEST_SCOPE);
			if (blockPreview){
				User surfer = (User) pageContext.getAttribute(SURFER, PageContext.REQUEST_SCOPE);
				if (surfer.getRole().equals(User.ROLE_ADMIN)){
					Folder folder = (Folder) pageContext.getAttribute(FOLDER, PageContext.REQUEST_SCOPE);
					Page page = (Page) pageContext.getAttribute(ACTIVEPAGE, PageContext.REQUEST_SCOPE);
					Translation activeObject = (Translation) pageContext.getAttribute(ACTIVEOBJECT, PageContext.REQUEST_SCOPE);
					String initObjects = folder.getId() + ", " + page.getId();
					if (activeObject != null) initObjects += ", " + activeObject.getId();
					
					out.println("<body" + bodyId + bodyCssClass + " data-ng-app=\"backApp\" data-ng-controller=\"BlockManagementCtrl\" data-ng-init=\"init(" + initObjects + ")\">");
				} else {
					out.println("<body" + bodyId + bodyCssClass + " data-ng-app=\"frontApp\">");
				}
			} else {
				out.println("<body" + bodyId + bodyCssClass + " data-ng-app=\"frontApp\">");
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
			User surfer = (User) pageContext.getAttribute(SURFER, PageContext.REQUEST_SCOPE);
			if (surfer.getRole().equals(User.ROLE_ADMIN)){
				String adminPath = commonUtil.getBasePath(true, null, TypeBase.ADMIN);
				pageContext.include(adminPath + "components/blockPreview.jsp");
				Boolean blockPreview = (Boolean) pageContext.getAttribute(BLOCKPREVIEW, PageContext.REQUEST_SCOPE);
				if (blockPreview){
					pageContext.include(adminPath + "components/backManagement.jsp");
				}
			}
			
			Folder folder = (Folder) pageContext.getAttribute(FOLDER, PageContext.REQUEST_SCOPE);
			pageContext.include(commonUtil.getBasePath(true, folder, TypeBase.COMMON) + "components/scripts.jsp");
			String engineScript = (String) pageContext.getAttribute(NENGINESCRIPT, PageContext.REQUEST_SCOPE); 
			if (engineScript != null){
				out.println(engineScript);		
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
		return EVAL_BODY_AGAIN;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCssClass() {
		return cssClass;
	}
	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
}