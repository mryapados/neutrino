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
import fr.cedricsevestre.dao.back.NDataDao;
import fr.cedricsevestre.entity.back.NData;
import fr.cedricsevestre.entity.back.Template;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class NDataService{

	private Logger logger = Logger.getLogger(NDataService.class);

	@Autowired
	private NDataDao nDataDao;

	@Transactional
	public NData save(NData nData) throws ServiceException {
		logger.debug("appel de la methode save NData " + nData.getId());
		try {
			return nDataDao.save(nData);
		} catch (PersistenceException e) {
			logger.error("erreur save NData " + e.getMessage());
			throw new ServiceException("erreur save NData", e);
		}
	}

	@Transactional
	public void remove(NData nData) throws ServiceException {
		logger.debug("appel de la methode remove NData " + nData.getId());
		try {
			nDataDao.delete(nData);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove NData " + e.getMessage());
			throw new ServiceException("NData id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove NData " + e.getMessage());
			throw new ServiceException("erreur remove NData", e);
		} catch (Exception e) {
			logger.error("erreur remove NData " + e.getMessage());
			throw new ServiceException("erreur remove NData", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById NData id " + id);
		try {
			nDataDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove NData " + e.getMessage());
			throw new ServiceException("NData id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove NData " + e.getMessage());
			throw new ServiceException("erreur removeById NData", e);
		} catch (Exception e) {
			logger.error("erreur remove NData " + e.getMessage());
			throw new ServiceException("erreur removeById NData", e);
		}
	}
	
	public NData findById(Integer id) throws ServiceException {
		try {
			return nDataDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById NData", e);
		}
	}
	
	public List<NData> findAll() throws ServiceException {
		try {
			return nDataDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll NData", e);
		}
	}
	
	public List<NData> findAllForTemplate(Template template) throws ServiceException {
		try {
			return nDataDao.findAllForTemplate(template);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll NData", e);
		}
	}
	
		
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public NDataDao getNDataDao() {
		return nDataDao;
	}

	public void setNDataDao(NDataDao nDataDao) {
		this.nDataDao = nDataDao;
	}






}
