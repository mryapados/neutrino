package fr.cedricsevestre.dao.custom;

import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.File;

@Repository
public interface FileDao extends TranslationDao<File> {
	
	
}
