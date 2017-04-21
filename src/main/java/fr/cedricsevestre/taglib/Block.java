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

import fr.cedricsevestre.common.Common;
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
import fr.cedricsevestre.service.engine.BlockControllerExecutor;
import fr.cedricsevestre.service.engine.independant.objects.NDataService;
import fr.cedricsevestre.service.engine.independant.objects.PositionService;
import fr.cedricsevestre.service.engine.translation.TObjectService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;

@Component
@Scope(value = "singleton")
public class Block extends TagSupport implements IIncludeJSP {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Block.class);

	private static Common common;
	@Autowired
	public void Common(Common common) {
		Block.common = common;
	}
	
	private static PositionService positionService;
	@Autowired
	public void PositionService(PositionService positionService) {
		Block.positionService = positionService;
	}
	
	private static TemplateService templateService;
	@Autowired
	public void TemplateService(TemplateService templateService) {
		Block.templateService = templateService;
	}

	private static NDataService nDataService;
	@Autowired
	public void NDataService(NDataService nDataService) {
		Block.nDataService = nDataService;
	}
	
	private static BlockControllerExecutor templateControllerExecutor ;
	@Autowired
	public void TemplateControllerExecutor(BlockControllerExecutor templateControllerExecutor) {
		Block.templateControllerExecutor = templateControllerExecutor;
	}
	
	private static PageService pageService;
	@Autowired
	public void PageService(PageService pageService) {
		Block.pageService = pageService;
	}
	
	private static TObjectService tObjectService;
	@Autowired
	public void TObjectService(TObjectService tObjectService) {
		Block.tObjectService = tObjectService;
	}
		
	private String position;
	private String page;
	private String activeObject;
	private int pageId;
	private int activeObjectId;
	
	public int doStartTag() throws JspException {
		logger.debug("Enter in doStartTag()");
		JspWriter out = pageContext.getOut();
		try {
			Boolean blockPreview = (Boolean) pageContext.getAttribute(Attributes.BLOCKPREVIEW.toString(), PageContext.REQUEST_SCOPE);
			if (blockPreview){
				User surfer = (User) pageContext.getAttribute(Attributes.SURFER.toString(), PageContext.REQUEST_SCOPE);
				if (surfer.getRole().equals(User.ROLE_ADMIN)){
					Template model = (Template) pageContext.getAttribute(Attributes.ACTIVEBLOCK.toString(), PageContext.REQUEST_SCOPE);
					Translation activeObject = (Translation) pageContext.getAttribute(Attributes.ACTIVEOBJECT.toString(), PageContext.REQUEST_SCOPE);
					Page page = (Page) pageContext.getAttribute(Attributes.ACTIVEPAGE.toString(), PageContext.REQUEST_SCOPE);
					if (model == null) model = page.getModel();
					
					String activeObjectId = "";
					if (activeObject != null) activeObjectId = activeObject.getId().toString();

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
	
	/* Get blocks from container
	 * Container = parentPageBlock if not null; 
	 * else Container = activeObject if not null and if contains blocks
	 * else Container = page;
	 */
	public void getJsp() throws JspException{
		logger.debug("Enter in getJsp()");
		JspWriter out = pageContext.getOut();
		try {
			if (Common.DEBUG) out.print("<p class=\"debug\">" + "Enter in getJSP()" + "</p>");
			
			Folder folder = (Folder) pageContext.getAttribute(Attributes.FOLDER.toString(), PageContext.REQUEST_SCOPE);
			List<Translation> models = new ArrayList<>();
			Translation model = (Template) pageContext.getAttribute(Attributes.PARENTPAGEBLOCK.toString(), PageContext.REQUEST_SCOPE);
			Page page = (Page) pageContext.getAttribute(Attributes.ACTIVEPAGE.toString(), PageContext.REQUEST_SCOPE);
			Lang lang = page.getLang();
			
			if (this.pageId != 0) page = pageService.findOne(this.pageId);
			else if (this.page != null) page = common.getPage(folder, this.page, lang);

			if (model == null) model = page.getModel();
			models.add(model);
			
			models.add(page);

			Translation activeObject;
			if (this.activeObjectId != 0) activeObject = tObjectService.findOne(this.activeObjectId);
			else if (this.activeObject != null) activeObject = tObjectService.identify(folder, this.activeObject, lang);
			else activeObject = (Translation) pageContext.getAttribute(Attributes.ACTIVEOBJECT.toString(), PageContext.REQUEST_SCOPE);
			
			if (activeObject != null){
				models.add(activeObject);
			}
					
			Position pos = positionService.findByNameForModelsWithMaps(models, position);

			if (Common.DEBUG) out.print("<p class=\"debug\">" + "position : " + position + "</p>");
			if (pos != null){
				List<MapTemplate> mapTemplates;
				mapTemplates = pos.getMapTemplates();
				for (MapTemplate mapTemplate : mapTemplates) {
					Template activeBlock = mapTemplate.getBlock();
					
					ModelMap modelMap = templateControllerExecutor.execute(activeBlock.getController(), model, activeObject, activeBlock, folder, lang, pageContext);
					if (modelMap != null){
						for (Map.Entry<String, Object> entry : modelMap.entrySet()) {
							pageContext.setAttribute(entry.getKey(), entry.getValue(), PageContext.REQUEST_SCOPE);
						}
					}
										
					Map<String, Object> nDatas = nDataService.getNDatas(mapTemplate);
					pageContext.setAttribute("nDatas", nDatas, PageContext.REQUEST_SCOPE);

					String path = templateService.getPathJSP(true, folder, page.getContext(), activeBlock, true);

					if (Common.DEBUG) out.print("<p class=\"debug\">" + "path = " + path + "</p>");
					pageContext.setAttribute(Attributes.ACTIVEBLOCK.toString(), activeBlock, PageContext.REQUEST_SCOPE);
					
					if (activeBlock.getKind() == TemplateKind.PAGEBLOCK){
						pageContext.setAttribute(Attributes.PARENTPAGEBLOCK.toString(), activeBlock, PageContext.REQUEST_SCOPE);
						pageContext.include(path.toString());
						pageContext.removeAttribute(Attributes.PARENTPAGEBLOCK.toString());
					} else {
						pageContext.include(path.toString());
					}
					
					//remove ndata attributes
					pageContext.removeAttribute("nDatas", PageContext.REQUEST_SCOPE);
				
					
					
				}
			}
			
			
			if (Common.DEBUG) out.print("<p class=\"debug\">" + "Exit getJSP()" + "</p>");
			
		} catch (ServiceException e) {
			logger.error("Erreur Block " + e.getMessage());
			e.printStackTrace();
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		} catch (IOException e) {
			logger.error("Erreur Block " + e.getMessage());
			e.printStackTrace();
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		} catch (ServletException e) {
			logger.error("Erreur Block " + e.getMessage());
			e.printStackTrace();
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		} catch (UtilException e) {
			logger.error("Erreur Block " + e.getMessage());
			e.printStackTrace();
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		} catch (ResourceNotFoundException e) {
			logger.error("Erreur Block " + e.getMessage());
			e.printStackTrace();
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		
		
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