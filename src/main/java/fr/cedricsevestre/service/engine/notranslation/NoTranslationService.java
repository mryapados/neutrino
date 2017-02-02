package fr.cedricsevestre.service.engine.notranslation;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.engine.NoTranslationDao;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;
import fr.cedricsevestre.service.engine.IBOService;

@Service
@Scope(value = "singleton")
public abstract class NoTranslationService<T extends NoTranslation> extends BaseService<T>{

	private Logger logger = Logger.getLogger(NoTranslationService.class);

	@Autowired
	private NoTranslationDao<T> noTranslationDao;

	public T findByName(String name) throws ServiceException {
		try {
			System.out.println("findbyname " + name + " " + noTranslationDao);
			
			return noTranslationDao.findByName(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findByName Base", e);
		}
	}

}
