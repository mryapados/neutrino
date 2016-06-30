package fr.cedricsevestre.service.engine.independant.objects;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.independant.objects.NSchema;
import fr.cedricsevestre.service.engine.BaseService;

@Service
@Scope(value = "singleton")
public class NSchemaService extends BaseService<NSchema>{


}
