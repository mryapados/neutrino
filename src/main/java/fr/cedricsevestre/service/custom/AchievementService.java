package fr.cedricsevestre.service.custom;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.dao.custom.AchievementDao;
import fr.cedricsevestre.entity.custom.Achievement;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@Service
@CustomService
public class AchievementService extends TranslationService<Achievement>{
	private Logger logger = Logger.getLogger(EducationService.class);

	@Autowired
	private AchievementDao dao;

	public List<Achievement> findAllForFolderAndLang(Folder folder, Lang lang) throws ServiceException {
		try {
			return dao.findAllForFolderAndLang(folder, lang);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllForFolderAndLang Education", e);
		}
	}	
}
