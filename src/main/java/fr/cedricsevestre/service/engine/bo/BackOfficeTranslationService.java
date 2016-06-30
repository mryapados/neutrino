package fr.cedricsevestre.service.engine.bo;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.engine.BaseDao;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.IBaseService;
import fr.cedricsevestre.service.engine.translation.TObjectService;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@Service
@Scope(value = "singleton")
public class BackOfficeTranslationService{

	private Logger logger = Logger.getLogger(BackOfficeTranslationService.class);

	@Autowired
	private TObjectService tObjectService;

	public List<Translation> findAllForType(String type) throws ServiceException{
		List<Translation> translations = tObjectService.findAllForType(type);
		return translations;
	}



}
