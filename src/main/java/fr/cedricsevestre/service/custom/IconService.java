package fr.cedricsevestre.service.custom;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.entity.custom.Icon;
import fr.cedricsevestre.service.engine.notranslation.NoTranslationService;

@Service
@Scope(value = "singleton")
@CustomService
public class IconService extends NoTranslationService<Icon>{

	
}
