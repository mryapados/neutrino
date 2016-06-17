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
import fr.cedricsevestre.dao.back.NSchemaDao;
import fr.cedricsevestre.entity.engine.NSchema;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class NSchemaService{

	private Logger logger = Logger.getLogger(NSchemaService.class);

	@Autowired
	private NSchemaDao nSchemaDao;

	@Transactional
	public NSchema save(NSchema nSchema) throws ServiceException {
		logger.debug("appel de la methode save NSchema " + nSchema.getId());
		try {
			return nSchemaDao.save(nSchema);
		} catch (PersistenceException e) {
			logger.error("erreur save NSchema " + e.getMessage());
			throw new ServiceException("erreur save NSchema", e);
		}
	}

	@Transactional
	public void remove(NSchema nSchema) throws ServiceException {
		logger.debug("appel de la methode remove NSchema " + nSchema.getId());
		try {
			nSchemaDao.delete(nSchema);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove NSchema " + e.getMessage());
			throw new ServiceException("NSchema id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove NSchema " + e.getMessage());
			throw new ServiceException("erreur remove NSchema", e);
		} catch (Exception e) {
			logger.error("erreur remove NSchema " + e.getMessage());
			throw new ServiceException("erreur remove NSchema", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById NSchema id " + id);
		try {
			nSchemaDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove NSchema " + e.getMessage());
			throw new ServiceException("NSchema id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove NSchema " + e.getMessage());
			throw new ServiceException("erreur removeById NSchema", e);
		} catch (Exception e) {
			logger.error("erreur remove NSchema " + e.getMessage());
			throw new ServiceException("erreur removeById NSchema", e);
		}
	}
	
	public NSchema findById(Integer id) throws ServiceException {
		try {
			return nSchemaDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById NSchema", e);
		}
	}
	
	public List<NSchema> findAll() throws ServiceException {
		try {
			return nSchemaDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll NSchema", e);
		}
	}
		
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public NSchemaDao getNSchemaDao() {
		return nSchemaDao;
	}

	public void setNSchemaDao(NSchemaDao nSchemaDao) {
		this.nSchemaDao = nSchemaDao;
	}






}
