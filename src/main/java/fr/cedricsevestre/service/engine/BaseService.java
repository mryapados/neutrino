package fr.cedricsevestre.service.engine;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.engine.BaseDao;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public abstract class BaseService<T>{

	private Logger logger = Logger.getLogger(BaseService.class);

	@Autowired
	private BaseDao<T> baseDao;

	@Transactional
	public T save(T base) throws ServiceException {
		logger.debug("appel de la methode save Base ");
		try {
			return baseDao.save(base);
		} catch (PersistenceException e) {
			logger.error("erreur save Base " + e.getMessage());
			throw new ServiceException("erreur save Base", e);
		}
	}

	@Transactional
	public void remove(T base) throws ServiceException {
		logger.debug("appel de la methode remove Base ");
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
	public List<T> findAll() throws ServiceException {
		try {
			return baseDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Base", e);
		}
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}









}
