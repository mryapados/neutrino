package fr.cedricsevestre.service.engine;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.NSchema;

@Service
@Scope(value = "singleton")
public class NSchemaService extends BaseService<NSchema>{


}
