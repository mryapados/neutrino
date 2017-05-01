package fr.cedricsevestre.service.custom;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.dao.custom.CategoryDao;
import fr.cedricsevestre.dao.custom.SkillDao;
import fr.cedricsevestre.entity.custom.Category;
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
public class SkillService extends TranslationService<Skill>{
	private Logger logger = Logger.getLogger(SkillService.class);

	@Autowired
	private SkillDao dao;

	public List<Skill> findAllKindForFolderAndLang(SkillKind kind, Folder folder, Lang lang) throws ServiceException {
		try {
			return dao.findAllKindForFolderAndLang(kind, folder, lang);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllKindForReumeAdFolderAndLang Skill", e);
		}
	}	
	

	
	
}
