package fr.cedricsevestre.service.custom;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.dao.custom.MarkerDao;
import fr.cedricsevestre.dao.custom.TagDao;
import fr.cedricsevestre.entity.custom.Marker;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;
import fr.cedricsevestre.service.engine.notranslation.NoTranslationService;

@Service
@Scope(value = "singleton")
@CustomService
public class MarkerService extends NoTranslationService<Marker>{

	@Autowired
	MarkerDao markerDao;

	@Override
	public List<NoTranslation> findAllFetched() throws ServiceException {
		try {
			return markerDao.findAllFetched();
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}

}
