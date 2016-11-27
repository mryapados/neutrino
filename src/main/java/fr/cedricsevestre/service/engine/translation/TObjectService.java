package fr.cedricsevestre.service.engine.translation;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class TObjectService extends TranslationService<Translation>{
	
	@Override
	public IdProvider findByIdFetched(Integer id) throws ServiceException {
		throw new ServiceException("Method unimplemented for TObjectService");
	}
	@Override
	public List<IdProvider> findAllFetched() throws ServiceException {
		throw new ServiceException("Method unimplemented for TObjectService");
	}
	@Override
	public Page<IdProvider> findAllFetched(Pageable pageable) throws ServiceException {
		throw new ServiceException("Method unimplemented for TObjectService");
	}
	
}
