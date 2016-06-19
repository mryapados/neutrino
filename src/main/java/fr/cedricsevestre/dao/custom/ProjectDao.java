package fr.cedricsevestre.dao.custom;

import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Project;

@Repository
public interface ProjectDao extends TranslationDao<Project> {
	

}
