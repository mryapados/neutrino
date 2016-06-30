package fr.cedricsevestre.dao.engine;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.NoTranslation;

@Repository
public interface NoTranslationDao<T extends NoTranslation> extends BaseDao<T> {
	
//	@Query("SELECT t FROM #{#entityName} t WHERE t.name =:name OR (t.name =:name AND t.objectType = #{#entityName})")
//	T findByName(@Param("name") String name);
	
	@Query("SELECT t FROM #{#entityName} t WHERE t.name =:name AND t.objectType = #{#entityName}")
	T findByName(@Param("name") String name);
	
	@Query("SELECT nt FROM NoTranslation nt WHERE nt.objectType = :type")
	List<NoTranslation> findAllForType(@Param("type") String type);
}
