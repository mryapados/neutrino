package fr.cedricsevestre.service.engine;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.NoTranslation;
import fr.cedricsevestre.entity.engine.Translation;

@Service
@Scope(value = "singleton")
public class NTObjectService extends NoTranslationService<NoTranslation>{




}
