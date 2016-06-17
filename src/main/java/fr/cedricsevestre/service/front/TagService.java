package fr.cedricsevestre.service.front;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.front.TagDao;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class TagService{

	private Logger logger = Logger.getLogger(TagService.class);

	@Autowired
	private TagDao tagDao;

	@Transactional
	public Tag save(Tag tag) throws ServiceException {
		logger.debug("appel de la methode save Tag " + tag.getWord());
		try {
			return tagDao.save(tag);
		} catch (PersistenceException e) {
			logger.error("erreur save Tag " + e.getMessage());
			throw new ServiceException("erreur save Tag", e);
		}
	}

	@Transactional
	public void remove(Tag tag) throws ServiceException {
		logger.debug("appel de la methode remove Tag " + tag.getWord());
		try {
			tagDao.delete(tag);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Tag " + e.getMessage());
			throw new ServiceException("Tag id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Tag " + e.getMessage());
			throw new ServiceException("erreur remove Tag", e);
		} catch (Exception e) {
			logger.error("erreur remove Tag " + e.getMessage());
			throw new ServiceException("erreur remove Tag", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Tag id " + id);
		try {
			tagDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Tag " + e.getMessage());
			throw new ServiceException("Tag id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Tag " + e.getMessage());
			throw new ServiceException("erreur removeById Tag", e);
		} catch (Exception e) {
			logger.error("erreur remove Tag " + e.getMessage());
			throw new ServiceException("erreur removeById Tag", e);
		}
	}
	
	public Tag findById(Integer id) throws ServiceException {
		try {
			return tagDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById player", e);
		}
	}
	public List<Tag> findAll() throws ServiceException {
		try {
			return tagDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Tag", e);
		}
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public TagDao getTagDao() {
		return tagDao;
	}

	public void setTagDao(TagDao tagDao) {
		this.tagDao = tagDao;
	}






}
