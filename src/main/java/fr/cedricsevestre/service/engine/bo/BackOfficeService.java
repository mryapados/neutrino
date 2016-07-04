package fr.cedricsevestre.service.engine.bo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.bean.NField;
import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TObjectService;

@Service
@Scope(value = "singleton")
public abstract class BackOfficeService implements IBackOfficeObjectService{

	private Logger logger = Logger.getLogger(BackOfficeService.class);

	public Class<?> getEntity(String type) throws ServiceException{
		try {
			return Class.forName(Common.CUSTOM_ENTITY_PACKAGE + "." + type);
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
		
		System.out.println("classObject = " + classObject.getName());
		Field[] classObjectFields = classObject.getDeclaredFields();
		for (Field classObjectField : classObjectFields) {
			System.out.println("--- field = " + classObjectField.getName());
			Annotation[] annotations = classObjectField.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				Class<? extends Annotation> annotationType = annotation.annotationType();
				System.out.println("--- --- annotation = " + annotationType.getName());
				if(annotation instanceof BOField){
					BOField nType = (BOField) annotation;
			        System.out.println("--- --- --- type : " + nType.type());
			        System.out.println("--- --- --- ofType: " + nType.ofType());
			        fields.add(classObjectField);
				}
			}
		}
		return fields;
	}
	
	public NField mkNField(Field field) throws ServiceException{
		Annotation[] annotations = field.getDeclaredAnnotations();
		for (Annotation annotation : annotations) {
			Class<? extends Annotation> annotationType = annotation.annotationType();
			System.out.println("--- --- annotation = " + annotationType.getName());
			if(annotation instanceof BOField){
				BOField nType = (BOField) annotation;
		        System.out.println("--- --- --- type : " + nType.type());
		        System.out.println("--- --- --- ofType: " + nType.ofType());
		        return new NField(nType.type(), nType.ofType(), field.getName(), field.getType().getName(), nType.inList());
			}
		}
		throw new ServiceException("BoField not found");
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
