package fr.cedricsevestre.service.back;

import java.io.File;
import java.util.List;

import javax.persistence.PersistenceException;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.dao.back.TranslationDao;
import fr.cedricsevestre.dao.back.TranslationProviderDao;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.TranslationProvider;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public abstract class TranslationService<T extends Translation> extends BaseService<T>{

	private Logger logger = Logger.getLogger(TranslationService.class);

	@Autowired
	private TranslationDao<T> baseDao;

	@Autowired
	protected TranslationProviderDao translationDao;
	
	public T findByName(String name) throws ServiceException {
		try {
			System.out.println("findbyname " + name + " " + baseDao);
			
			return baseDao.findByName(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findByName Base", e);
		}
	}

	@Transactional
	public T translate(T base, Lang lang) throws ServiceException, InstantiationException, IllegalAccessException {
		T translated = (T) base.getClass().newInstance();

		TranslationProvider translation = base.getTranslation();
		if (translation == null){
			translation = translationDao.save(new TranslationProvider());
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
