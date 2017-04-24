package fr.cedricsevestre.com.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import fr.cedricsevestre.constants.AttributeConst;
import fr.cedricsevestre.constants.CacheConst;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.independant.objects.Position;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
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
import fr.cedricsevestre.service.engine.notranslation.NoTranslationService;
import fr.cedricsevestre.service.engine.translation.TObjectService;
import fr.cedricsevestre.service.engine.translation.TranslationService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;
import fr.cedricsevestre.taglib.Bind;

@Component
public class JspTagUtil {
	
	private Logger logger = Logger.getLogger(JspTagUtil.class);
	
	@Autowired
	CommonUtil commonUtil;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private TObjectService tObjectService;
	
	@Autowired
	private PositionService positionService;
	
	@Autowired
	private NDataService nDataService;
	
	@Autowired
	private TemplateService templateService;
	
	@Autowired
	private BlockControllerExecutor templateControllerExecutor;
	
	/* Get blocks from container parentPageBlock if not null + activeObject if not null + page */
	public void getJsp(PageContext pageContext, String position, String pageName, String activeObjectName, int pageId, int activeObjectId) throws JspException{
		JspWriter out = pageContext.getOut();
		try {
			if (CommonUtil.DEBUG) out.print("<p class=\"debug\">" + "Enter in getJSP()" + "</p>");
			
			//Get folder from pageContext
			Folder folder = (Folder) pageContext.getAttribute(AttributeConst.FOLDER, PageContext.REQUEST_SCOPE);
			
			List<Translation> models = new ArrayList<>();
			//Get model from pageContext if PARENTPAGEBLOCK != null
			Translation model = (Template) pageContext.getAttribute(AttributeConst.PARENTPAGEBLOCK, PageContext.REQUEST_SCOPE);
			
			//Get page from pageContext
			Page page = (Page) pageContext.getAttribute(AttributeConst.ACTIVEPAGE, PageContext.REQUEST_SCOPE);
			
			//Get lang from page
			Lang lang = page.getLang(); //TODO get from pageContext
			
			if (pageId != 0) page = pageService.findOne(pageId); //If pageId provided -> page is replace
			else if (pageName != null) page = commonUtil.getPage(folder, pageName, lang); //If pageName provided -> page is replace

			if (model == null) model = page.getModel(); //If model == null (if not PARENTPAGEBLOCK) -> model become page model
			models.add(model);
			
			models.add(page);

			Translation activeObject = (Translation) pageContext.getAttribute(AttributeConst.ACTIVEOBJECT, PageContext.REQUEST_SCOPE);
			if (activeObjectId != 0) activeObject = tObjectService.findOne(activeObjectId); //If activeObjectId provided -> activeObject is replace
			else if (activeObject != null) activeObject = tObjectService.identify(folder, activeObjectName, lang); //If activeObjectName provided -> activeObject is replace

			if (activeObject != null){
				models.add(activeObject);
			}
					
			Position pos = positionService.findByNameForModelsWithMaps(models, position);

			if (CommonUtil.DEBUG) out.print("<p class=\"debug\">" + "position : " + position + "</p>");
			if (pos != null){
				List<MapTemplate> mapTemplates;
				mapTemplates = pos.getMapTemplates();
				for (MapTemplate mapTemplate : mapTemplates) {
					Template activeBlock = mapTemplate.getBlock();
					
					ModelMap modelMap = templateControllerExecutor.execute(activeBlock.getController(), page, model, activeObject, activeBlock, folder, lang, pageContext);
					if (modelMap != null){
						for (Map.Entry<String, Object> entry : modelMap.entrySet()) {
							pageContext.setAttribute(entry.getKey(), entry.getValue(), PageContext.REQUEST_SCOPE);
						}
					}
										
					Map<String, Object> nDatas = nDataService.getNDatas(mapTemplate);
					pageContext.setAttribute("nDatas", nDatas, PageContext.REQUEST_SCOPE);

					String path = templateService.getPathJSP(true, folder, page.getContext(), activeBlock, true);

					if (CommonUtil.DEBUG) out.print("<p class=\"debug\">" + "path = " + path + "</p>");
					pageContext.setAttribute(AttributeConst.ACTIVEBLOCK, activeBlock, PageContext.REQUEST_SCOPE);
					
					if (activeBlock.getKind() == TemplateKind.PAGEBLOCK){
						pageContext.setAttribute(AttributeConst.PARENTPAGEBLOCK, activeBlock, PageContext.REQUEST_SCOPE);
						pageContext.include(path.toString());
						pageContext.removeAttribute(AttributeConst.PARENTPAGEBLOCK);
					} else {
						pageContext.include(path.toString());
					}
					
					//remove ndata attributes
					pageContext.removeAttribute(AttributeConst.NDATAS, PageContext.REQUEST_SCOPE);
				}
			}

			if (CommonUtil.DEBUG) out.print("<p class=\"debug\">" + "Exit getJSP()" + "</p>");
			
		} catch (ServiceException | IOException | ServletException | UtilException | ResourceNotFoundException e) {
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

}
