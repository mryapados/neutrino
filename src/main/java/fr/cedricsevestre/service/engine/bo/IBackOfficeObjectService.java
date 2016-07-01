package fr.cedricsevestre.service.engine.bo;

import java.util.List;
import java.util.Map;

import fr.cedricsevestre.exception.ServiceException;

public interface IBackOfficeObjectService {
	List<Map<String, Object>> findAllForType(String type) throws ServiceException;
}
