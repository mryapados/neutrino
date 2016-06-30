package fr.cedricsevestre.dao.engine;

import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.translation.objects.Page;

@Repository
public interface PageDao extends TranslationDao<Page> {
	
}
