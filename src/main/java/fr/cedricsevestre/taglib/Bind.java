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
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.service.engine.EntityLocator;
import fr.cedricsevestre.service.engine.ServiceLocator;
import fr.cedricsevestre.service.engine.notranslation.NoTranslationService;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@Component
@Scope(value = "singleton")
public class Bind extends TagSupport {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(Bind.class);

	
	private static EntityLocator entityLocator;
	@Autowired
	public void EntityLocator(EntityLocator entityLocator) {
		this.entityLocator = entityLocator;
	}
	private static ServiceLocator serviceLocator;
	@Autowired
	public void ServiceLocator(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	private static TranslationService<Translation> translationService;
	@Autowired
	public void TranslationService(TranslationService<Translation> translationService) {
		this.translationService = translationService;
	}
	private static NoTranslationService<NoTranslation> noTranslationService;
	@Autowired
	public void NoTranslation(NoTranslationService<NoTranslation> noTranslationService) {
		this.noTranslationService = noTranslationService;
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
	
	private IdProvider getObject(Class<?> entity, Integer id) throws JspTagException{
		try {
			Class<?> params[] = {Integer.class};
			Object paramsObj[] = {id};
			Object service = serviceLocator.getService(entity.getSimpleName());
			Class<?> clazz = service.getClass();
			Method findOne = clazz.getMethod("findOne", params);
			return (IdProvider) findOne.invoke(service, paramsObj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			throw new JspTagException(e);
		}
	}
	
	private Field getField(Class<?> classObject, String fieldName) throws NoSuchFieldException {
		try {
			return classObject.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			Class<?> superClass = classObject.getSuperclass();
			if (superClass != null){
				return getField(classObject.getSuperclass(), fieldName);
			} else {
				throw e;
			}
		}
	}
	
	private Object getFieldValue(Object object, Field field) throws JspTagException {
		try {
			field.setAccessible(true);
			return field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("Failed to get value from field", e);
			throw new JspTagException("Erreur getFieldValue", e);
		}
	}
	
	private Object getResult(String type, int beanId, String field) throws JspTagException{
		try {
			Class<?> clazz = entityLocator.getEntity(type).getClass();
			Object object = getObject(clazz, beanId);
			return getFieldValue(object, getField(clazz, field));
		} catch (ClassNotFoundException | NoSuchFieldException e) {
			throw new JspTagException(e);
		}
	}
	
	public int doStartTag() throws JspException {
		logger.debug("Enter in doStartTag()");
		try {
			Object result = getResult(type, beanId, field);
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