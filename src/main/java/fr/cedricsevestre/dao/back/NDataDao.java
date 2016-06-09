package fr.cedricsevestre.dao.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.back.MapTemplate;
import fr.cedricsevestre.entity.back.NData;
import fr.cedricsevestre.entity.back.NSchema;
import fr.cedricsevestre.entity.back.Position;
import fr.cedricsevestre.entity.back.Template;

@Repository
@Qualifier(value="backEntityManagerFactory")
public interface NDataDao extends JpaRepository<NData, Integer> {
	@Query("SELECT nd FROM NData nd WHERE nd.template=:template ORDER BY nd.ordered, nd.id")
	List<NData> findAllForTemplate(@Param("template") Template template);
}
