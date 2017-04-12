package fr.cedricsevestre.dao.engine.bo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.engine.translation.Link;
import fr.cedricsevestre.entity.engine.translation.Translation;

@Repository
public interface LinkDao extends TranslationDao<Link> {

}
