package fr.cedricsevestre.service.back;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.back.LangDao;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class LangService{

	private Logger logger = Logger.getLogger(LangService.class);

	@Autowired
	private LangDao langDao;

	@Transactional
	public Lang save(Lang lang) throws ServiceException {
		logger.debug("appel de la methode save Lang " + lang.getId());
		try {
			return langDao.save(lang);
		} catch (PersistenceException e) {
			logger.error("erreur save Lang " + e.getMessage());
			throw new ServiceException("erreur save Lang", e);
		}
	}

	@Transactional
	public void remove(Lang lang) throws ServiceException {
		logger.debug("appel de la methode remove Lang " + lang.getId());
		try {
			langDao.delete(lang);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Lang " + e.getMessage());
			throw new ServiceException("Lang id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Lang " + e.getMessage());
			throw new ServiceException("erreur remove Lang", e);
		} catch (Exception e) {
			logger.error("erreur remove Lang " + e.getMessage());
			throw new ServiceException("erreur remove Lang", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Lang id " + id);
		try {
			langDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Lang " + e.getMessage());
			throw new ServiceException("Lang id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Lang " + e.getMessage());
			throw new ServiceException("erreur removeById Lang", e);
		} catch (Exception e) {
			logger.error("erreur remove Lang " + e.getMessage());
			throw new ServiceException("erreur removeById Lang", e);
		}
	}
	
	public Lang findById(Integer id) throws ServiceException {
		try {
			return langDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById Lang", e);
		}
	}
	
	public List<Lang> findAll() throws ServiceException {
		try {
			return langDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Lang", e);
		}
	}
		
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public LangDao getLangDao() {
		return langDao;
	}

	public void setLangDao(LangDao langDao) {
		this.langDao = langDao;
	}






}
