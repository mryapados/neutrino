package fr.cedricsevestre.controller.engine;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;

@Component
public class NoTranslationConverter implements Converter <String, NoTranslation> {

	public NoTranslation convert (String objectType) {
		
		System.out.println("Enter in convert [NoTranslationConverter]");
		
		NoTranslation noTranslation = null;
	    switch (objectType) {
	        case "Tag":
	        	noTranslation = new Tag ();
	            break;
	        case "Lecturer":
	            
	            break;
	        default:
	            throw new IllegalArgumentException (
	                    "Unknown noTranslation type:" + objectType);
	    }
	    return noTranslation;
	}
	
	
	
}
