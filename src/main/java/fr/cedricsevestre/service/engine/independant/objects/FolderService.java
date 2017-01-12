package fr.cedricsevestre.service.engine.independant.objects;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOService;
import fr.cedricsevestre.dao.engine.FolderDao;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.Position;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;

@BOService
@Service
@Scope(value = "singleton")
public class FolderService extends BaseService<Folder>{

	private Logger logger = Logger.getLogger(FolderService.class);

	@Autowired
	private FolderDao folderDao;

	public Folder findByName(String name) throws ServiceException {
		try {		
			return folderDao.findByName(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById position", e);
		}
	}

	public Folder findByServerName(String serverName) throws ServiceException {
		try {		
			return folderDao.findByServerName(serverName);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById position", e);
		}
	}


}
