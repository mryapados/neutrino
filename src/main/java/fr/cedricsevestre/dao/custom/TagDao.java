package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.NoTranslationDao;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Repository
public interface TagDao extends NoTranslationDao<Tag> {
	@Override
	@Query("SELECT e FROM Tag e LEFT JOIN FETCH e.files")
	List<Tag> findAllFetched();
	
	@Override
	@Query(value = "SELECT e FROM Tag e LEFT JOIN FETCH e.files a", countQuery = "select count(e) FROM Tag e")
	Page<Tag> findAllFetched(Pageable pageable);
	
	@Override
	@Query("SELECT e FROM Tag e LEFT JOIN FETCH e.files")
	List<Tag> findAllFetched(Specification<Tag> spec);
	
	@Override
	@Query(value = "SELECT e FROM Tag e LEFT JOIN FETCH e.files a", countQuery = "select count(e) FROM Tag e")
	Page<Tag> findAllFetched(Specification<Tag> spec, Pageable pageable);
	
	@Query(value = "SELECT e FROM Tag e LEFT JOIN FETCH e.files a WHERE e.id =:id")
	Tag findByIdFetched(@Param("id") Integer id);
}
