package fr.cedricsevestre.dao.back;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.Base;

@Repository
//@Qualifier(value="backEntityManagerFactory")
public interface BaseDao<T extends Base> extends JpaRepository<T, Integer> {
	
	@Query("SELECT t FROM Base t WHERE t.name =:name")
	T findByName(@Param("name") String name);
	
	
}
