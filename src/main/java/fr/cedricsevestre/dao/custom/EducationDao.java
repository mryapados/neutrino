package fr.cedricsevestre.dao.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Education;
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
public interface EducationDao extends TranslationDao<Education> {

	@Query("SELECT e FROM Education e WHERE (e.folders IS EMPTY OR :folder IN elements(e.folders)) AND e.lang =:lang AND e.resume =:resume ORDER BY e.year DESC")
	List<Education> findAllForResumeAndFolderAndLang(@Param("resume") Resume resume, @Param("folder") Folder folder, @Param("lang") Lang lang);

}
