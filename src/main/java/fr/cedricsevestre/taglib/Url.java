package fr.cedricsevestre.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

import org.apache.taglibs.standard.tag.common.core.UrlSupport;

import fr.cedricsevestre.constants.AttributeConst;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;

public class Url extends UrlSupport {
	private static final long serialVersionUID = 1L;

	@Override
	public int doStartTag() throws JspException {
		//Set servername to url if is provided in current url;
		int doStartTag = super.doStartTag();
		Folder folder = (Folder) pageContext.getAttribute(AttributeConst.FOLDER, PageContext.REQUEST_SCOPE);
		if (folder.isServerNameForced()) addParameter(AttributeConst.SERVERNAME, folder.getName());
		return doStartTag;
	}
	
	public void setValue(String value) throws JspTagException {
		this.value = value;
	}

	// for tag attribute
	public void setContext(String context) throws JspTagException {
		this.context = context;
	}
	
}