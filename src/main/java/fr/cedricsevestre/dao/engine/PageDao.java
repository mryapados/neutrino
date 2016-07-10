package fr.cedricsevestre.dao.engine;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;

@Repository
public interface PageDao extends TranslationDao<Page> {

	@Query("SELECT e FROM Page e")
	List<Translation> findAllFetched();
	
}
