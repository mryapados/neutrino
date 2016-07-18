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
import fr.cedricsevestre.common.Common.TypeBase;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.independant.objects.NData;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema;
import fr.cedricsevestre.entity.engine.independant.objects.Position;
import fr.cedricsevestre.entity.engine.independant.objects.User;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema.ScopeType;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.entity.engine.translation.objects.Template.TemplateType;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.independant.objects.NDataService;
import fr.cedricsevestre.service.engine.independant.objects.PositionService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;

@Component
@Scope(value = "singleton")
public class Include extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Include.class);

	private static Common common;
	@Autowired
	public void Common(Common common) {
		this.common = common;
	}
	
	private static TemplateService templateService;
	@Autowired
	public void TemplateService(TemplateService templateService) {
		this.templateService = templateService;
	}
	
	private String page = null;

	private static final String BLOCKPREVIEW = "blockPreview";
	private static final String FOLDER = "folder";
	private static final String SURFER = "surfer";
	private static final String ACTIVEBLOCK = "activeBlock";
	private static final String ACTIVEOBJECT = "activeObject";
	private static final String PAGE = "page";
	
	public int doStartTag() {
		logger.debug("Enter in doStartTag()");
		JspWriter out = pageContext.getOut();
		
//		Translation model = (Template) pageContext.getAttribute("parentPageBlock", PageContext.REQUEST_SCOPE);
//		Page page = (Page) pageContext.getAttribute("page", PageContext.REQUEST_SCOPE);
//		if (model == null) model = page.getModel();
		
		Page pageObject = (Page) pageContext.getAttribute("page", PageContext.REQUEST_SCOPE);
		Folder folder = (Folder) pageContext.getAttribute(FOLDER, PageContext.REQUEST_SCOPE);


		System.out.println(common.getBasePath(true, folder, TypeBase.VIEWS) + pageObject.getContext() + "/" + page);
		System.out.println(common.getBasePath(true, folder, TypeBase.COMMON) + pageObject.getContext() + "/" + page);
		System.out.println(common.getBasePath(true, null, TypeBase.COMMON) + page);

		if (includemmmmmmmmmm(page)){
			return SKIP_BODY;
		} else if (includemmmmmmmmmm(common.getBasePath(true, folder, TypeBase.VIEWS) + pageObject.getContext() + "/" + page)){
			return SKIP_BODY;
		} else if (includemmmmmmmmmm(common.getBasePath(true, folder, TypeBase.COMMON) + page)){
			return SKIP_BODY;
		} else if (includemmmmmmmmmm(common.getBasePath(true, null, TypeBase.COMMON) + page)){
			return SKIP_BODY;
		}

		
//		try {
////			if (include(common.getBasePath(true, folder, TypeBase.VIEWS) + pageObject.getContext() + "/" + page)){
////				System.out.println("1 TROUVE " + common.getBasePath(true, folder, TypeBase.VIEWS) + page);
////				return SKIP_BODY;
////			} else if(include(common.getBasePath(true, folder, TypeBase.COMMON) + page)){
////				System.out.println("2 TROUVE " + common.getBasePath(true, folder, TypeBase.COMMON) + page);
////				return SKIP_BODY;
////			} else if(include(common.getBasePath(true, null, TypeBase.COMMON) + page)){
////				System.out.println("3 TROUVE " + common.getBasePath(true, null, TypeBase.COMMON) + page);
////				return SKIP_BODY;
////			}
//			
////		} catch (ServletException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
//		}
		
		
		return EVAL_BODY_INCLUDE;
	}
	
	private boolean includemmmmmmmmmm(String path){
		try {
			pageContext.include(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
//	private boolean include(String path) throws ServletException{
//		System.out.println("include " + path);
//		try {
//			pageContext.include(path);
//			System.out.println("4 TROUVE " + path);
//		} catch (ServletException e) {
//			System.out.println("5 PAS TROUVE " + path);
//			e.printStackTrace();	
//			return false;
//		} catch (IOException e) {
//			System.out.println("6 PAS TROUVE " + path);
//			e.printStackTrace();
//			return false;
//		}
//		return true;
//	}
	

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	


}