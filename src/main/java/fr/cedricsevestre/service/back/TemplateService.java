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
import fr.cedricsevestre.dao.back.TemplateDao;
import fr.cedricsevestre.entity.back.Base;
import fr.cedricsevestre.entity.back.Lang;
import fr.cedricsevestre.entity.back.MapTemplate;
import fr.cedricsevestre.entity.back.Template;
import fr.cedricsevestre.entity.back.Translation;
import fr.cedricsevestre.entity.back.Template.TemplateType;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class TemplateService{

	private Logger logger = Logger.getLogger(TemplateService.class);

	@Autowired
	private TemplateDao templateDao;

	@Autowired
	TranslationService translationService;
	
	@Transactional
	public Template save(Template template) throws ServiceException {
		logger.debug("appel de la methode save Template " + template.getName());
		try {
			return templateDao.save(template);
		} catch (PersistenceException e) {
			logger.error("erreur save Template " + e.getMessage());
			throw new ServiceException("erreur save Template", e);
		}
	}

	@Transactional
	public void remove(Template template) throws ServiceException {
		logger.debug("appel de la methode remove Template " + template.getName());
		try {
			templateDao.delete(template);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Template " + e.getMessage());
			throw new ServiceException("Template id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Template " + e.getMessage());
			throw new ServiceException("erreur remove Template", e);
		} catch (Exception e) {
			logger.error("erreur remove Template " + e.getMessage());
			throw new ServiceException("erreur remove Template", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Template id " + id);
		try {
			templateDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Template " + e.getMessage());
			throw new ServiceException("Template id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Template " + e.getMessage());
			throw new ServiceException("erreur removeById Template", e);
		} catch (Exception e) {
			logger.error("erreur remove Template " + e.getMessage());
			throw new ServiceException("erreur removeById Template", e);
		}
	}
	
	public Template findById(Integer id) throws ServiceException {
		try {
			return templateDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById Template", e);
		}
	}
	public Template findByName(String name) throws ServiceException {
		try {
			return templateDao.findByName(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findByName Template", e);
		}
	}
	public Template findByNameWithAllExceptData(String name) throws ServiceException {
		try {
			return templateDao.findByNameWithAllExceptData(name);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findByNameWithAllExceptData Template", e);
		}
	}	
	
	public List<Template> findAll() throws ServiceException {
		try {
			return templateDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Template", e);
		}
	}
	
	public List<Template> findAllBlockNotAffected() throws ServiceException {
		try {
			return templateDao.findAllBlockNotAffected();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllNotAffected Template", e);
		}
	}
	
	public List<Template> findAllBlockAndPageBlock() throws ServiceException {
		try {
			return templateDao.findAllBlockAndPageBlock();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllNotAffected Template", e);
		}
	}
	
	public List<Template> findAllWithModels() throws ServiceException {
		try {
			return templateDao.findAllWithModels();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllWithModels Template", e);
		}
	}
	
	public String pathType(Template template) throws ServiceException{
		if (template.getType() == TemplateType.BLOCK) return "blocks";
		else if (template.getType() == TemplateType.PAGE) return "pages";
		else if (template.getType() == TemplateType.PAGEBLOCK) return "pageblocks";
		else throw new ServiceException("erreur pathType Template");
	}
	
	public String pathJSP(String pathContext, Template template) throws ServiceException{	
		String pathBlock = pathType(template) + "/" + template.getPath();
		StringBuilder path = new StringBuilder();
		path.append(Common.BASEPAGESPATH);
		path.append(pathContext);
		path.append("/templates/");
		path.append(pathBlock);
		path.append(".jsp");
		return path.toString();
	}
	
	public Boolean checkJSPExist(String webInfFolder, String pathContext, Template template) throws ServiceException{
		File d = new File(webInfFolder);
		String path = d.getParent().replace("\\", "/") + pathJSP(pathContext, template);
		System.out.println("path = " + path);
		File f = new File(path);
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		return false;
	}
	
	@Transactional
	public Template translate(Template template, Lang lang) throws ServiceException {
		Template translated = new Template();
		
		Translation translation = template.getTranslation();
		if (translation == null){
			translation = translationService.save(new Translation());
		}
		translated.setLang(lang);
		translated.setTranslation(translation);
		translated.setName(template.getName());
		translated.setPath(template.getPath());
		translated.setType(template.getType());
		translated.setSchema(template.getSchema());
//		for (MapTemplate models : baseObject.getModels()) {
//			// TODO
//			System.out.println(models.getId());
//		}
//		
//		for (MapTemplate blocks : baseObject.getBlocks()) {
//			// TODO
//			System.out.println(blocks.getId());
//		}
		
		return translated;
	}


	
	
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public TemplateDao getTemplateDao() {
		return templateDao;
	}

	public void setTemplateDao(TemplateDao templateDao) {
		this.templateDao = templateDao;
	}








}
