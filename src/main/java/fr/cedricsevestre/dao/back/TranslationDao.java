package fr.cedricsevestre.dao.back;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.NSchema;
import fr.cedricsevestre.entity.engine.Translation;

@Repository
public interface TranslationDao extends JpaRepository<Translation, Integer> {
	
	
}
