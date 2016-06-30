package fr.cedricsevestre.dao.engine;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.independant.objects.NData;
import fr.cedricsevestre.entity.engine.translation.objects.Template;

@Repository
public interface NDataDao extends BaseDao<NData> {
	@Query("SELECT nd FROM NData nd WHERE nd.template=:template ORDER BY nd.ordered, nd.id")
	List<NData> findAllForTemplate(@Param("template") Template template);
	
	@Query("SELECT nd FROM NData nd WHERE nd.mapTemplate=:mapTemplate ORDER BY nd.ordered, nd.id")
	List<NData> findAllForMapTemplate(@Param("mapTemplate") MapTemplate mapTemplate);

	
}
