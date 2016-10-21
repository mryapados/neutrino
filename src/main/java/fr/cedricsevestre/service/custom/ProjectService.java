package fr.cedricsevestre.service.custom;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.dao.custom.ProjectDao;
import fr.cedricsevestre.entity.custom.Project;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TranslationService;


@Service
@Scope(value = "singleton")
@CustomService
public class ProjectService extends TranslationService<Project>{

	@Autowired
	ProjectDao projectDao;
	
	@Override
	public Translation findByIdFetched(Integer id) throws ServiceException {
		try {
			return projectDao.findByIdFetched(id);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findByIdFetched", e);
		}
	}
	
	@Override
	public List<Translation> findAllFetched() throws ServiceException {
		try {
			return projectDao.findAllFetched();
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}
	@Override
	public Page<Translation> findAllFetched(Pageable pageable) throws ServiceException {
		try {
			return projectDao.findAllFetched(pageable);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}
}
