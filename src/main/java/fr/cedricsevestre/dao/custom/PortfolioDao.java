package fr.cedricsevestre.dao.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Portfolio;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;

@Repository
public interface PortfolioDao extends TranslationDao<Portfolio> {

	@Query("SELECT e FROM Portfolio e WHERE (e.folders IS EMPTY OR :folder IN elements(e.folders)) AND e.lang =:lang ORDER BY e.ordered DESC")
	Page<Portfolio> findAllForFolderAndLang(@Param("folder") Folder folder, @Param("lang") Lang lang, Pageable pageable);
	
}
