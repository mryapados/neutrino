package fr.cedricsevestre.com.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspTagException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.service.engine.EntityLocator;
import fr.cedricsevestre.service.engine.ServiceLocator;
import fr.cedricsevestre.service.engine.notranslation.NoTranslationService;
import fr.cedricsevestre.service.engine.translation.TranslationService;
import fr.cedricsevestre.taglib.Bind;

@Component
public class IdProviderUtil {
	
	private Logger logger = Logger.getLogger(Bind.class);
	
	@Autowired
	private EntityLocator entityLocator;
	
	@Autowired
	private ServiceLocator serviceLocator;

	
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
	
	@Cacheable("idProviderFieldValue")
	public Object getIdProviderFieldValue(String type, int beanId, String field) throws JspTagException{
		System.out.println("JE SUIS RENTREEEEEEEEEEEEE");
		try {
			Class<?> clazz = entityLocator.getEntity(type).getClass();
			Object object = getObject(clazz, beanId);
			return getFieldValue(object, getField(clazz, field));
		} catch (ClassNotFoundException | NoSuchFieldException e) {
			throw new JspTagException(e);
		}
	}
	
	
	private IdProvider copyFields(IdProvider entity, IdProvider newEntity, Class<?> clazz) throws IllegalAccessException {
	    List<Field> fields = new ArrayList<>();
	    for (Field field : clazz.getDeclaredFields()) {
	        fields.add(field);
	    }
	    for (Field field : fields) {
	        field.setAccessible(true);
	        field.set(newEntity, field.get(entity));
	    }
	    return newEntity;
	}
	public IdProvider copy(IdProvider entity) throws IllegalAccessException, InstantiationException {
	    Class<?> clazz = entity.getClass();
	    IdProvider newEntity = (IdProvider) entity.getClass().newInstance();

	    while (clazz != null) {
	        copyFields(entity, newEntity, clazz);
	        clazz = clazz.getSuperclass();
	    }

	    return newEntity;
	}


	
	
	
	
	
	
}
