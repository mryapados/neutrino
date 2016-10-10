package fr.cedricsevestre.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.Position;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.independant.objects.PositionService;
import fr.cedricsevestre.taglib.IIncludeJSP.Attributes;

@Component
@Scope(value = "singleton")
public class HasBlock extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(HasBlock.class);

	private String position = null;
	private String var = null;
	private String scope = null;
	
	private static PositionService positionService;
	@Autowired
	public void PositionService(PositionService positionService) {
		HasBlock.positionService = positionService;
	}
	
	public int doStartTag() {
		logger.debug("Enter in doStartTag()");
		JspWriter out = pageContext.getOut();
		try {
			Integer contextScope = PageContext.PAGE_SCOPE;
			if (scope != null){
				switch (scope.toUpperCase()) {
					case "SESSION":
						contextScope = PageContext.SESSION_SCOPE;
						break;
					case "REQUEST":
						contextScope = PageContext.REQUEST_SCOPE;
						break;
					case "APPLICATION":
						contextScope = PageContext.APPLICATION_SCOPE;
						break;
					default:
						contextScope = PageContext.PAGE_SCOPE;
						break;
					}
			}
			
			
			Folder folder = (Folder) pageContext.getAttribute(Attributes.FOLDER.toString(), PageContext.REQUEST_SCOPE);
			List<Translation> models = new ArrayList<>();
			Translation model = (Template) pageContext.getAttribute(Attributes.PARENTPAGEBLOCK.toString(), PageContext.REQUEST_SCOPE);
			Page page = (Page) pageContext.getAttribute(Attributes.PAGE.toString(), PageContext.REQUEST_SCOPE);
			if (model == null) model = page.getModel();
			models.add(model);
			
			Translation activeObject = (Translation) pageContext.getAttribute(Attributes.ACTIVEOBJECT.toString(), PageContext.REQUEST_SCOPE);
			if (activeObject != null){
				models.add(activeObject);
			}
			
			System.out.println("GGGGGGGGGGGGGGGGGGGGGGGGGGG");
			System.out.println(position);
			System.out.println(model.getId());
			Integer count = positionService.countByNameForModelsWithMaps(models, position);
			
			pageContext.setAttribute(var, count > 0, contextScope);

		} catch (ServiceException e) {
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		} 
		
		return SKIP_BODY;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}

}