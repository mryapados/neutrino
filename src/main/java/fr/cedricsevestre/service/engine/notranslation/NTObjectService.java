package fr.cedricsevestre.service.engine.notranslation;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class NTObjectService extends NoTranslationService<NoTranslation>{
	
	@Override
	public IdProvider findByIdFetched(Integer id) throws ServiceException {
		throw new ServiceException("Method unimplemented for TObjectService");
	}
	@Override
	public List<IdProvider> findAllFetched() throws ServiceException {
		throw new ServiceException("Method unimplemented for NTObjectService");
	}
	@Override
	public Page<IdProvider> findAllFetched(Pageable pageable) throws ServiceException {
		throw new ServiceException("Method unimplemented for NTObjectService");
	}
}
