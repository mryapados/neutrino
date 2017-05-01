package fr.cedricsevestre.service.custom;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.dao.custom.EducationDao;
import fr.cedricsevestre.dao.custom.ExperienceDao;
import fr.cedricsevestre.dao.custom.SkillDao;
import fr.cedricsevestre.entity.custom.Education;
import fr.cedricsevestre.entity.custom.Experience;
import fr.cedricsevestre.entity.custom.Resume;
import fr.cedricsevestre.entity.custom.Skill;
import fr.cedricsevestre.entity.custom.Skill.SkillKind;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TranslationService;


@Service
@Scope(value = "singleton")
@CustomService
public class EducationService extends TranslationService<Education>{
	private Logger logger = Logger.getLogger(EducationService.class);

	@Autowired
	private EducationDao dao;

	public List<Education> findAllForFolderAndLang(Folder folder, Lang lang) throws ServiceException {
		try {
			return dao.findAllForFolderAndLang(folder, lang);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllForFolderAndLang Education", e);
		}
	}	
	

}
