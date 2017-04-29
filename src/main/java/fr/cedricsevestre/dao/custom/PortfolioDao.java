package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Category;
import fr.cedricsevestre.entity.custom.Experience;
import fr.cedricsevestre.entity.custom.Media;
import fr.cedricsevestre.entity.custom.Portfolio;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.custom.Resume;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Template;

@Repository
public interface PortfolioDao extends TranslationDao<Portfolio> {

	@Query("SELECT e FROM Portfolio e WHERE (e.folders IS EMPTY OR :folder IN elements(e.folders)) AND e.lang =:lang AND e.resume =:resume ORDER BY e.ordered DESC")
	Page<Portfolio> findAllForResumeAndFolderAndLang(@Param("resume") Resume resume, @Param("folder") Folder folder, @Param("lang") Lang lang, Pageable pageable);
	
}
