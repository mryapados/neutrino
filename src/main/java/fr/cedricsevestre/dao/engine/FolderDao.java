package fr.cedricsevestre.dao.engine;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.independant.objects.Folder;

@Repository
public interface FolderDao extends BaseDao<Folder> {
	
	@Query("SELECT p FROM Folder p WHERE p.name =:name")
	Folder findByName(@Param("name") String name);	
	
	@Query("SELECT p FROM Folder p WHERE :serverName IN elements(p.serverName)")
	Folder findByServerName(@Param("serverName") String serverName);	
	
}
