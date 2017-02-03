package fr.cedricsevestre.service.engine;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;

public interface IBOService<T>{

	public T findByIdFetched(Integer id) throws ServiceException;
	public List<T> findAllFetched() throws ServiceException;
	public Page<T> findAllFetched(Pageable pageable) throws ServiceException;
//	public List<T> findAllFetched(Specification<T> spec) throws ServiceException;
//	public Page<T> findAllFetched(Specification<T> spec, Pageable pageable) throws ServiceException;
	
}
