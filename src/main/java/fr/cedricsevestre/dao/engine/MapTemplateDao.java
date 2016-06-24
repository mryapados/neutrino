package fr.cedricsevestre.dao.engine;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.MapTemplate;
import fr.cedricsevestre.entity.engine.Position;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.entity.engine.Translation;

@Repository
public interface MapTemplateDao extends BaseDao<MapTemplate> {
	@Query("SELECT mt FROM MapTemplate mt WHERE mt.model=:model AND mt.position=:position ORDER BY mt.ordered")
	List<MapTemplate> findAllForModelAndPosition(@Param("model") Translation model, @Param("position") Position position);
}
