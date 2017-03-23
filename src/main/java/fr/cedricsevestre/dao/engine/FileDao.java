package fr.cedricsevestre.dao.engine;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.independant.objects.File;

@Repository
public interface FileDao extends BaseDao<File> {
	
	@Query("SELECT f FROM File f WHERE f.path LIKE :path")
	List<File> findByPath(@Param("path") String path);	

}
