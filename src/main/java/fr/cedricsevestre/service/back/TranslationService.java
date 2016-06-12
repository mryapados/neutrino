package fr.cedricsevestre.service.back;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.back.TranslationDao;
import fr.cedricsevestre.entity.back.Base;
import fr.cedricsevestre.entity.back.Lang;
import fr.cedricsevestre.entity.back.Translation;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class TranslationService{

	private Logger logger = Logger.getLogger(TranslationService.class);

	@Autowired
	private TranslationDao translationDao;

	@Transactional
	public Translation save(Translation translation) throws ServiceException {
		logger.debug("appel de la methode save Translation " + translation.getId());
		try {
			return translationDao.save(translation);
		} catch (PersistenceException e) {
			logger.error("erreur save Translation " + e.getMessage());
			throw new ServiceException("erreur save Translation", e);
		}
	}

	@Transactional
	public void remove(Translation translation) throws ServiceException {
		logger.debug("appel de la methode remove Translation " + translation.getId());
		try {
			translationDao.delete(translation);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Translation " + e.getMessage());
			throw new ServiceException("Translation id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Translation " + e.getMessage());
			throw new ServiceException("erreur remove Translation", e);
		} catch (Exception e) {
			logger.error("erreur remove Translation " + e.getMessage());
			throw new ServiceException("erreur remove Translation", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Translation id " + id);
		try {
			translationDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Translation " + e.getMessage());
			throw new ServiceException("Translation id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Translation " + e.getMessage());
			throw new ServiceException("erreur removeById Translation", e);
		} catch (Exception e) {
			logger.error("erreur remove Translation " + e.getMessage());
			throw new ServiceException("erreur removeById Translation", e);
		}
	}
	
	public Translation findById(Integer id) throws ServiceException {
		try {
			return translationDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById Translation", e);
		}
	}
	
	public List<Translation> findAll() throws ServiceException {
		try {
			return translationDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Translation", e);
		}
	}
	
		
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public TranslationDao getTranslationDao() {
		return translationDao;
	}

	public void setTranslationDao(TranslationDao translationDao) {
		this.translationDao = translationDao;
	}






}
