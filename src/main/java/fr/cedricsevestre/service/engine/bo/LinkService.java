package fr.cedricsevestre.service.engine.bo;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.CustomService;
import fr.cedricsevestre.dao.custom.AlbumDao;
import fr.cedricsevestre.dao.custom.ProjectDao;
import fr.cedricsevestre.dao.engine.bo.LinkDao;
import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.custom.Marker;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.bo.Link;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@Service
@Scope(value = "singleton")
@CustomService
public class LinkService extends TranslationService<Link>{

	@Autowired
	LinkDao dao;

	@Override
	public Link findByIdFetched(Integer id) throws ServiceException {
		try {
			return dao.findByIdFetched(id);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findByIdFetched", e);
		}
	}
	
	@Override
	public List<Link> findAllFetched() throws ServiceException {
		try {
			return dao.findAllFetched();
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}
	
	@Override
	public Page<Link> findAllFetched(Pageable pageable) throws ServiceException {
		try {
			return dao.findAllFetched(pageable);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}
	
	@Override
	public List<Link> findAllFetched(Specification<Link> spec) throws ServiceException {
		try {
			return dao.findAllFetched(spec);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}
	
	@Override
	public Page<Link> findAllFetched(Specification<Link> spec, Pageable pageable) throws ServiceException {
		try {
			return dao.findAllFetched(spec, pageable);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}

}
