package fr.cedricsevestre.dao.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.Template;

@Repository
public interface TemplateDao extends BaseDao<Template> {
	
//	@Query("SELECT t FROM Template t WHERE t.name =:name")
//	Template findByName(@Param("name") String name);
	
	@Query("SELECT t FROM Template t LEFT JOIN FETCH t.models m LEFT JOIN t.blocks b LEFT JOIN FETCH t.translation tr LEFT JOIN FETCH tr.translations trs WHERE t.name =:name")
	Template findByNameWithAllExceptData(@Param("name") String name);
		
	@Query("SELECT t FROM Template t WHERE t.type ='BLOCK' AND t.models IS EMPTY")
	List<Template> findAllBlockNotAffected();
	
	@Query("SELECT t FROM Template t WHERE t.type ='BLOCK' OR t.type = 'PAGEBLOCK'")
	List<Template> findAllBlockAndPageBlock();
	
	@Query("SELECT t FROM Template t LEFT JOIN FETCH t.models m")
	List<Template> findAllWithModels();
	
	
	
	
	
}
