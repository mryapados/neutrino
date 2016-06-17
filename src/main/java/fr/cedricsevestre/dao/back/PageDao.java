package fr.cedricsevestre.dao.back;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.Page;

@Repository
@Qualifier(value="backEntityManagerFactory")
public interface PageDao extends JpaRepository<Page, Integer> {
	
	@Query("SELECT b FROM Page b WHERE b.name =:name")
	Page findByName(@Param("name") String name);
	
	
	
}
