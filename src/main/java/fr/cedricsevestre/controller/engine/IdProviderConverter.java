package fr.cedricsevestre.controller.engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.exception.ResourceNotFoundException;
import fr.cedricsevestre.service.engine.EntityLocator;

@Component
public class IdProviderConverter implements Converter <String, IdProvider> {
	@Autowired
	EntityLocator entityLocator;
	
	@Override
	public IdProvider convert (String objectType) {
		System.out.println("Enter in convert [IdProviderConverter] : " + objectType);
		Class<?> cls;
		try {
			cls = entityLocator.getEntity(objectType).getClass();
		} catch (ClassNotFoundException e) {
			throw new ResourceNotFoundException(objectType + " Not found !", e);
		}
		if (cls == null){
            throw new IllegalArgumentException ("Unknown IdProvider type:" + objectType);
		}
		try {
			return (IdProvider) cls.newInstance();
		} catch (InstantiationException e) {
			throw new IllegalArgumentException (e.getMessage(),  e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException (e.getMessage(),  e);
		} 
	}
}
