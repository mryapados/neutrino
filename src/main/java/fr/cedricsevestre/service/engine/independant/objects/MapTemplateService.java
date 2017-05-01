package fr.cedricsevestre.service.engine.independant.objects;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.constants.CacheConst;
import fr.cedricsevestre.dao.engine.MapTemplateDao;
import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;

@Service
@Scope(value = "singleton")
public class MapTemplateService extends BaseService<MapTemplate>{

	private Logger logger = Logger.getLogger(MapTemplateService.class);

	@Autowired
	private MapTemplateDao mapTemplateDao;

	@CacheEvict(value = {CacheConst.POSITION_MAPTEMPLATE}, allEntries = true)
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
	

	
	
	
	
	
	

}
