package fr.cedricsevestre.service.custom;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.dao.custom.AlbumDao;
import fr.cedricsevestre.dao.custom.ProjectDao;
import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@Service
@Scope(value = "singleton")
@CustomService
public class AlbumService extends TranslationService<Album>{

	@Autowired
	AlbumDao albumDao;

	@Override
	public List<Translation> findAllFetched() throws ServiceException {
		try {
			return albumDao.findAllFetched();
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}


}
