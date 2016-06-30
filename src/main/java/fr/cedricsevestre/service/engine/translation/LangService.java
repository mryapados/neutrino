package fr.cedricsevestre.service.engine.translation;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.service.engine.BaseService;

@Service
@Scope(value = "singleton")
public class LangService extends BaseService<Lang>{

}
