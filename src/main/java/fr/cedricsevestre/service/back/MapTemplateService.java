package fr.cedricsevestre.service.back;

import java.util.HashSet;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.back.MapTemplateDao;
import fr.cedricsevestre.entity.engine.MapTemplate;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class MapTemplateService{

	private Logger logger = Logger.getLogger(MapTemplateService.class);

	@Autowired
	private MapTemplateDao mapTemplateDao;

	@Transactional
	public MapTemplate save(MapTemplate mapTemplate) throws ServiceException {
		logger.debug("appel de la methode save Map " + mapTemplate.getId());
		try {
			return mapTemplateDao.save(mapTemplate);
		} catch (PersistenceException e) {
			logger.error("erreur save Map " + e.getMessage());
			throw new ServiceException("erreur save Map", e);
		}
	}
	
	@Transactional
	public MapTemplate saveAndOrder(MapTemplate mapTemplate) throws ServiceException {
		logger.debug("appel de la methode save Map " + mapTemplate.getId());
		try {
			
			System.out.println(mapTemplate.getModel().toString());
			System.out.println(mapTemplate.getBlock().toString());
			System.out.println(mapTemplate.getPosition().toString());
			
			Integer step = 10;
			Integer origine = mapTemplate.getOrdered();
			Integer order = step;
			
			if (origine < 0) {
				mapTemplate.setOrdered(order);
				order += step;
			}
			List<MapTemplate> mapTemplates = mapTemplateDao.findAllForModelAndPosition(mapTemplate.getModel(), mapTemplate.getPosition());
			for (MapTemplate orderedMapTemplate : mapTemplates) {
				orderedMapTemplate.setOrdered(order);
				mapTemplateDao.save(orderedMapTemplate);
				order += step;
			}
			if (origine >= 0) {
				mapTemplate.setOrdered(order);
				order += step;
			}
			return mapTemplateDao.save(mapTemplate);
			
		} catch (PersistenceException e) {
			logger.error("erreur save Map " + e.getMessage());
			throw new ServiceException("erreur save Map", e);
		}
	}

	@Transactional
	public void remove(MapTemplate mapTemplate) throws ServiceException {
		logger.debug("appel de la methode remove Map " + mapTemplate.getId());
		try {
			mapTemplateDao.delete(mapTemplate);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Map " + e.getMessage());
			throw new ServiceException("Map id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Map " + e.getMessage());
			throw new ServiceException("erreur remove Map", e);
		} catch (Exception e) {
			logger.error("erreur remove Map " + e.getMessage());
			throw new ServiceException("erreur remove Map", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Map id " + id);
		try {
			mapTemplateDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Map " + e.getMessage());
			throw new ServiceException("Map id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Map " + e.getMessage());
			throw new ServiceException("erreur removeById Map", e);
		} catch (Exception e) {
			logger.error("erreur remove Map " + e.getMessage());
			throw new ServiceException("erreur removeById Map", e);
		}
	}
	
	public MapTemplate findById(Integer id) throws ServiceException {
		try {
			return mapTemplateDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById player", e);
		}
	}
	public List<MapTemplate> findAll() throws ServiceException {
		try {
			return mapTemplateDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Map", e);
		}
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public MapTemplateDao getMapDao() {
		return mapTemplateDao;
	}

	public void setMapDao(MapTemplateDao mapTemplateDao) {
		this.mapTemplateDao = mapTemplateDao;
	}






}
