package fr.cedricsevestre.dao.engine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.translation.TranslationProvider;

@Repository
public interface TranslationProviderDao extends JpaRepository<TranslationProvider, Integer> {
	
	
}
