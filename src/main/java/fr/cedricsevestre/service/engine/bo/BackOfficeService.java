package fr.cedricsevestre.service.engine.bo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class BackOfficeService{

	private Logger logger = Logger.getLogger(BackOfficeService.class);

	public Class<?> getEntity(String entityName) throws ServiceException{
		try {
			return Class.forName(Common.CUSTOM_ENTITY_PACKAGE + "." + entityName);
		} catch (ClassNotFoundException e) {
			throw new ServiceException("erreur getEntity", e);
		}
	}
	
	@Deprecated
	public Class<?> getEntityService(String entityName) throws ServiceException{
		try {
			return Class.forName(Common.CUSTOM_SERVICE_PACKAGE + "." + entityName + "Service");
		} catch (ClassNotFoundException e) {
			throw new ServiceException("erreur getEntity", e);
		}
	}
	
	public List<Field> getFields(Class<?> classObject) throws ServiceException{
		List<Field> fields = new ArrayList<>();
		Class<?> superClass = classObject.getSuperclass();
		if (superClass != null){
			fields.addAll(getFields(classObject.getSuperclass()));
		}
		Field[] classObjectFields = classObject.getDeclaredFields();
		for (Field classObjectField : classObjectFields) {
			BOField annotation = classObjectField.getAnnotation(BOField.class);
			if (annotation != null){
				fields.add(classObjectField);
			}
		}
		return fields;
	}

	public Object getFieldValue(Object object, Field field) throws ServiceException {
	    try {
	        field.setAccessible(true);
	        return field.get(object);
	    } catch (IllegalAccessException e) {
	        logger.error("Failed to get value from field", e);
	        throw new ServiceException("Erreur getFieldValue", e);
	    }
	}


}
