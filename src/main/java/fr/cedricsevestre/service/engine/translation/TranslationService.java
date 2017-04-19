package fr.cedricsevestre.service.engine.translation;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.com.utils.IdProviderUtil;
import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.dao.engine.TranslationProviderDao;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.TranslationProvider;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;
import fr.cedricsevestre.service.engine.IBOService;

@Service
@Scope(value = "singleton")
public abstract class TranslationService<T extends Translation> extends BaseService<T>{

	private Logger logger = Logger.getLogger(TranslationService.class);
	
	@Autowired
	private TranslationDao<T> translationDao;

	@Autowired
	protected TranslationProviderDao translationProviderDao;


	@Autowired
	protected IdProviderUtil idProviderUtil;
	
	@Deprecated
	public T findByName(String name) throws ServiceException {
		try {
			System.out.println("findbyname " + name + " " + translationDao);
			
			return translationDao.findByName(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findByName Base", e);
		}
	}

	@Cacheable(value="identify")
	public T identify(Folder folder, String name, Lang lang) throws ServiceException {
		try {
			System.out.println("ZZZZ identify " + name + " " + lang.getCode() + " " + translationDao);
			if (folder == null) return translationDao.identify(name, lang);
			else return translationDao.identify(folder, name, lang);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur identify Base", e);
		}
	}
	
	@Override
	@Transactional
	public T save(T base) throws ServiceException {
		logger.debug("appel de la methode save Base " + base.getClass());
		try {
			TranslationProvider translationProvider = base.getTranslation();
			if (translationProvider != null){
				if (translationProvider.getId() == null) {
					translationProviderDao.save(translationProvider);
				}
			}
			return translationDao.save(base);
		} catch (PersistenceException e) {
			logger.error("erreur save Base " + e.getMessage());
			throw new ServiceException("erreur save Base", e);
		}
	}

	public T translate(T base, Lang lang) throws ServiceException {
		boolean fromSomething = base.getId() != null;
		if (fromSomething) base = translationDao.findOne(base.getId()); //Refresh object
		
		base.setId(null);

		TranslationProvider translation = base.getTranslation();
		if (translation == null){
			translation = new TranslationProvider();
		}
		base.setLang(lang);
		base.setTranslation(translation);
		base.setName(base.getName());
		
		return base;
	}
	


}
