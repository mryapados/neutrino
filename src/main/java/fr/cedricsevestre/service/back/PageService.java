package fr.cedricsevestre.service.back;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.back.PageDao;
import fr.cedricsevestre.entity.back.Page;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class PageService{

	private Logger logger = Logger.getLogger(PageService.class);

	@Autowired
	private PageDao pageDao;

	@Transactional
	public Page save(Page page) throws ServiceException {
		logger.debug("appel de la methode save Page " + page.getName());
		try {
			return pageDao.save(page);
		} catch (PersistenceException e) {
			logger.error("erreur save Page " + e.getMessage());
			throw new ServiceException("erreur save Page", e);
		}
	}

	@Transactional
	public void remove(Page page) throws ServiceException {
		logger.debug("appel de la methode remove Page " + page.getName());
		try {
			pageDao.delete(page);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Page " + e.getMessage());
			throw new ServiceException("Page id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Page " + e.getMessage());
			throw new ServiceException("erreur remove Page", e);
		} catch (Exception e) {
			logger.error("erreur remove Page " + e.getMessage());
			throw new ServiceException("erreur remove Page", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Page id " + id);
		try {
			pageDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Page " + e.getMessage());
			throw new ServiceException("Page id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Page " + e.getMessage());
			throw new ServiceException("erreur removeById Page", e);
		} catch (Exception e) {
			logger.error("erreur remove Page " + e.getMessage());
			throw new ServiceException("erreur removeById Page", e);
		}
	}
	
	public Page findById(Integer id) throws ServiceException {
		try {
			return pageDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById player", e);
		}
	}
	public Page findByName(String name) throws ServiceException {
		try {
			return pageDao.findByName(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById player", e);
		}
	}
	
	
	public List<Page> findAll() throws ServiceException {
		try {
			return pageDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Page", e);
		}
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}






}
