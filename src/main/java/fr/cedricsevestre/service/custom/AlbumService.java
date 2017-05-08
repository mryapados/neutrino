package fr.cedricsevestre.service.custom;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.dao.custom.AlbumDao;
import fr.cedricsevestre.dao.custom.ProjectDao;
import fr.cedricsevestre.dao.engine.BaseDao;
import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@Service
@CustomService
public class AlbumService extends TranslationService<Album>{

	
	
	
}
