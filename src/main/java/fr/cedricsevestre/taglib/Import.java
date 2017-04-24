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
		
		throw new JspException("Not implemented !");

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