package fr.cedricsevestre.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.taglibs.standard.tag.common.core.ImportSupport;
import org.apache.taglibs.standard.tag.common.core.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.NData;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.entity.engine.translation.objects.Template.TemplateKind;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.independant.objects.NDataService;
import fr.cedricsevestre.service.engine.independant.objects.PositionService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;
import fr.cedricsevestre.taglib.IIncludeJSP.Attributes;

@Component
@Scope(value = "singleton")
public class CountBlock extends TagSupport {
	private static final long serialVersionUID = 1L;

	private String var; // 'var' attribute
	private int scope; // processed 'scope' attribute
	private String position = null;
			
	private static PositionService positionService;
	@Autowired
	public void PositionService(PositionService positionService) {
		CountBlock.positionService = positionService;
	}
	
	public int doStartTag() throws JspException {	
		try {
		Folder folder = (Folder) pageContext.getAttribute(Attributes.FOLDER.toString(), PageContext.REQUEST_SCOPE);
		List<Translation> models = new ArrayList<>();
		Translation model = (Template) pageContext.getAttribute(Attributes.PARENTPAGEBLOCK.toString(), PageContext.REQUEST_SCOPE);
		Page page = (Page) pageContext.getAttribute(Attributes.ACTIVEPAGE.toString(), PageContext.REQUEST_SCOPE);
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

		if (var != null) pageContext.setAttribute(var, count, scope);
		if (count == 0) return SKIP_BODY;
		
		return EVAL_BODY_AGAIN;
		
		} catch (ServiceException e) {
			throw new JspTagException(e);
		}
	}
	

	public void setVar(String var) {
		this.var = var;
	}
	public void setScope(String scope) {
		this.scope = Util.getScope(scope);
	}

	public void setPisition(String position) {
		this.position = position;
	}
	
}