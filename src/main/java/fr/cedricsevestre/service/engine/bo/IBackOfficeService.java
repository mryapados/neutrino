package fr.cedricsevestre.service.engine.bo;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;

import fr.cedricsevestre.bean.NData;
import fr.cedricsevestre.exception.ServiceException;

public interface IBackOfficeService {
	NData findAll(Class<?> entity) throws ServiceException;
	NData findAll(Class<?> entity, Pageable pageRequest) throws ServiceException;
}
