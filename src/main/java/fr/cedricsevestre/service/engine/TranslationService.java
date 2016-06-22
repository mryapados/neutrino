package fr.cedricsevestre.service.engine;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.engine.TranslationDao;
import fr.cedricsevestre.dao.engine.TranslationProviderDao;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.entity.engine.TranslationProvider;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public abstract class TranslationService<T extends Translation> extends BaseService<T>{

	private Logger logger = Logger.getLogger(TranslationService.class);

	@Autowired
	private TranslationDao<T> translationDao;

	@Autowired
	protected TranslationProviderDao translationProviderDao;
	
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

	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}









}