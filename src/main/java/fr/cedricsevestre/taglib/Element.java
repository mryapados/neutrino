package fr.cedricsevestre.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.com.utils.JspTagUtil;
import fr.cedricsevestre.constants.AttributeConst;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.independant.objects.NData;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema.ScopeType;
import fr.cedricsevestre.entity.engine.independant.objects.Position;
import fr.cedricsevestre.entity.engine.independant.objects.User;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.entity.engine.translation.objects.Template.TemplateKind;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.exception.UtilException;
import fr.cedricsevestre.service.engine.TemplateControllerExecutor;
import fr.cedricsevestre.service.engine.independant.objects.NDataService;
import fr.cedricsevestre.service.engine.independant.objects.PositionService;
import fr.cedricsevestre.service.engine.translation.TObjectService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;
//TODO
@Component
@Scope(value = "singleton")
public class Element extends TagSupport implements IIncludeJSP {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Element.class);

	private static PositionService positionService;
	@Autowired
	public void PositionService(PositionService positionService) {
		Element.positionService = positionService;
	}
	
	private static JspTagUtil jspTagUtil;
	@Autowired
	public void JspTagUtil(JspTagUtil jspTagUtil) {
		Element.jspTagUtil = jspTagUtil;
	}
	
	private String template;
	private String page;
	private String activeObject;
	private int pageId;
	private int activeObjectId;
	
	public int doStartTag() throws JspException {
		logger.debug("Enter in doStartTag()");
		getJsp();
		return EVAL_BODY_INCLUDE;
	}
	
	public void getJsp() throws JspException{
		logger.debug("Enter in getJsp()");
		jspTagUtil.getJspElement(pageContext, template, page, activeObject, pageId, activeObjectId);
	}
	
	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getActiveObject() {
		return activeObject;
	}

	public void setActiveObject(String activeObject) {
		this.activeObject = activeObject;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getActiveObjectId() {
		return activeObjectId;
	}

	public void setActiveObjectId(int activeObjectId) {
		this.activeObjectId = activeObjectId;
	}


	
	

}