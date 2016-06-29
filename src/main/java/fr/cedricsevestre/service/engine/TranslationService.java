package fr.cedricsevestre.service.engine;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.dao.engine.TranslationProviderDao;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.entity.engine.TranslationProvider;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public abstract class TranslationService<T extends Translation>{

	private Logger logger = Logger.getLogger(TranslationService.class);

	@Autowired
	private TranslationDao<T> translationDao;

	@Autowired
	protected TranslationProviderDao translationProviderDao;
	
	
	
	@Transactional
	public T save(T base) throws ServiceException {
		logger.debug("appel de la methode save Base ");
		try {
			return translationDao.save(base);
		} catch (PersistenceException e) {
			logger.error("erreur save Base " + e.getMessage());
			throw new ServiceException("erreur save Base", e);
		}
	}


	@Transactional
	public void remove(T base) throws ServiceException {
		logger.debug("appel de la methode remove Base ");
		try {
			translationDao.delete(base);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Base " + e.getMessage());
			throw new ServiceException("Base id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Base " + e.getMessage());
			throw new ServiceException("erreur remove Base", e);
		} catch (Exception e) {
			logger.error("erreur remove Base " + e.getMessage());
			throw new ServiceException("erreur remove Base", e);
		}
	}


	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Base id " + id);
		try {
			translationDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Base " + e.getMessage());
			throw new ServiceException("Base id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Base " + e.getMessage());
			throw new ServiceException("erreur removeById Base", e);
		} catch (Exception e) {
			logger.error("erreur remove Base " + e.getMessage());
			throw new ServiceException("erreur removeById Base", e);
		}
	}
	

	public T findById(Integer id) throws ServiceException {
		try {
			return translationDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById Base", e);
		}
	}
	

	public List<T> findAll() throws ServiceException {
		try {
			return translationDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Base", e);
		}
	}

	public T findByName(String name) throws ServiceException {
		try {
			System.out.println("findbyname " + name + " " + translationDao);
			
			return translationDao.findByName(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findByName Base", e);
		}
	}

	@Transactional
	public T translate(T base, Lang lang, Class<T> cls) throws ServiceException, InstantiationException, IllegalAccessException {
		T translated = cls.newInstance(); 

		TranslationProvider translation = base.getTranslation();
		if (translation == null){
			translation = translationProviderDao.save(new TranslationProvider());
		}
		translated.setLang(lang);
		translated.setTranslation(translation);
		translated.setName(base.getName());

		return translated;
	}

	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}









}
