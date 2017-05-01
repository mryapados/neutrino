package fr.cedricsevestre.service.custom;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOService;
import fr.cedricsevestre.dao.custom.PortfolioDao;
import fr.cedricsevestre.entity.custom.Portfolio;
import fr.cedricsevestre.entity.custom.Resume;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@BOService
@Service
@Scope(value = "singleton")
public class PortfolioService extends TranslationService<Portfolio>{

	private Logger logger = Logger.getLogger(PortfolioService.class);

	@Autowired
	private PortfolioDao dao;

	public Page<Portfolio> findAllForFolderAndLang(Folder folder, Lang lang, Pageable pageable) throws ServiceException {
		try {
			return dao.findAllForFolderAndLang(folder, lang, pageable);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllForResumeAndFolderAndLang Portfolio", e);
		}
	}	
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}




}
