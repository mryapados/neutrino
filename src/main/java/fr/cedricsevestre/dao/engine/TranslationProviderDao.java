package fr.cedricsevestre.dao.engine;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.NSchema;
import fr.cedricsevestre.entity.engine.TranslationProvider;

@Repository
public interface TranslationProviderDao extends JpaRepository<TranslationProvider, Integer> {
	
	
}
