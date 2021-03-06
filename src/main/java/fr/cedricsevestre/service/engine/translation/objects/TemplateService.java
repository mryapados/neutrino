package fr.cedricsevestre.service.engine.translation.objects;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.annotation.BOService;
import fr.cedricsevestre.com.utils.CommonUtil;
import fr.cedricsevestre.com.utils.CommonUtil.TypeBase;
import fr.cedricsevestre.dao.engine.TemplateDao;
import fr.cedricsevestre.entity.custom.Album;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.independant.objects.NData;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema.ScopeType;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.TranslationProvider;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.entity.engine.translation.objects.Template.TemplateKind;
import fr.cedricsevestre.exception.JSPNotFoundException;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.CacheService;
import fr.cedricsevestre.service.engine.translation.TranslationService;

@BOService
@Service
@Scope(value = "singleton")
public class TemplateService extends TranslationService<Template>{

	private Logger logger = Logger.getLogger(TemplateService.class);

	@Autowired
	private TemplateDao templateDao;
	
	@Autowired
	private CommonUtil common;
	
	@Autowired
	private CacheService cacheService;
	

	public Template identifyWithAllExceptData(Folder folder, String name, Lang lang) throws ServiceException {
		try {
			if (folder == null) {
				return templateDao.identifyWithAllExceptData(name, lang);
			}
			else {
				return templateDao.identifyWithAllExceptData(folder, name, lang);
			}
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findByNameWithAllExceptData Template", e);
		}
	}	
	
//	public List<Template> findAll() throws ServiceException {
//		try {
//			return (List<Template>) templateDao.findAll();
//		} catch (PersistenceException e) {
//			throw new ServiceException("erreur findAll Template", e);
//		}
//	}
	
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
		if (template.getKind() == TemplateKind.BLOCK) return "blocks";
		else if (template.getKind() == TemplateKind.PAGE) return "pages";
		else if (template.getKind() == TemplateKind.PAGEBLOCK) return "pageblocks";
		else if (template.getKind() == TemplateKind.ELEMENT) return "elements";
		else throw new ServiceException("erreur pathType Template");
	}
	
	public String pathJSP(boolean webInf, String pathContext, Template template, boolean jsp) throws ServiceException{	
		String pathType = pathType(template) + "/" + template.getPath();
		StringBuilder path = new StringBuilder();
		if (webInf) path.append(CommonUtil.BASE_WEBINF);
		path.append(pathContext);
		path.append("templates/");
		path.append(pathType);
		if (jsp) path.append(".jsp");
		return path.toString();
	}
	
	public Boolean checkJSPExist(String webInfFolder, String pathContext, Template template) throws ServiceException{
		logger.debug("Enter in checkJSPExist : pathContext = " + pathContext + "; template = " + template);
		String path = pathJSP(true, pathContext, template, true);
		boolean jspExist;
		File d = new File(webInfFolder);
		String fullPath = d.getParent().replace("\\", "/") + path;
		File f = new File(fullPath);
		if(f.exists() && !f.isDirectory()) { 
			jspExist = true;
		} else {
			jspExist = false;
		}
		logger.debug("EXIST [" + path + "] = " + jspExist);
		return jspExist;
	}
	
	@Cacheable(value="templateService")
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
					throw new JSPNotFoundException("JSP not found for template : " + template.getName() + "(" + template.getPath() + "), context : " + context);
				}
			}
		}
	}
	
	@Override
	public Template translate(Template base, Lang lang) throws ServiceException {
		
		boolean fromSomething = base.getId() != null;
		if (fromSomething) base = templateDao.findOne(base.getId()); //Refresh object
		
		base.setId(null);

		TranslationProvider translation = base.getTranslation();
		if (translation == null){
			translation = new TranslationProvider();
		}
		base.setLang(lang);
		base.setTranslation(translation);
		base.setName(base.getName());
		base.setPath(base.getPath());
		base.setKind(base.getKind());
		base.setSchema(base.getSchema());
		
		
//		for (MapTemplate models : baseObject.getModels()) {
//			// TODO
//			System.out.println(models.getId());
//		}
//		
//		for (MapTemplate blocks : baseObject.getBlocks()) {
//			// TODO
//			System.out.println(blocks.getId());
//		}
		return base;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}


}
