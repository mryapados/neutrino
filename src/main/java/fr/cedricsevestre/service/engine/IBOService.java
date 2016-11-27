package fr.cedricsevestre.service.engine;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;

public interface IBOService{

	public IdProvider findByIdFetched(Integer id) throws ServiceException;
	public List<IdProvider> findAllFetched() throws ServiceException;
	public Page<IdProvider> findAllFetched(Pageable pageable) throws ServiceException;
	
}
