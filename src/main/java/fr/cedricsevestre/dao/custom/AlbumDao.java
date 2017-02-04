package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Template;

@Repository
public interface AlbumDao extends TranslationDao<Album> {
	
	
	
	
//	@Override
//	@EntityGraph("allJoins")
//    List<Album> findAll(Specification<Album> spec);
	
	
	
	
	@Override
	@Query("SELECT e FROM Album e LEFT JOIN FETCH e.files")
	List<Album> findAllFetched();

	@Override
	@Query(value = "SELECT e FROM Album e LEFT JOIN FETCH e.files a", countQuery = "select count(e) FROM Album e INNER JOIN e.files a INNER JOIN e.project p")
	Page<Album> findAllFetched(Pageable pageable);
	
//	@Override
//	@Query("SELECT e FROM Album e LEFT JOIN FETCH e.files")
//	List<Album> findAllFetched(Specification<Album> spec);
//
//	@Override
//	@Query(value = "SELECT e FROM Album e LEFT JOIN FETCH e.files a", countQuery = "select count(e) FROM Album e INNER JOIN e.files a INNER JOIN e.project p")
//	Page<Album> findAllFetched(Specification<Album> spec, Pageable pageable);
	
	@Override
	@Query(value = "SELECT e FROM Album e LEFT JOIN FETCH e.files a WHERE e.id =:id")
	Album findByIdFetched(@Param("id") Integer id);
}
