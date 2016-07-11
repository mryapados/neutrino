package fr.cedricsevestre.service.engine.bo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.bean.NData;
import fr.cedricsevestre.bean.NField;
import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.CustomServiceLocator;

@Service
@Scope(value = "singleton")
public class BackOfficeService<T extends IdProvider> implements IBackOfficeService{

	@Autowired
	CustomServiceLocator customServiceLocator;
	
	
	
	private Logger logger = Logger.getLogger(BackOfficeService.class);

	public static Class<?> getEntity(String entityName) throws ServiceException{
		try {
			return Class.forName(Common.CUSTOM_ENTITY_PACKAGE + "." + entityName);
		} catch (ClassNotFoundException e) {
			throw new ServiceException("erreur getEntity", e);
		}
	}
	
	@Deprecated
	public static Class<?> getEntityService(String entityName) throws ServiceException{
		try {
			return Class.forName(Common.CUSTOM_SERVICE_PACKAGE + "." + entityName + "Service");
		} catch (ClassNotFoundException e) {
			throw new ServiceException("erreur getEntity", e);
		}
	}
	
	private List<Field> getFields(Class<?> classObject) throws ServiceException{
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

	private Object getFieldValue(Object object, Field field) throws ServiceException {
	    try {
	        field.setAccessible(true);
	        return field.get(object);
	    } catch (IllegalAccessException e) {
	        logger.error("Failed to get value from field", e);
	        throw new ServiceException("Erreur getFieldValue", e);
	    }
	}

	@SuppressWarnings("unchecked")
	private Page<T> getDatas(Class<?> entity, Pageable pageable) throws ServiceException{
		try {
			Class<?> params[] = {Pageable.class};
			Object paramsObj[] = {pageable};
			Object service = customServiceLocator.getService(entity.getSimpleName());
			Class<?> clazz = Class.forName(service.getClass().getName());
			Method findAllFetched = clazz.getMethod("findAllFetched", params);
			return (Page<T>) findAllFetched.invoke(service, paramsObj);
		} catch (ClassNotFoundException e) {
			logger.error("getDatas -> ClassNotFoundException", e);
			throw new ServiceException("Error getList", e);
		} catch (NoSuchMethodException e) {
			logger.error("getDatas -> NoSuchMethodException", e);
			throw new ServiceException("Error getList", e);
		} catch (SecurityException e) {
			logger.error("getDatas -> SecurityException", e);
			throw new ServiceException("Error getList", e);
		} catch (IllegalAccessException e) {
			logger.error("getDatas -> IllegalAccessException", e);
			throw new ServiceException("Error getList", e);
		} catch (IllegalArgumentException e) {
			logger.error("getDatas -> IllegalArgumentException", e);
			throw new ServiceException("Error getList", e);
		} catch (InvocationTargetException e) {
			logger.error("getDatas -> InvocationTargetException", e);
			throw new ServiceException("Error getList", e);
		}
	}
	
	private List<NField> getNField(List<Field> fields) throws ServiceException{
		List<NField> nfFields = new ArrayList<>();
		for (Field field : fields) {
			BOField annotation = field.getAnnotation(BOField.class);
			if (annotation != null){
				BOField nType = (BOField) annotation;
				nfFields.add(new NField(nType.type(), nType.ofType(), field.getName(), field.getType().getName(), nType.inList()));
			}
		}
		return nfFields;
	}
	
	@Override
	public NData findAll(Class<?> entity) throws ServiceException{
		return findAll(entity, null);
	}
	@Override
	public NData findAll(Class<?> entity, Pageable pageRequest) throws ServiceException{
		List<Map<String, Object>> datas = new ArrayList<>();
		Page<T> translations = getDatas(entity, pageRequest);
		List<Field> fields = getFields(entity);
		List<NField> nfFields = getNField(fields);
		for (T translation : translations) {
			Map<String, Object> record = new HashMap<>();
			for (Field field : fields) {
				record.put(field.getName(), getFieldValue(translation, field));
			}
			datas.add(record);
		}
		return new NData(nfFields, datas);
	}
	
	
	
	
}
