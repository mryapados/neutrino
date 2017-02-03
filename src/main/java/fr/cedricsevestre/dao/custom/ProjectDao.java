package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.File;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Repository
public interface ProjectDao extends TranslationDao<Project> {
	@Override
	@Query("SELECT e FROM Project e LEFT JOIN FETCH e.albums a")
	List<Project> findAllFetched();

	@Override
	@Query(value = "SELECT e FROM Project e LEFT JOIN FETCH e.albums a", countQuery = "select count(e) FROM Project e")
	Page<Project> findAllFetched(Pageable pageable);
	
//	@Override
//	@Query("SELECT e FROM Project e LEFT JOIN FETCH e.albums a")
//	List<Project> findAllFetched(Specification<Project> spec);
//
//	@Override
//	@Query(value = "SELECT e FROM Project e LEFT JOIN FETCH e.albums a", countQuery = "select count(e) FROM Project e")
//	Page<Project> findAllFetched(Specification<Project> spec, Pageable pageable);
	
	@Override
	@Query(value = "SELECT e FROM Project e LEFT JOIN FETCH e.albums a WHERE e.id =:id")
	Project findByIdFetched(@Param("id") Integer id);
}
