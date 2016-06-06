package fr.cedricsevestre.taglib;

import java.io.IOException;
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
import fr.cedricsevestre.entity.back.MapTemplate;
import fr.cedricsevestre.entity.back.Page;
import fr.cedricsevestre.entity.back.Position;
import fr.cedricsevestre.entity.back.Template;
import fr.cedricsevestre.entity.back.Template.TemplateType;
import fr.cedricsevestre.entity.front.User;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.back.PositionService;
import fr.cedricsevestre.service.back.TemplateService;

@Component
@Scope(value = "singleton")
public class Block extends TagSupport {

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
		
	private String position = null;

	public int doStartTag() {
		JspWriter out = pageContext.getOut();
		try {
			Boolean blockPreview = (Boolean) pageContext.getAttribute("blockPreview", PageContext.REQUEST_SCOPE);
			if (blockPreview){
				User surfer = (User) pageContext.getAttribute("surfer", PageContext.REQUEST_SCOPE);
				if (surfer.getRole().equals(User.ROLE_ADMIN)){
					//out.println("<data-ui-position facade=\"BlockManagementFacade\" position=\"" + position + "\" />");
					Template model = (Template) pageContext.getAttribute("activeBlock", PageContext.REQUEST_SCOPE);
					Page page = (Page) pageContext.getAttribute("page", PageContext.REQUEST_SCOPE);
					if (model == null) model = page.getModel();
					
					out.println("<data-ui-position model=\"" + model.getName() + "\" position=\"" + position + "\" />");
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

//	public int doEndTag() {
//		try {
//			JspWriter out = pageContext.getOut();
//			Boolean blockPreview = (Boolean) pageContext.getAttribute("blockPreview", PageContext.REQUEST_SCOPE);
//			if (blockPreview){
//				out.println("<div class=\"targetblock-add-element\" drop=\"" + position + "\" dropStyle=\"columnDrop\"></div>");
//			}
//		} catch (Exception ex) {
//			logger.error("Erreur Block " + ex.getMessage());
//			ex.printStackTrace();
//		}
//		return SKIP_BODY;
//	}
	
	public void getJsp() throws ServletException, IOException{
		JspWriter out = pageContext.getOut();
		if (Common.DEBUG) out.print("<p class=\"debug\">" + "Enter in getJSP()" + "</p>");
		try {
			Template model = (Template) pageContext.getAttribute("parentPageBlock", PageContext.REQUEST_SCOPE);
			Page page = (Page) pageContext.getAttribute("page", PageContext.REQUEST_SCOPE);
			if (model == null) model = page.getModel();
			
			System.out.println("getJsp model = " + model.getName());
			
			Position pos = positionService.findByNameForModelWithMaps(model, position);
			if (Common.DEBUG) out.print("<p class=\"debug\">" + "position : " + position + "</p>");
			if (pos != null){
				List<MapTemplate> mapTemplates;
				mapTemplates = pos.getMapTemplates();
				for (MapTemplate mapTemplate : mapTemplates) {
					Template activeBlock = mapTemplate.getBlock();
					
					String path = templateService.pathJSP(page.getContext(), activeBlock);				

					if (Common.DEBUG) out.print("<p class=\"debug\">" + "path = " + path + "</p>");
					pageContext.setAttribute("activeBlock", activeBlock, PageContext.REQUEST_SCOPE);
					
					if (activeBlock.getType() == TemplateType.PAGEBLOCK){
						pageContext.setAttribute("parentPageBlock", activeBlock, PageContext.REQUEST_SCOPE);
						pageContext.include(path.toString());
						pageContext.removeAttribute("parentPageBlock");
					} else {
						pageContext.include(path.toString());
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