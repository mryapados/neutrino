package fr.cedricsevestre.taglib;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

import org.apache.taglibs.standard.tag.common.core.ImportSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.NData;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.entity.engine.translation.objects.Template.TemplateKind;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.independant.objects.NDataService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;

@Component
@Scope(value = "singleton")
public class Import extends ImportSupport implements IIncludeJSP {
	private static final long serialVersionUID = 1L;

	private String template = null;
	
	private static TemplateService templateService;
	@Autowired
	public void TemplateService(TemplateService templateService) {
		Import.templateService = templateService;
	}

	private static NDataService nDataService;
	@Autowired
	public void NDataService(NDataService nDataService) {
		Import.nDataService = nDataService;
	}
	
	@Override
	public void doCatch(Throwable t) throws Throwable {
		t.printStackTrace();
		throw t;
	}
	
	public int doStartTag() throws JspException {		
		if (template != null){
			getJsp();
			return SKIP_BODY;
		}
		
		if (url.indexOf("/") == 0) {
			url = "/WEB-INF" + url;
		}
		return super.doStartTag();
	}
	
	public void getJsp() throws JspException{
		try {
			
			Folder folder = (Folder) pageContext.getAttribute(Attributes.FOLDER.toString(), PageContext.REQUEST_SCOPE);
			Page page = (Page) pageContext.getAttribute(Attributes.PAGE.toString(), PageContext.REQUEST_SCOPE);
			
			
			Template target = templateService.identify(folder, template, page.getLang());

			NSchema nSchema =  target.getSchema();
			List<NData> nDatas = null;
			if (nSchema != null){
				//pb lazy
				//activeBlock ne contient pas datas qui n'est pas initialisé car lazy
				//Il faut donc recharger le template en demandant explicitement les datas.
				//Ou charger les datas directement, c'est la méthode choisie ici.
		
				//Get only for scope ALL
				nDatas = nDataService.findAllForTemplate(target);
				for (NData nData : nDatas) {
					pageContext.setAttribute(nData.getPropertyName(), nDataService.getNDataValue(nData), PageContext.REQUEST_SCOPE);
				}
			}
			

			String path = templateService.getPathJSP(true, folder, page.getContext(), target, true);
			
			if (target.getKind() == TemplateKind.BLOCK){
				pageContext.setAttribute(Attributes.ACTIVEBLOCK.toString(), target, PageContext.REQUEST_SCOPE);
				pageContext.include(path);
				
			} else if (target.getKind() == TemplateKind.PAGEBLOCK){
				pageContext.setAttribute(Attributes.ACTIVEBLOCK.toString(), target, PageContext.REQUEST_SCOPE);
				pageContext.setAttribute(Attributes.PARENTPAGEBLOCK.toString(), target, PageContext.REQUEST_SCOPE);
				pageContext.include(path);
				pageContext.removeAttribute(Attributes.PARENTPAGEBLOCK.toString());
				
			} else {
				pageContext.include(path);
			}

		} catch (ServiceException e) {
			throw new JspException(e);
		} catch (ServletException e) {
			throw new JspException(e);
		} catch (IOException e) {
			throw new JspException(e);
		}
	}
	
	public int doEndTag() throws JspException {		
		if (template != null){
			return SKIP_BODY;
		}
		return super.doEndTag();
	}
	
    public void setUrl(String url) throws JspTagException {
        this.url = url;
    }
    public void setContext(String context) throws JspTagException {
        this.context = context;
    }
    public void setCharEncoding(String charEncoding) throws JspTagException {
        this.charEncoding = charEncoding;
    }
	public void setTemplate(String template) throws JspTagException {
		this.template = template;
	}
	
}