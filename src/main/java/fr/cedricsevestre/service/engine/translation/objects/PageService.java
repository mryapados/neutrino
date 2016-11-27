package fr.cedricsevestre.service.engine.translation.objects;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.annotation.BOService;
import fr.cedricsevestre.dao.engine.PageDao;
import fr.cedricsevestre.dao.engine.TemplateDao;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.TranslationProvider;
import fr.cedricsevestre.entity.engine.translation.objects.Page;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@BOService
@Service
@Scope(value = "singleton")
public class PageService extends TranslationService<Page>{

	private Logger logger = Logger.getLogger(PageService.class);

	@Autowired
	private PageDao pageDao;
	
	@Override
	public List<IdProvider> findAllFetched() throws ServiceException {
		try {
			return pageDao.findAllFetched();
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}

	@Override
	public IdProvider findByIdFetched(Integer id) throws ServiceException {
		try {
			return pageDao.findByIdFetched(id);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findByIdFetched", e);
		}
	}
	
	@Override
	public org.springframework.data.domain.Page<IdProvider> findAllFetched(Pageable pageable) throws ServiceException {
		try {
			return pageDao.findAllFetched(pageable);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}
	
	@Transactional
	public Page translate(Page page, Lang lang) throws ServiceException {
		if (page.getId() != null) page = pageDao.findOne(page.getId()); //Refresh object
		
		Page translated = new Page();
		TranslationProvider translation = page.getTranslation();
		if (translation == null){
			translation = translationProviderDao.save(new TranslationProvider());
		}
		translated.setLang(lang);
		translated.setTranslation(translation);
		translated.setName(page.getName());
		translated.setContext(page.getContext());
		
		Template pageModel = page.getModel();
		if (pageModel != null){
			TranslationProvider pageModelTranslation = pageModel.getTranslation();
			if (pageModelTranslation != null){
				Map<Lang, Translation> translations = pageModelTranslation.getTranslations();
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




}
