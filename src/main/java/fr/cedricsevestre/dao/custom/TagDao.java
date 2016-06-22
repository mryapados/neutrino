package fr.cedricsevestre.dao.custom;

import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.NoTranslationDao;
import fr.cedricsevestre.entity.custom.Tag;

@Repository
public interface TagDao extends NoTranslationDao<Tag> {
	
	
}
