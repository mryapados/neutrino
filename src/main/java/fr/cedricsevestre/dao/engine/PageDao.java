package fr.cedricsevestre.dao.engine;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;

@Repository
public interface PageDao extends TranslationDao<Page> {
	@Override
	@Query("SELECT p FROM Page p LEFT JOIN FETCH p.model m")
	List<Page> findAllFetched();

	@Override
	@Query(value = "SELECT p FROM Page p LEFT JOIN FETCH p.model m ", countQuery = "select count(p) FROM Page p")
	org.springframework.data.domain.Page<Page> findAllFetched(Pageable pageable);
	
	@Override
	@Query("SELECT p FROM Page p LEFT JOIN FETCH p.model m")
	List<Page> findAllFetched(Specification<Page> spec);

	@Override
	@Query(value = "SELECT p FROM Page p LEFT JOIN FETCH p.model m ", countQuery = "select count(p) FROM Page p")
	org.springframework.data.domain.Page<Page> findAllFetched(Specification<Page> spec, Pageable pageable);

	@Override
	@Query(value = "SELECT p FROM Page p LEFT JOIN FETCH p.model m WHERE p.id =:id")
	Page findByIdFetched(@Param("id") Integer id);
	
}
