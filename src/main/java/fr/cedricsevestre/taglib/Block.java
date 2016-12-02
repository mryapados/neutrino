package fr.cedricsevestre.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.independant.objects.NData;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema.ScopeType;
import fr.cedricsevestre.entity.engine.independant.objects.Position;
import fr.cedricsevestre.entity.engine.independant.objects.User;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.entity.engine.translation.objects.Template.TemplateKind;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.independant.objects.NDataService;
import fr.cedricsevestre.service.engine.independant.objects.PositionService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;

@Component
@Scope(value = "singleton")
public class Block extends TagSupport implements IIncludeJSP {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Block.class);

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
					Page page = (Page) pageContext.getAttribute(Attributes.PAGE.toString(), PageContext.REQUEST_SCOPE);
					if (model == null) model = page.getModel();
					
					Integer activeObjectId = 0;
					if (activeObject != null) activeObjectId = activeObject.getId();
					
					out.println("<data-ui-position model=\"" + model.getName() + "\" activeobject=\"" + activeObjectId + "\" position=\"" + position + "\" />");
				} else {
					getJsp();
				}
			} else getJsp();
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
			Page page = (Page) pageContext.getAttribute(Attributes.PAGE.toString(), PageContext.REQUEST_SCOPE);
			if (model == null) model = page.getModel();
			models.add(model);
			
			Translation activeObject = (Translation) pageContext.getAttribute(Attributes.ACTIVEOBJECT.toString(), PageContext.REQUEST_SCOPE);
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
					if (nDatas != null){
						for (NData nData : nDatas) {
							pageContext.removeAttribute(nData.getPropertyName(), PageContext.REQUEST_SCOPE);
						}
					}
					
					
				}
			}
			
			
			if (Common.DEBUG) out.print("<p class=\"debug\">" + "Exit getJSP()" + "</p>");
			
		} catch (ServiceException e) {
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		} catch (IOException e) {
			try {
				out.println("<p class=\"bg-danger\">" + e.getMessage() + "</p>");
			} catch (IOException ex) {
				logger.error("Erreur Block " + ex.getMessage());
				ex.printStackTrace();
			}
		} catch (ServletException e) {
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

}