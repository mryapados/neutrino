package fr.cedricsevestre.dao.engine;

import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.Page;

@Repository
public interface PageDao extends TranslationDao<Page> {
	
}
