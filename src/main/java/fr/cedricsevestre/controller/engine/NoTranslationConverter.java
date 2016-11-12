package fr.cedricsevestre.controller.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.service.engine.EntityLocator;

@Component
public class NoTranslationConverter implements Converter <String, NoTranslation> {
	@Autowired
	EntityLocator entityLocator;
	
	public NoTranslation convert (String objectType) {
		System.out.println("Enter in convert [NoTranslationConverter]");
		Class<?> cls = entityLocator.getEntity(objectType).getClass();
		if (cls == null){
            throw new IllegalArgumentException ("Unknown noTranslation type:" + objectType);
		}
		try {
			return (NoTranslation) cls.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalArgumentException (e.getMessage(),  e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException (e.getMessage(),  e);
		} 
	}
}
