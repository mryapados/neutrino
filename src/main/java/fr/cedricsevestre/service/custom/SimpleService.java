package fr.cedricsevestre.service.custom;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.dao.custom.ProjectDao;
import fr.cedricsevestre.dao.custom.TagDao;
import fr.cedricsevestre.dao.engine.BaseDao;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.custom.Simple;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;
import fr.cedricsevestre.service.engine.notranslation.NoTranslationService;

@Service
@Scope(value = "singleton")
@CustomService
public class SimpleService extends BaseService<Simple>{

	
}
