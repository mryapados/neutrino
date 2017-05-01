package fr.cedricsevestre.service.custom;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOService;
import fr.cedricsevestre.dao.custom.ArticleDao;
import fr.cedricsevestre.entity.custom.Article;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@BOService
@Service
@Scope(value = "singleton")
public class ArticleService extends TranslationService<Article>{

	private Logger logger = Logger.getLogger(ArticleService.class);

	@Autowired
	private ArticleDao dao;

	public Page<Article> findAllForFolderAndLang(Folder folder, Lang lang, Pageable pageable) throws ServiceException {
		try {
			return dao.findAllForFolderAndLang(folder, lang, pageable);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllForFolderAndLang Article", e);
		}
	}	
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}




}
