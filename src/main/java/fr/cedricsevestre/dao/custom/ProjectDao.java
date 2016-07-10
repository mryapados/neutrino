package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Repository
public interface ProjectDao extends TranslationDao<Project> {
	
	@Query("SELECT e FROM Project e LEFT JOIN FETCH e.albums")
	List<Translation> findAllFetched();
	
}
