package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Category;
import fr.cedricsevestre.entity.custom.Experience;
import fr.cedricsevestre.entity.custom.Job;
import fr.cedricsevestre.entity.custom.Media;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.custom.Resume;
import fr.cedricsevestre.entity.custom.Skill;
import fr.cedricsevestre.entity.custom.Skill.SkillKind;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Repository
public interface SkillDao extends TranslationDao<Skill> {

	@Query("SELECT s FROM Skill s WHERE (s.folders IS EMPTY OR :folder IN elements(s.folders)) AND s.lang =:lang AND s.kind =:kind ORDER BY s.ordered")
	List<Skill> findAllKindForFolderAndLang(@Param("kind") SkillKind kind, @Param("folder") Folder folder, @Param("lang") Lang lang);
	
	
}
