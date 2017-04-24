package fr.cedricsevestre.taglib;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.log4j.Logger;
import org.apache.taglibs.standard.tag.common.core.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.com.utils.EntityLocator;
import fr.cedricsevestre.com.utils.IdProviderUtil;
import fr.cedricsevestre.com.utils.ServiceLocator;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.service.engine.notranslation.NoTranslationService;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@Component
@Scope(value = "singleton")
public class Bind extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Bind.class);

	private static IdProviderUtil idProviderUtil;
	@Autowired
	public void IdProviderUtil(IdProviderUtil idProviderUtil) {
		Bind.idProviderUtil = idProviderUtil;
	}

	private String var;                 
	private int scope;
	private String type;
	private int beanId;
	private String field;

	public Bind() {
		super();
		init();
	}

	private void init() {
		var = null;
	    scope = PageContext.PAGE_SCOPE;
	}

	public int doStartTag() throws JspException {
		logger.debug("Enter in doStartTag()");
		try {
			Object result = idProviderUtil.getIdProviderFieldValue(type, beanId, field);
			if (var != null) pageContext.setAttribute(var, result, scope);
			else pageContext.getOut().print(result);

		} catch (IOException e) {
			throw new JspTagException(e);
		}
		return SKIP_BODY;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public void setScope(String scope) {
		this.scope = Util.getScope(scope);
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setBeanId(int beanId) {
		this.beanId = beanId;
	}

	public void setField(String field) {
		this.field = field;
	}

}