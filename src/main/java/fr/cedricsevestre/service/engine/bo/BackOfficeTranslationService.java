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
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.IBaseService;
import fr.cedricsevestre.service.engine.TObjectService;
import fr.cedricsevestre.service.engine.TranslationService;

@Service
@Scope(value = "singleton")
public class BackOfficeTranslationService{

	private Logger logger = Logger.getLogger(BackOfficeTranslationService.class);

//	@Autowired
//	private TranslationService<Translation> translationService;
	
	
	@Autowired
	private TObjectService tObjectService;
	

	public List<Translation> getAll(String type) throws ServiceException{
		
		
		List<Translation> translations = tObjectService.findAll();
		for (Translation translation : translations) {
			System.out.println(translation.getObjectType() + " - " + translation.getName());
		}
		
		
		return translations;
	}






}
