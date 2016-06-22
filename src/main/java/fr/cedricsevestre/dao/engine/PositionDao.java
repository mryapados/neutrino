package fr.cedricsevestre.dao.engine;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.Position;
import fr.cedricsevestre.entity.engine.Template;

@Repository
public interface PositionDao extends BaseDao<Position> {
	
	@Query("SELECT p FROM Position p WHERE p.name =:name")
	Position findByName(@Param("name") String name);
	
	@Deprecated
	@Query("SELECT p FROM Position p LEFT JOIN FETCH p.mapTemplates m WHERE p.name =:name ORDER BY m.ordered")
	Position findByNameWithMaps(@Param("name") String name);
	
	@Query("SELECT p FROM Position p LEFT JOIN FETCH p.mapTemplates m WHERE (p.name =:position AND m.model =:model) ORDER BY m.ordered")
	Position findByNameForModelWithMaps(@Param("model") Template model, @Param("position") String positionName);

	
	@Query("SELECT p FROM Position p LEFT JOIN FETCH p.mapTemplates m WHERE m.model =:model ORDER BY m.ordered")
	List<Position> findAllForModelWithMaps(@Param("model") Template model);
	
	@Deprecated
	@Query("SELECT p FROM Position p LEFT JOIN FETCH p.mapTemplates m ORDER BY m.ordered")
	List<Position> findAllWithMaps();
	
	@Query("SELECT p FROM Position p LEFT JOIN FETCH p.mapTemplates m WHERE m.model !=:model")
	List<Position> findAllEmptyWithMaps(@Param("model") Template model);
	
	
	
}
