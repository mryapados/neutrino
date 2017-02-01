package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.custom.File;
import fr.cedricsevestre.entity.engine.IdProvider;

@Repository
public interface FileDao extends TranslationDao<File> {
	
	@Override
	@Query("SELECT e FROM File e LEFT JOIN FETCH e.tags")
	List<File> findAllFetched();
	
	@Override
	@Query(value = "SELECT e FROM File e LEFT JOIN FETCH e.tags a", countQuery = "select count(e) FROM File e")
	Page<File> findAllFetched(Pageable pageable);
	
	@Override
	@Query("SELECT e FROM File e LEFT JOIN FETCH e.tags")
	List<File> findAllFetched(Specification<File> spec);
	
	@Override
	@Query(value = "SELECT e FROM File e LEFT JOIN FETCH e.tags a", countQuery = "select count(e) FROM File e")
	Page<File> findAllFetched(Specification<File> spec, Pageable pageable);
	
	@Override
	@Query(value = "SELECT e FROM File e LEFT JOIN FETCH e.tags a WHERE e.id =:id")
	File findByIdFetched(@Param("id") Integer id);
}
