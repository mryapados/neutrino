package fr.cedricsevestre.service.engine.translation;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class TObjectService extends TranslationService<Translation>{
	
	@Override
	public Translation findByIdFetched(Integer id) throws ServiceException {
		throw new ServiceException("Method unimplemented for TObjectService");
	}
	@Override
	public List<Translation> findAllFetched() throws ServiceException {
		throw new ServiceException("Method unimplemented for TObjectService");
	}
	@Override
	public Page<Translation> findAllFetched(Pageable pageable) throws ServiceException {
		throw new ServiceException("Method unimplemented for TObjectService");
	}
	
}
