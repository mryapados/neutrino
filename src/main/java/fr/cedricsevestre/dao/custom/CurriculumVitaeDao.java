package fr.cedricsevestre.dao.custom;

import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.CurriculumVitae;

@Repository
public interface CurriculumVitaeDao extends TranslationDao<CurriculumVitae> {

}
