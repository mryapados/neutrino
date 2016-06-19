package fr.cedricsevestre.dao.custom;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.custom.File;

@Repository
public interface FileDao extends TranslationDao<File> {
	
//	@Query("SELECT f FROM File f WHERE f.name =:name")
//	File FindByName(@Param("name") String name);
	
	
	
}
