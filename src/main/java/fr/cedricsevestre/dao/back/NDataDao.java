package fr.cedricsevestre.dao.back;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.back.NData;
import fr.cedricsevestre.entity.back.NSchema;

@Repository
@Qualifier(value="backEntityManagerFactory")
public interface NDataDao extends JpaRepository<NData, Integer> {
	
	
}
