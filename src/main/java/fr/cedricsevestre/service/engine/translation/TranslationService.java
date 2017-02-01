package fr.cedricsevestre.service.engine.translation;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.dao.engine.TranslationProviderDao;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.TranslationProvider;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;
import fr.cedricsevestre.service.engine.IBOService;

@Service
@Scope(value = "singleton")
public abstract class TranslationService<T extends Translation> extends BaseService<T> implements IBOService<T>{

	private Logger logger = Logger.getLogger(TranslationService.class);
	
	@Autowired
	private TranslationDao<T> translationDao;

	@Autowired
	protected TranslationProviderDao translationProviderDao;
	
	@Override
	public List<T> findAllFetched() throws ServiceException {
		try {
			return translationDao.findAllFetched();
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}

	@Override
	public Page<T> findAllFetched(Pageable pageable) throws ServiceException {
		try {
			return translationDao.findAllFetched(pageable);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}
	
	@Override
	public List<T> findAllFetched(Specification<T> spec) throws ServiceException {
		try {
			return translationDao.findAllFetched(spec);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}

	@Override
	public Page<T> findAllFetched(Specification<T> spec, Pageable pageable) throws ServiceException {
		try {
			return translationDao.findAllFetched(spec, pageable);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}
	
	@Override
	public T findByIdFetched(Integer id) throws ServiceException {
		try {
			return translationDao.findByIdFetched(id);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findByIdFetched", e);
		}
	}
	
	public T findByName(String name) throws ServiceException {
		try {
			System.out.println("findbyname " + name + " " + translationDao);
			
			return translationDao.findByName(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findByName Base", e);
		}
	}

	@Transactional
	public T translate(T base, Lang lang, Class<T> cls) throws ServiceException, InstantiationException, IllegalAccessException {
		if (base.getId() != null) base = translationDao.findOne(base.getId()); //Refresh object
		T translated = cls.newInstance(); 

		TranslationProvider translation = base.getTranslation();
		if (translation == null){
			translation = translationProviderDao.save(new TranslationProvider());
		}
		translated.setLang(lang);
		translated.setTranslation(translation);
		translated.setName(base.getName());

		return translated;
	}

}
