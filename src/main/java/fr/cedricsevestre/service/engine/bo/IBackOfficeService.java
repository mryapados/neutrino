package fr.cedricsevestre.service.engine.bo;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;

import fr.cedricsevestre.bean.NData;
import fr.cedricsevestre.bean.NDatas;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.exception.ServiceException;

public interface IBackOfficeService {
	NDatas<?> findAll(Class<?> entity, Pageable pageRequest) throws ServiceException;
	NDatas<?> findAll(Class<?> entity, Pageable pageable, Specification<IdProvider> spec, EntityGraphType entityGraphType, String entityGraphName) throws ServiceException;
	NDatas<?> findAll(Class<?> entity, Pageable pageable, EntityGraphType entityGraphType, String entityGraphName) throws ServiceException;
	
	NData<?> findOne(Class<?> entity, Integer id) throws ServiceException;
	NData<?> findOne(Class<?> entity, Integer id, EntityGraphType entityGraphType, String entityGraphName) throws ServiceException;
	NData<?> findOne(Class<?> entity, Integer id, Specification<IdProvider> spec, EntityGraphType entityGraphType, String entityGraphName) throws ServiceException;
	
	NData<?> copy(Class<?> entity, Integer id) throws ServiceException;
}
