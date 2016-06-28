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
import fr.cedricsevestre.entity.engine.NSchema.ScopeType;
import fr.cedricsevestre.entity.engine.Page;
import fr.cedricsevestre.entity.engine.Position;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.entity.engine.Template.TemplateType;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.entity.engine.User;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.NDataService;
import fr.cedricsevestre.service.engine.PositionService;
import fr.cedricsevestre.service.engine.TemplateService;

@Component
@Scope(value = "singleton")
public class Block extends TagSupport {

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
		
	private String position = null;

	public int doStartTag() {
		logger.debug("Enter in doStartTag()");
		JspWriter out = pageContext.getOut();
		try {
			Boolean blockPreview = (Boolean) pageContext.getAttribute("blockPreview", PageContext.REQUEST_SCOPE);
			if (blockPreview){
				User surfer = (User) pageContext.getAttribute("surfer", PageContext.REQUEST_SCOPE);
				if (surfer.getRole().equals(User.ROLE_ADMIN)){
					Template model = (Template) pageContext.getAttribute("activeBlock", PageContext.REQUEST_SCOPE);
					Translation activeObject = (Translation) pageContext.getAttribute("activeObject", PageContext.REQUEST_SCOPE);
					Page page = (Page) pageContext.getAttribute("page", PageContext.REQUEST_SCOPE);
					if (model == null) model = page.getModel();
					
					Integer activeObjectId = 0;
					if (activeObject != null) activeObjectId = activeObject.getId();
					
					out.println("<data-ui-position model=\"" + model.getName() + "\" activeobject=\"" + activeObjectId + "\" position=\"" + position + "\" />");
				} else {
					getJsp();
				}
			} else getJsp();
		} catch (ServletException | IOException e) {
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
	public void getJsp() throws ServletException, IOException{
		logger.debug("Enter in getJsp()");
		JspWriter out = pageContext.getOut();
		if (Common.DEBUG) out.print("<p class=\"debug\">" + "Enter in getJSP()" + "</p>");
		try {
			
			List<Translation> models = new ArrayList<>();
			Translation model = (Template) pageContext.getAttribute("parentPageBlock", PageContext.REQUEST_SCOPE);
			Page page = (Page) pageContext.getAttribute("page", PageContext.REQUEST_SCOPE);
			if (model == null) model = page.getModel();
			models.add(model);
			
			Translation activeObject = (Translation) pageContext.getAttribute("activeObject", PageContext.REQUEST_SCOPE);
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
					
					NSchema nSchema =  activeBlock.getSchema();
					List<NData> nDatas = null;
					if (nSchema != null){
						//pb lazy
						//activeBlock ne contient pas datas qui n'est pas initialisé car lazy
						//Il faut donc recharger le template en demandant explicitement les datas.
						//Ou charger les datas directement, c'est la méthode choisie ici.
						if (nSchema.getScope() == ScopeType.ALL){
							nDatas = nDataService.findAllForTemplate(activeBlock);
						} else if (nSchema.getScope() == ScopeType.ONE){
							nDatas = nDataService.findAllForMapTemplate(mapTemplate);
						}
						
//						List<Object> properties = new ArrayList<>();
						for (NData nData : nDatas) {
							pageContext.setAttribute(nData.getPropertyName(), nDataService.getNDataValue(nData), PageContext.REQUEST_SCOPE);
						}
					}
					
					String path = null;	
					String pathContext = Common.BASE_PAGES_VIEWS_PATH + page.getContext();
					if (templateService.checkJSPExist(common.getWebInfFolder(), pathContext, activeBlock)){
						path = templateService.pathJSP(pathContext, activeBlock);
					} else {
						path = templateService.pathJSP(Common.BASE_PAGES_COMMON_PATH, activeBlock);
					}

					if (Common.DEBUG) out.print("<p class=\"debug\">" + "path = " + path + "</p>");
					pageContext.setAttribute("activeBlock", activeBlock, PageContext.REQUEST_SCOPE);
					
					if (activeBlock.getType() == TemplateType.PAGEBLOCK){
						pageContext.setAttribute("parentPageBlock", activeBlock, PageContext.REQUEST_SCOPE);
						pageContext.include(path.toString());
						pageContext.removeAttribute("parentPageBlock");
					} else {
						pageContext.include(path.toString());
					}
					
					//remove ndata attributes
					if (nDatas != null){
						for (NData nData : nDatas) {
							pageContext.removeAttribute(nData.getPropertyName(), PageContext.REQUEST_SCOPE);
						}
					}
					
					
				}
			}
		} catch (ServiceException e) {
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		
		if (Common.DEBUG) out.print("<p class=\"debug\">" + "Exit getJSP()" + "</p>");
	}
	
	@Deprecated
	public void getJspOld() throws ServletException, IOException{
		logger.debug("Enter in getJsp()");
		JspWriter out = pageContext.getOut();
		if (Common.DEBUG) out.print("<p class=\"debug\">" + "Enter in getJSP()" + "</p>");
		try {
			Translation model = (Template) pageContext.getAttribute("parentPageBlock", PageContext.REQUEST_SCOPE);

			Position pos = null;
			if (model == null){
				model = (Translation) pageContext.getAttribute("activeObject", PageContext.REQUEST_SCOPE);
				if (model != null){
					pos = positionService.findByNameForModelWithMaps(model, position);
					if (pos == null) model = null;
				}
			}
			
			Page page = (Page) pageContext.getAttribute("page", PageContext.REQUEST_SCOPE);
			if (model == null) model = page.getModel();
						
			if (pos == null) pos = positionService.findByNameForModelWithMaps(model, position);

			if (Common.DEBUG) out.print("<p class=\"debug\">" + "position : " + position + "</p>");
			if (pos != null){
				List<MapTemplate> mapTemplates;
				mapTemplates = pos.getMapTemplates();
				for (MapTemplate mapTemplate : mapTemplates) {
					Template activeBlock = mapTemplate.getBlock();
					
					NSchema nSchema =  activeBlock.getSchema();
					List<NData> nDatas = null;
					if (nSchema != null){
						//pb lazy
						//activeBlock ne contient pas datas qui n'est pas initialisé car lazy
						//Il faut donc recharger le template en demandant explicitement les datas.
						//Ou charger les datas directement, c'est la méthode choisie ici.
						if (nSchema.getScope() == ScopeType.ALL){
							nDatas = nDataService.findAllForTemplate(activeBlock);
						} else if (nSchema.getScope() == ScopeType.ONE){
							nDatas = nDataService.findAllForMapTemplate(mapTemplate);
						}
						
//						List<Object> properties = new ArrayList<>();
						for (NData nData : nDatas) {
							pageContext.setAttribute(nData.getPropertyName(), nDataService.getNDataValue(nData), PageContext.REQUEST_SCOPE);
						}
					}
					
					String path = null;	
					String pathContext = Common.BASE_PAGES_VIEWS_PATH + page.getContext();
					if (templateService.checkJSPExist(common.getWebInfFolder(), pathContext, activeBlock)){
						path = templateService.pathJSP(pathContext, activeBlock);
					} else {
						path = templateService.pathJSP(Common.BASE_PAGES_COMMON_PATH, activeBlock);
					}

					if (Common.DEBUG) out.print("<p class=\"debug\">" + "path = " + path + "</p>");
					pageContext.setAttribute("activeBlock", activeBlock, PageContext.REQUEST_SCOPE);
					
					if (activeBlock.getType() == TemplateType.PAGEBLOCK){
						pageContext.setAttribute("parentPageBlock", activeBlock, PageContext.REQUEST_SCOPE);
						pageContext.include(path.toString());
						pageContext.removeAttribute("parentPageBlock");
					} else {
						pageContext.include(path.toString());
					}
					
					//remove ndata attributes
					if (nDatas != null){
						for (NData nData : nDatas) {
							pageContext.removeAttribute(nData.getPropertyName(), PageContext.REQUEST_SCOPE);
						}
					}
					
					
				}
			}
		} catch (ServiceException e) {
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		}
		
		if (Common.DEBUG) out.print("<p class=\"debug\">" + "Exit getJSP()" + "</p>");
	}
	
	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return (position);
	}

}