package fr.cedricsevestre.service.engine.translation.objects;

import java.io.File;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.common.Common.TypeBase;
import fr.cedricsevestre.dao.engine.TemplateDao;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.TranslationProvider;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.entity.engine.translation.objects.Template.TemplateType;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@Service
@Scope(value = "singleton")
public class TemplateService extends TranslationService<Template>{

	private Logger logger = Logger.getLogger(TemplateService.class);

	@Autowired
	private TemplateDao templateDao;
	
	@Autowired
	private Common common;
	
	@Override
	public List<Translation> findAllFetched() throws ServiceException {
		try {
			return templateDao.findAllFetched();
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
		}
	}
	@Override
	public Page<Translation> findAllFetched(Pageable pageable) throws ServiceException {
		try {
			return templateDao.findAllFetched(pageable);
		} catch (PersistenceException e) {
			throw new ServiceException("Error findAllFetched", e);
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
			return (List<Template>) templateDao.findAll();
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
	
	public String pathJSP(boolean webInf, String pathContext, Template template, boolean jsp) throws ServiceException{	
		String pathBlock = pathType(template) + "/" + template.getPath();
		StringBuilder path = new StringBuilder();
		if (webInf) path.append(Common.BASE_WEBINF);
		path.append(pathContext);
		path.append("templates/");
		path.append(pathBlock);
		if (jsp) path.append(".jsp");
		return path.toString();
	}
	
	public Boolean checkJSPExist(String webInfFolder, String pathContext, Template template) throws ServiceException{
		File d = new File(webInfFolder);
		System.out.println("webInfFolder = " + webInfFolder);
		String path = d.getParent().replace("\\", "/") + pathJSP(true, pathContext, template, true);
		//String path = webInfFolder.replace("\\", "/") + pathJSP(pathContext, template);
		System.out.println("path = " + path);
		File f = new File(path);
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		return false;
	}
	
	public String getPathJSP(Boolean webInf, Folder folder, String context, Template template, boolean jsp) throws ServiceException{
		String pathContext = common.getBasePath(false, folder, TypeBase.VIEWS) + context + "/";
		if (checkJSPExist(common.getWebInfFolder(), pathContext, template)){
			return pathJSP(webInf, pathContext, template, jsp);
		} else {
			pathContext = common.getBasePath(false, folder, TypeBase.COMMON);
			if (checkJSPExist(common.getWebInfFolder(), pathContext, template)){
				return pathJSP(webInf,pathContext, template, jsp);
			} else {
				pathContext = common.getBasePath(false, null, TypeBase.COMMON);
				if (checkJSPExist(common.getWebInfFolder(), pathContext, template)){
					return pathJSP(webInf,pathContext, template, jsp);
				} else {
					throw new ServiceException("JSP not found");
				}
			}
		}
	}
	
	//@Override
	@Transactional
	public Template translate(Template template, Lang lang) throws ServiceException {
		Template translated = new Template();
		
		TranslationProvider translation = template.getTranslation();
		if (translation == null){
			translation = translationProviderDao.save(new TranslationProvider());
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


}
