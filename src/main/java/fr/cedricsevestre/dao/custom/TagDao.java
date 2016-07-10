package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.NoTranslationDao;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Repository
public interface TagDao extends NoTranslationDao<Tag> {
	
	@Query("SELECT e FROM Tag e LEFT JOIN FETCH e.files")
	List<NoTranslation> findAllFetched();
	
}
