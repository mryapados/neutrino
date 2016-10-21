package fr.cedricsevestre.dao.engine;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Page;

@Repository
public interface PageDao extends TranslationDao<Page> {

	@Override
	@Query("SELECT p FROM Page p LEFT JOIN FETCH p.model m")
	List<Translation> findAllFetched();

	@Override
	@Query(value = "SELECT p FROM Page p LEFT JOIN FETCH p.model m ", countQuery = "select count(p) FROM Page p")
	org.springframework.data.domain.Page<Translation> findAllFetched(Pageable pageable);
	
	
	@Query(value = "SELECT p FROM Page p LEFT JOIN FETCH p.model m WHERE p.id =:id")
	Translation findByIdFetched(@Param("id") Integer id);
	
}
