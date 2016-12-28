package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.File;
import fr.cedricsevestre.entity.engine.IdProvider;

@Repository
public interface FileDao extends TranslationDao<File> {
	@Override
	@Query("SELECT e FROM File e LEFT JOIN FETCH e.tags")
	List<IdProvider> findAllFetched();

	@Override
	@Query(value = "SELECT e FROM File e LEFT JOIN FETCH e.tags a", countQuery = "select count(e) FROM File e")
	Page<IdProvider> findAllFetched(Pageable pageable);
	
	@Query(value = "SELECT e FROM File e LEFT JOIN FETCH e.tags a WHERE e.id =:id")
	IdProvider findByIdFetched(@Param("id") Integer id);
}
