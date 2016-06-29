package fr.cedricsevestre.service.engine;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.exception.ServiceException;

public interface IBaseService<T> {

	T save(T base) throws ServiceException;

	void remove(T base) throws ServiceException;

	void removeById(Integer id) throws ServiceException;

	T findById(Integer id) throws ServiceException;

	List<T> findAll() throws ServiceException;

}
