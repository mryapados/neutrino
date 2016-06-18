package fr.cedricsevestre.service.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.back.PageDao;
import fr.cedricsevestre.entity.engine.Base;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.Page;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class PageService extends TranslationService<Page>{

	private Logger logger = Logger.getLogger(PageService.class);

	@Autowired
	private PageDao pageDao;

	@Override
	@Transactional
	public Page translate(Page page, Lang lang) throws ServiceException {
		Page translated = new Page();
		
		Translation translation = page.getTranslation();
		if (translation == null){
			translation = translationDao.save(new Translation());
		}
		translated.setLang(lang);
		translated.setTranslation(translation);
		translated.setName(page.getName());
		translated.setContext(page.getContext());

		Template pageModel = page.getModel();
		if (pageModel != null){
			Translation pageModelTranslation = pageModel.getTranslation();
			if (pageModelTranslation != null){
				Map<Lang, Base> translations = pageModelTranslation.getTranslations();
				if (translations != null){
					translated.setModel((Template) translations.get(lang));
				}
			}
		}

		return translated;
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}






}
