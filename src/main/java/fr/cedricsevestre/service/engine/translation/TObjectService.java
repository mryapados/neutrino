package fr.cedricsevestre.service.engine.translation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.translation.Translation;

@Service
@Scope(value = "singleton")
public class TObjectService extends TranslationService<Translation>{

}
