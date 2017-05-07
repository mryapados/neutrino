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
import org.apache.taglibs.standard.tag.common.core.ParamParent;
import org.apache.taglibs.standard.tag.common.core.ParamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.com.utils.JspTagUtil;
import fr.cedricsevestre.conf.ApplicationProperties;
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

public class BlockTag extends TagSupport implements IIncludeJSP, ParamParent {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(BlockTag.class);

	private PositionService positionService;
	private JspTagUtil jspTagUtil;
	
	private String position;
	private String page;
	private String activeObject;
	private int pageId;
	private int activeObjectId;
	private Map<String, String> params;	 // added parameters
	
	public BlockTag() {
		super();
		init();
	}
	
    private void init() {
    	position = null;
    	page  = null;
    	activeObject = null;
    	pageId = 0;
    	activeObjectId = 0;
    	params = null;
    }
    
    @Override
    public void addParameter(String name, String value) {
    	params.put(name, value);
    }
    
	public int doStartTag() throws JspException {
		logger.debug("Enter in doStartTag()");
		
    	positionService = (PositionService) pageContext.getAttribute(AttributeConst.POSITION_SERVICE_BEAN, PageContext.APPLICATION_SCOPE);
    	jspTagUtil = (JspTagUtil) pageContext.getAttribute(AttributeConst.JSP_TAG_UTIL_BEAN, PageContext.APPLICATION_SCOPE);
		
		params = new HashMap<>();
		JspWriter out = pageContext.getOut();
		try {
			Boolean blockPreview = (Boolean) pageContext.getAttribute(AttributeConst.BLOCKPREVIEW, PageContext.REQUEST_SCOPE);
			if (blockPreview){
				User surfer = (User) pageContext.getAttribute(AttributeConst.SURFER, PageContext.REQUEST_SCOPE);
				if (surfer.getRole().equals(User.ROLE_ADMIN)){
					Template model = (Template) pageContext.getAttribute(AttributeConst.ACTIVEBLOCK, PageContext.REQUEST_SCOPE);
					Translation activeObject = (Translation) pageContext.getAttribute(AttributeConst.ACTIVEOBJECT, PageContext.REQUEST_SCOPE);
					Page page = (Page) pageContext.getAttribute(AttributeConst.ACTIVEPAGE, PageContext.REQUEST_SCOPE);
					if (model == null) model = page.getModel();
					
					String activeObjectId = "";
					if (this.activeObjectId != 0) activeObjectId = String.valueOf(this.activeObjectId);
					else if (activeObject != null) activeObjectId = activeObject.getId().toString();

					Position pos = positionService.findByName(position);
					out.println("<data-ui-position model-id=\"" + model.getId() + "\" active-object-id=\"" + activeObjectId + "\" position-id=\"" + pos.getId() + "\" />");
				} else {
					getJsp();
				}
			} else getJsp();
		} catch (IOException | ServiceException e) {
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		return EVAL_BODY_INCLUDE;
	}
	
	public void getJsp() throws JspException{
		logger.debug("Enter in getJsp()");
		jspTagUtil.getJspBlock(pageContext, position, page, activeObject, pageId, activeObjectId, params);
	}
	
	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return (position);
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