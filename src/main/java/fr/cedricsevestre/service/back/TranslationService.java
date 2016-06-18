package fr.cedricsevestre.service.back;

import java.io.File;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.dao.back.BaseDao;
import fr.cedricsevestre.dao.back.TranslationDao;
import fr.cedricsevestre.entity.engine.Base;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public abstract class TranslationService<T extends Base>{

	private Logger logger = Logger.getLogger(TranslationService.class);

	@Autowired
	private BaseDao<T> baseDao;

	@Autowired
	protected TranslationDao translationDao;
	
	@Transactional
	public T save(T base) throws ServiceException {
		logger.debug("appel de la methode save Base " + base.getName());
		try {
			return baseDao.save(base);
		} catch (PersistenceException e) {
			logger.error("erreur save Base " + e.getMessage());
			throw new ServiceException("erreur save Base", e);
		}
	}

	@Transactional
	public void remove(T base) throws ServiceException {
		logger.debug("appel de la methode remove Base " + base.getName());
		try {
			baseDao.delete(base);
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
			baseDao.delete(id);
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
			return baseDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById Base", e);
		}
	}
	public T findByName(String name) throws ServiceException {
		try {
			System.out.println("findbyname " + name + " " + baseDao);
			
			return baseDao.findByName(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findByName Base", e);
		}
	}
	public List<T> findAll() throws ServiceException {
		try {
			return baseDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Base", e);
		}
	}
	

	@Transactional
	public T translate(T base, Lang lang) throws ServiceException, InstantiationException, IllegalAccessException {
		T translated = (T) base.getClass().newInstance();

		Translation translation = base.getTranslation();
		if (translation == null){
			translation = translationDao.save(new Translation());
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
