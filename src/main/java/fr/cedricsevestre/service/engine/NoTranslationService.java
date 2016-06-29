package fr.cedricsevestre.service.engine;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.engine.NoTranslationDao;
import fr.cedricsevestre.entity.engine.NoTranslation;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public abstract class NoTranslationService<T extends NoTranslation> implements IBaseService<T>{

	private Logger logger = Logger.getLogger(NoTranslationService.class);

	@Autowired
	private NoTranslationDao<T> noTranslationDao;

	@Override
	@Transactional
	public T save(T base) throws ServiceException {
		logger.debug("appel de la methode save Base ");
		try {
			return noTranslationDao.save(base);
		} catch (PersistenceException e) {
			logger.error("erreur save Base " + e.getMessage());
			throw new ServiceException("erreur save Base", e);
		}
	}

	@Override
	@Transactional
	public void remove(T base) throws ServiceException {
		logger.debug("appel de la methode remove Base ");
		try {
			noTranslationDao.delete(base);
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

	@Override
	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Base id " + id);
		try {
			noTranslationDao.delete(id);
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
	
	@Override
	public T findById(Integer id) throws ServiceException {
		try {
			return noTranslationDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById Base", e);
		}
	}
	
	@Override
	public List<T> findAll() throws ServiceException {
		try {
			return noTranslationDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Base", e);
		}
	}

	public T findByName(String name) throws ServiceException {
		try {
			System.out.println("findbyname " + name + " " + noTranslationDao);
			
			return noTranslationDao.findByName(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findByName Base", e);
		}
	}


}
