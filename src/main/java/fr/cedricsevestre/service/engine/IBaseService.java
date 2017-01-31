package fr.cedricsevestre.service.engine;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.exception.ServiceException;

public interface IBaseService<T> {

	T save(T base) throws ServiceException;

	void remove(T base) throws ServiceException;

	void remove(Iterable<T> base) throws ServiceException;
	
	void removeById(Integer id) throws ServiceException;

	T findById(Integer id) throws ServiceException;

	Iterable<T>findAll() throws ServiceException;

	Page<T>findAll(Pageable pageable) throws ServiceException;
	
	List<T> findAll(Specification<T> spec) throws ServiceException;
	
	Page<T> findAll(Specification<T> spec, Pageable pageable) throws ServiceException;
}
