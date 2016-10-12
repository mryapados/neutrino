package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Repository
public interface ProjectDao extends TranslationDao<Project> {
	@Override
	@Query("SELECT e FROM Project e LEFT JOIN FETCH e.albums a")
	List<Translation> findAllFetched();

	@Override
	@Query(value = "SELECT e FROM Project e LEFT JOIN FETCH e.albums a", countQuery = "select count(e) FROM Project e")
	Page<Translation> findAllFetched(Pageable pageable);
}
