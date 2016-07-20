package fr.cedricsevestre.taglib;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

import org.apache.taglibs.standard.resources.Resources;
import org.apache.taglibs.standard.tag.common.core.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.NData;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema.ScopeType;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.entity.engine.translation.objects.Template.TemplateType;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.independant.objects.NDataService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;

@Component
@Scope(value = "singleton")
public class Import extends org.apache.taglibs.standard.tag.common.core.ImportSupport implements IIncludeJSP {
	private static final long serialVersionUID = 1L;
	private static final String FOLDER = "folder";
	private static final String PAGE = "page";
	
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
		return super.doStartTag();
	}
	
	public void getJsp() throws JspException{
		try {
			Template target = templateService.findByName(template);

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
			
			Folder folder = (Folder) pageContext.getAttribute(FOLDER, PageContext.REQUEST_SCOPE);
			Page page = (Page) pageContext.getAttribute(PAGE, PageContext.REQUEST_SCOPE);
			String path = templateService.getPathJSP(true, folder, page.getContext(), target, true);
			
			if (target.getType() == TemplateType.BLOCK){
				pageContext.setAttribute("activeBlock", target, PageContext.REQUEST_SCOPE);
				pageContext.include(path);
				
			} else if (target.getType() == TemplateType.PAGEBLOCK){
				pageContext.setAttribute("activeBlock", target, PageContext.REQUEST_SCOPE);
				pageContext.setAttribute("parentPageBlock", target, PageContext.REQUEST_SCOPE);
				pageContext.include(path);
				pageContext.removeAttribute("parentPageBlock");
				
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