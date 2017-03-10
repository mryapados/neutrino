package fr.cedricsevestre.dao.engine;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.translation.Lang;

@Repository
public interface LangDao extends BaseDao<Lang> {
	@Query("SELECT e FROM #{#entityName} e WHERE e.code =:code")
	Lang findByCode(@Param("code") String code);
}
