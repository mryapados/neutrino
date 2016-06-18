package fr.cedricsevestre.dao.back;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import fr.cedricsevestre.entity.engine.Base;

@NoRepositoryBean
public interface BaseDao<T extends Base> extends JpaRepository<T, Integer> {
	
//	@Query("SELECT t FROM #{#entityName} t WHERE t.name =:name OR (t.name =:name AND t.objectType = #{#entityName})")
//	T findByName(@Param("name") String name);
	
	@Query("SELECT t FROM #{#entityName} t WHERE t.name =:name AND t.objectType = #{#entityName}")
	T findByName(@Param("name") String name);
}
