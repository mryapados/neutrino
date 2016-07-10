package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Repository
public interface AlbumDao extends TranslationDao<Album> {
	
	@Query("SELECT e FROM Album e LEFT JOIN FETCH e.files")
	List<Translation> findAllFetched();
	
}
