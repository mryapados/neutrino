package fr.cedricsevestre.service.engine;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.Translation;

@Service
@Scope(value = "singleton")
public class TObjectService extends TranslationService<Translation>{




}
