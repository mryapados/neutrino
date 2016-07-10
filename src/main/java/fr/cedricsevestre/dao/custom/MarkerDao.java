package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.NoTranslationDao;
import fr.cedricsevestre.entity.custom.Marker;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;

@Repository
public interface MarkerDao extends NoTranslationDao<Marker> {
	
	
	@Query("SELECT e FROM Marker e")
	List<NoTranslation> findAllFetched();
	
}
