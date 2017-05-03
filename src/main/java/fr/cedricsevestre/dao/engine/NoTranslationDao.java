package fr.cedricsevestre.dao.engine;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;

@Repository
public interface NoTranslationDao<T extends NoTranslation> extends BaseDao<T> {
	@Query("SELECT t FROM #{#entityName} t WHERE t.name =:name")
	T findByName(@Param("name") String name);
	
	@Query("SELECT t FROM #{#entityName} t WHERE (t.folders IS EMPTY OR :folder IN elements(t.folders)) AND (t.name =:name)")
	T identify(@Param("folder") Folder folder, @Param("name") String name);
	
	@Query("SELECT t FROM #{#entityName} t WHERE (t.folders IS EMPTY) AND (t.name =:name)")
	T identify(@Param("name") String name);
}
