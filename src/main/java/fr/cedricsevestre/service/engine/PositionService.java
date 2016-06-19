package fr.cedricsevestre.service.engine;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.engine.PositionDao;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.MapTemplate;
import fr.cedricsevestre.entity.engine.Position;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class PositionService extends BaseService<Position>{

	private Logger logger = Logger.getLogger(PositionService.class);

	@Autowired
	private PositionDao positionDao;

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
