package fr.cedricsevestre.service.back;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.Lang;

@Service
@Scope(value = "singleton")
public class LangService extends BaseService<Lang>{

}
