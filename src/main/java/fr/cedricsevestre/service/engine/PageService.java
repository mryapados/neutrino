package fr.cedricsevestre.service.engine;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.Page;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.entity.engine.TranslationProvider;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class PageService extends TranslationService<Page>{

	private Logger logger = Logger.getLogger(PageService.class);

	@Transactional
	public Page translate(Page page, Lang lang) throws ServiceException {
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