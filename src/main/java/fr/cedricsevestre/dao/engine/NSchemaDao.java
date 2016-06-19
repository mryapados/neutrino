package fr.cedricsevestre.dao.engine;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.NData;
import fr.cedricsevestre.entity.engine.NSchema;

@Repository
public interface NSchemaDao extends BaseDao<NSchema> {
	
	
}
