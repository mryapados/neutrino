package fr.cedricsevestre.service.engine.notranslation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;

@Service
@Scope(value = "singleton")
public class NTObjectService extends NoTranslationService<NoTranslation>{

}
