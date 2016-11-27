package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Repository
public interface AlbumDao extends TranslationDao<Album> {
	@Override
	@Query("SELECT e FROM Album e LEFT JOIN FETCH e.files")
	List<IdProvider> findAllFetched();

	@Override
	@Query(value = "SELECT e FROM Album e LEFT JOIN FETCH e.files a", countQuery = "select count(e) FROM Album e")
	Page<IdProvider> findAllFetched(Pageable pageable);
	
	@Query(value = "SELECT e FROM Album e LEFT JOIN FETCH e.files a WHERE e.id =:id")
	IdProvider findByIdFetched(@Param("id") Integer id);
}
