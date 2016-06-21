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

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.MapTemplate;
import fr.cedricsevestre.entity.engine.NData;
import fr.cedricsevestre.entity.engine.NSchema;
import fr.cedricsevestre.entity.engine.Page;
import fr.cedricsevestre.entity.engine.Position;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.entity.engine.User;
import fr.cedricsevestre.entity.engine.NSchema.ScopeType;
import fr.cedricsevestre.entity.engine.Template.TemplateType;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.NDataService;
import fr.cedricsevestre.service.engine.PositionService;
import fr.cedricsevestre.service.engine.TemplateService;

@Component
@Scope(value = "singleton")
public class Discrimin extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Discrimin.class);

	private String name = null;
	private String scope = null;
	
	public int doStartTag() {
		Integer contextScope = PageContext.REQUEST_SCOPE;
		if (scope !=null){
			switch (scope.toUpperCase()) {
				case "SESSION":
					contextScope = PageContext.SESSION_SCOPE;
					break;
				case "PAGE":
					contextScope = PageContext.PAGE_SCOPE;
					break;
				case "APPLICATION":
					contextScope = PageContext.APPLICATION_SCOPE;
					break;
				default:
					contextScope = PageContext.REQUEST_SCOPE;
					break;
				}
		}
		List<String> discriminators = (ArrayList<String>) pageContext.getAttribute("NEngineDiscriminator", contextScope);
		if (discriminators != null && discriminators.contains(name)){
			return SKIP_BODY;
		}
		else {
			if (discriminators == null){
				discriminators = new ArrayList<>();
			}
			discriminators.add(name);
			pageContext.setAttribute("NEngineDiscriminator", discriminators, contextScope);
			return EVAL_BODY_AGAIN;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}



}