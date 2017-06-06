package fr.cedricsevestre.taglib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

import org.apache.taglibs.standard.tag.common.core.UrlSupport;

import fr.cedricsevestre.com.utils.IdProviderUtil;
import fr.cedricsevestre.constants.AttributeConst;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.exception.UtilException;

public class UrlTag extends UrlSupport {
	private static final long serialVersionUID = 1L;

	private IdProvider bean;
	private IdProviderUtil idProviderUtil;
	
	
	
	private Object getFieldResult(IdProvider object, String expression) throws UtilException{
		String[] fields = expression.split("\\.");
		IdProvider result = object;
		for (int i = 0; i <= fields.length - 1; i++) {
			Object obj = idProviderUtil.getIdProviderFieldValue(result, fields[i], false);
			if (i == fields.length - 1){
				if (obj instanceof IdProvider){
					return ((IdProvider) result).getId().toString();
				} else {
					return obj;
				}
			} else {
				result = (IdProvider) obj;
			}
		}
		return result;
	}
	
	
	
//	private String getFinalFieldResult(IdProvider object, String expression) throws UtilException{
//		Object result = getFieldResult(object, expression);
//		if (result instanceof IdProvider){
//			return ((IdProvider) result).getId().toString();
//		} else {
//			return result.toString();
//		}
//		
//	}
	
	private void parseUrl() {
		try {
			idProviderUtil = (IdProviderUtil) pageContext.getAttribute(AttributeConst.ID_PROVIDER_UTIL_BEAN, PageContext.APPLICATION_SCOPE);
			Pattern pattern = java.util.regex.Pattern.compile("\\{(.*?)\\}");
			Matcher matcher = pattern.matcher(value);
			while (matcher.find()) {
				for (int i = 0; i <= matcher.groupCount(); i++) {
					System.out.println(matcher.group(i));
				}
				System.out.println(getFieldResult(bean, matcher.group(1)));
				
				
				
				
				
			}
		} catch (UtilException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// if (matcher.find()) {
		// String originInternalLink = matcher.group(0);
		// request.setAttribute("internallink_value", originInternalLink);
		// RequestDispatcher rd =
		// request.getRequestDispatcher("/front/common/tools/noheto-include.jspz");
		// rd.include(request, response);
		// String resultInternalLink = (String)
		// request.getAttribute("internallink_url");
		// result = matcher.replaceFirst(resultInternalLink);
		// contextUrl = ""; // Les liens internes fournissent déjà le context
		//
		// if (result.indexOf("//") == 0) {
		// result = result.substring(1, result.length());
		// }
		//
		// }

	}

	@Override
	public int doStartTag() throws JspException {
		
		if (bean != null){
			parseUrl();
		}
		
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

	public void setBean(Object bean) {
		this.bean = (IdProvider) bean;
	}

}