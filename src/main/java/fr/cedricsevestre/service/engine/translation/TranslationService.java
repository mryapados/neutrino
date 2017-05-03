package fr.cedricsevestre.service.engine.translation;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.com.utils.IdProviderUtil;
import fr.cedricsevestre.constants.CacheConst;
import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.dao.engine.TranslationProviderDao;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.TranslationProvider;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;

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

	@Cacheable(CacheConst.TRANSLATION_IDENTIFY)
	public T identify(Folder folder, String name, Lang lang) throws ServiceException {
		logger.debug("Enter in identify : folder = " + folder +  "; name = " + name + "; lang = " + lang);
		try {
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
		if (fromSomething) base = (T) idProviderUtil.getFullObject(base.getClass(), base.getId());
		
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
