package fr.cedricsevestre.dao.engine;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Template;

@Repository
public interface TemplateDao extends TranslationDao<Template> {
	
	@Query("SELECT t FROM Template t LEFT JOIN FETCH t.models m LEFT JOIN t.blocks b LEFT JOIN FETCH t.translation tr LEFT JOIN FETCH tr.translations trs WHERE t.name =:name")
	Template findByNameWithAllExceptData(@Param("name") String name);
		
	@Query("SELECT t FROM Template t WHERE t.kind ='BLOCK' AND t.models IS EMPTY")
	List<Template> findAllBlockNotAffected();
	
	@Query("SELECT t FROM Template t WHERE t.kind ='BLOCK' OR t.kind = 'PAGEBLOCK'")
	List<Template> findAllBlockAndPageBlock();
	
	@Query("SELECT t FROM Template t LEFT JOIN FETCH t.models m")
	List<Template> findAllWithModels();
	
	@Override
	@Query("SELECT t FROM Template t LEFT JOIN FETCH t.models m LEFT JOIN FETCH t.blocks b")
	List<Template> findAllFetched();

	@Override
	@Query(value = "SELECT t FROM Template t LEFT JOIN FETCH t.models m LEFT JOIN FETCH t.blocks b", countQuery = "select count(t) FROM Template t")
	Page<Template> findAllFetched(Pageable pageable);
	
//	@Override
//	@Query("SELECT t FROM Template t LEFT JOIN FETCH t.models m LEFT JOIN FETCH t.blocks b")
//	List<Template> findAllFetched(Specification<Template> spec);
//
//	@Override
//	@Query(value = "SELECT t FROM Template t LEFT JOIN FETCH t.models m LEFT JOIN FETCH t.blocks b", countQuery = "select count(t) FROM Template t")
//	Page<Template> findAllFetched(Specification<Template> spec, Pageable pageable);

	@Override
	@Query(value = "SELECT t FROM Template t LEFT JOIN FETCH t.models m LEFT JOIN FETCH t.blocks b WHERE t.id =:id")
	Template findByIdFetched(@Param("id") Integer id);
	
	
	
	
}
