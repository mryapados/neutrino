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
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TObjectService;

@Service
@Scope(value = "singleton")
public class BackOfficeTranslationService{

	private Logger logger = Logger.getLogger(BackOfficeTranslationService.class);

	@Autowired
	private TObjectService tObjectService;

	public List<Translation> findAllForType(String type) throws ServiceException{
		List<Translation> translations = tObjectService.findAllForType(type);
		return translations;
	}

	public List<NField> getFields(Class<?> classObject) throws ServiceException{
		List<NField> fields = new ArrayList<>();
		
		
		Class<?> superclass = classObject.getSuperclass();
		System.out.println("superclass = " + superclass.getName());
		Field[] superclassFields = superclass.getDeclaredFields();
		for (Field superclassField : superclassFields) {
			System.out.println("--- field = " + superclassField.getName());
			
			Annotation[] annotations = superclassField.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				
				Class<? extends Annotation> annotationType = annotation.annotationType();
				System.out.println("--- --- annotation = " + annotationType.getName());
				
				if(annotation instanceof BOField){
					BOField nType = (BOField) annotation;
			        System.out.println("--- --- --- type : " + nType.type());
			        System.out.println("--- --- --- ofType: " + nType.ofType());
				}
				
				
				
				
				
				
				//System.out.println("superclass annotation = " + annotation..getClass().getName());
			}
			
			
		}
		
		Field[] fields2 = classObject.getDeclaredFields();
		for (Field field : fields2) {
			System.out.println(field.getName());
		}
		
		
		System.out.println("class = " + classObject.getName());
		
		return fields;
	}
	


}
