package fr.cedricsevestre.service.engine.independant.objects;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.dao.engine.PositionDao;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.entity.engine.independant.objects.Position;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;

@Service
@Scope(value = "singleton")
@CustomService
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
	public Position findByNameForModelWithMaps(Translation model, String positionName) throws ServiceException {
		try {
			return positionDao.findByNameForModelWithMaps(model, positionName);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById position", e);
		}
	}
	
	public Position findByNameForModelsWithMaps(List<Translation> models, String positionName) throws ServiceException {
		try {
			return positionDao.findByNameForModelsWithMaps(models, positionName);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById position", e);
		}
	}
	
	public Integer countByNameForModelsWithMaps(List<Translation> models, String positionName) throws ServiceException {
		try {
			return positionDao.countByNameForModelsWithMaps(models, positionName);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById position", e);
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

	
	
	
	public Translation findByIdFetched(Integer id) throws ServiceException {
		try {
			return positionDao.findByIdFetched(id);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findByIdFetched", e);
		}
	}
	public List<Translation> findAllFetched() throws ServiceException {
		try {
			return positionDao.findAllFetched();
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}
	public Page<Translation> findAllFetched(Pageable pageable) throws ServiceException {
		try {
			return positionDao.findAllFetched(pageable);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}
	
}
