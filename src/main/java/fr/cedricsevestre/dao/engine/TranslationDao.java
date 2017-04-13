package fr.cedricsevestre.dao.engine;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Repository
public interface TranslationDao<T extends Translation> extends BaseDao<T> {	
	
//	@Query("SELECT t FROM #{#entityName} t WHERE t.name =:name OR (t.name =:name AND t.objectType = #{#entityName})")
//	T findByName(@Param("name") String name);
	
	@Query("SELECT t FROM #{#entityName} t WHERE t.name =:name")
	T findByName(@Param("name") String name);

	 
	@Query("SELECT t FROM #{#entityName} t WHERE (t.folders IS EMPTY OR :folder IN elements(t.folders)) AND (t.name =:name AND t.lang =:lang)")
	T identify(@Param("folder") Folder folder, @Param("name") String name, @Param("lang") Lang lang);
	
	@Query("SELECT t FROM #{#entityName} t WHERE (t.folders IS EMPTY) AND (t.name =:name AND t.lang =:lang)")
	T identify(@Param("name") String name, @Param("lang") Lang lang);
	
}
