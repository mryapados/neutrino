package fr.cedricsevestre.service.engine.translation;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOService;
import fr.cedricsevestre.dao.engine.LangDao;
import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;

@BOService
@Service
@Scope(value = "singleton")
public class LangService extends BaseService<Lang>{
	
	private Logger logger = Logger.getLogger(TranslationService.class);
	
	@Autowired
	private LangDao langDao;
	
	public Lang findByCode(String code) throws ServiceException {
		try {
			return langDao.findByCode(code);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findByName Base", e);
		}
	}
}
