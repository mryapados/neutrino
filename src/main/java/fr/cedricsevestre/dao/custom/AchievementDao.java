package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Achievement;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;

@Repository
public interface AchievementDao extends TranslationDao<Achievement> {
	@Query("SELECT e FROM Achievement e WHERE (e.folders IS EMPTY OR :folder IN elements(e.folders)) AND e.lang =:lang ORDER BY e.ordered DESC")
	List<Achievement> findAllForFolderAndLang(@Param("folder") Folder folder, @Param("lang") Lang lang);

}
