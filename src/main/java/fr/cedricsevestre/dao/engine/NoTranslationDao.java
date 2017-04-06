package fr.cedricsevestre.dao.engine;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Repository
public interface NoTranslationDao<T extends NoTranslation> extends BaseDao<T> {
	
	@Query("SELECT t FROM #{#entityName} t WHERE t.name =:name")
	T findByName(@Param("name") String name);
	
//	@Query("SELECT nt FROM NoTranslation nt WHERE nt.objectType = :type")
//	List<NoTranslation> findAllForType(@Param("type") String type);
	
	@Query("SELECT t FROM #{#entityName} t WHERE (t.folder IS NULL OR t.folder.id =:folderId) AND (t.name =:name)")
	T identify(@Param("folderId") Integer folderId, @Param("name") String name);
	
	
}
