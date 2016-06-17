package fr.cedricsevestre.dao.back;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.Lang;

@Repository
@Qualifier(value="backEntityManagerFactory")
public interface LangDao extends JpaRepository<Lang, Integer> {
	
	
}
