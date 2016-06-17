package fr.cedricsevestre.service.back;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.back.PositionDao;
import fr.cedricsevestre.entity.engine.MapTemplate;
import fr.cedricsevestre.entity.engine.Position;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class PositionService{

	private Logger logger = Logger.getLogger(PositionService.class);

	@Autowired
	private PositionDao positionDao;

	@Autowired
	private TemplateService templateService;
	
	@Transactional
	public Position save(Position position) throws ServiceException {
		logger.debug("appel de la methode save Position " + position.getName());
		try {
			return positionDao.save(position);
		} catch (PersistenceException e) {
			logger.error("erreur save Position " + e.getMessage());
			throw new ServiceException("erreur save Position", e);
		}
	}

	@Transactional
	public void remove(Position position) throws ServiceException {
		logger.debug("appel de la methode remove Position " + position.getName());
		try {
			positionDao.delete(position);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Position " + e.getMessage());
			throw new ServiceException("Position id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Position " + e.getMessage());
			throw new ServiceException("erreur remove Position", e);
		} catch (Exception e) {
			logger.error("erreur remove Position " + e.getMessage());
			throw new ServiceException("erreur remove Position", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Position id " + id);
		try {
			positionDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Position " + e.getMessage());
			throw new ServiceException("Position id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Position " + e.getMessage());
			throw new ServiceException("erreur removeById Position", e);
		} catch (Exception e) {
			logger.error("erreur remove Position " + e.getMessage());
			throw new ServiceException("erreur removeById Position", e);
		}
	}
	
	public Position findById(Integer id) throws ServiceException {
		try {
			return positionDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById position", e);
		}
	}
	public Position findByName(String name) throws ServiceException {
		try {		
			return positionDao.findByName(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById position", e);
		}
	}
	
	@Deprecated
	public Position findByNameWithMaps(String name) throws ServiceException {
		try {
			return positionDao.findByNameWithMaps(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById position", e);
		}
	}
	public Position findByNameForModelWithMaps(Template model, String positionName) throws ServiceException {
		try {
			
//			Position pos = positionDao.findByNameWithMaps(model, position);
//			if (pos == null) System.out.println("model = " + model.getName() + ", position = " + position + ", NULL");
//			else System.out.println("model = " + model.getName() + ", position = " + position + ", " + pos.getName());
			
			return positionDao.findByNameForModelWithMaps(model, positionName);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById position", e);
		}
	}
	
	public List<Position> findAll() throws ServiceException {
		try {
			return positionDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Position", e);
		}
	}
	
	public List<Position> findAllWithMaps() throws ServiceException {
		try {
			return positionDao.findAllWithMaps();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Position", e);
		}
	}
	
	public List<Position> findAllForModelWithMaps(Template modelName) throws ServiceException {
		try {
			return positionDao.findAllForModelWithMaps(modelName);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllForModelWithMaps Position", e);
		}
	}
	
	public List<Position> findAllEmptyWithMaps(Template modelName) throws ServiceException {
		try {
			return positionDao.findAllEmptyWithMaps(modelName);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllEmptyWithMaps Position", e);
		}
	}
	
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public PositionDao getPositionDao() {
		return positionDao;
	}

	public void setPositionDao(PositionDao positionDao) {
		this.positionDao = positionDao;
	}






}
