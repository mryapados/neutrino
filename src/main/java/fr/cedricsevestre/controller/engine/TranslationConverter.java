package fr.cedricsevestre.controller.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.service.engine.EntityLocator;

@Component
public class TranslationConverter implements Converter <String, Translation> {
	@Autowired
	EntityLocator entityLocator;
	
	public Translation convert (String objectType) {
		System.out.println("Enter in convert [TranslationConverter]");
		Class<?> cls = entityLocator.getEntity(objectType).getClass();
		if (cls == null){
            throw new IllegalArgumentException ("Unknown translation type:" + objectType);
		}
		try {
			return (Translation) cls.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalArgumentException (e.getMessage(),  e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException (e.getMessage(),  e);
		} 
	}
	
}
