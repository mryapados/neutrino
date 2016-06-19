package fr.cedricsevestre.dao.custom;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.custom.File;

@Repository
@Qualifier(value="frontEntityManagerFactory")
public interface FileDao extends JpaRepository<File, Integer> {
	
	@Query("SELECT f FROM File f WHERE f.name =:name")
	File FindByName(@Param("name") String name);
	
	
	
}
