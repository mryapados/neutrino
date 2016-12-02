package fr.cedricsevestre.service.engine.bo;

import org.springframework.data.domain.Pageable;

import fr.cedricsevestre.bean.NData;
import fr.cedricsevestre.bean.NDatas;
import fr.cedricsevestre.exception.ServiceException;

public interface IBackOfficeService {
	NDatas<?> findAll(Class<?> entity) throws ServiceException;
	NDatas<?> findAll(Class<?> entity, Pageable pageRequest) throws ServiceException;
	
	NData<?> findOne(Class<?> entity, Integer id) throws ServiceException;
	NData<?> copy(Class<?> entity, Integer id) throws ServiceException;
}
