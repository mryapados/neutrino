package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Resume;
import fr.cedricsevestre.entity.custom.SocialNetwork;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;

@Repository
public interface SocialNetworkDao extends TranslationDao<SocialNetwork> {
	
	@Query("SELECT e FROM SocialNetwork e WHERE (e.folders IS EMPTY OR :folder IN elements(e.folders)) AND e.lang =:lang ORDER BY e.ordered DESC")
	List<SocialNetwork> findAllForFolderAndLang(@Param("folder") Folder folder, @Param("lang") Lang lang);
	
}
