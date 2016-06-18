package fr.cedricsevestre.dao.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.MapTemplate;
import fr.cedricsevestre.entity.engine.NData;
import fr.cedricsevestre.entity.engine.NSchema;
import fr.cedricsevestre.entity.engine.Position;
import fr.cedricsevestre.entity.engine.Template;

@Repository
public interface NDataDao extends JpaRepository<NData, Integer> {
	@Query("SELECT nd FROM NData nd WHERE nd.template=:template ORDER BY nd.ordered, nd.id")
	List<NData> findAllForTemplate(@Param("template") Template template);
	
	@Query("SELECT nd FROM NData nd WHERE nd.mapTemplate=:mapTemplate ORDER BY nd.ordered, nd.id")
	List<NData> findAllForMapTemplate(@Param("mapTemplate") MapTemplate mapTemplate);

	
}
