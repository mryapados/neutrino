package fr.cedricsevestre.com.utils;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.cedricsevestre.conf.ApplicationProperties;
import fr.cedricsevestre.constants.AttributeConst;
import fr.cedricsevestre.controller.engine.AbstractController;
import fr.cedricsevestre.service.engine.CacheService;
import fr.cedricsevestre.service.engine.independant.objects.PositionService;
import fr.cedricsevestre.service.engine.translation.objects.PageService;
import fr.cedricsevestre.service.engine.translation.objects.TemplateService;

@Component
public class BeanApplicationScope {	
	private Logger logger = Logger.getLogger(AbstractController.class);
	
	@Autowired
	ServletContext servletContext;
	@Autowired
	private ApplicationProperties applicationProperties;
	@Autowired
	private CommonUtil commonUtil;
	@Autowired
	private PageService pageService;
	@Autowired
	private TemplateService templateService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private JspTagUtil jspTagUtil;
	@Autowired
	private IdProviderUtil idProviderUtil;
	
	@PostConstruct
	public void addBeanToApplicationScope(){
		logger.debug("Enter in addBeanToApplicationScope");
		servletContext.setAttribute(AttributeConst.APPLICATION_PROPERTIES_BEAN, applicationProperties);
		servletContext.setAttribute(AttributeConst.COMMON_UTIL_BEAN, commonUtil);
		servletContext.setAttribute(AttributeConst.CACHE_SERVICE_BEAN, cacheService);
		servletContext.setAttribute(AttributeConst.PAGE_SERVICE_BEAN, pageService);
		servletContext.setAttribute(AttributeConst.TEMPLATE_SERVICE_BEAN, templateService);
		servletContext.setAttribute(AttributeConst.CACHE_SERVICE_BEAN, cacheService);
		servletContext.setAttribute(AttributeConst.POSITION_SERVICE_BEAN, positionService);
		servletContext.setAttribute(AttributeConst.JSP_TAG_UTIL_BEAN, jspTagUtil);
		servletContext.setAttribute(AttributeConst.ID_PROVIDER_UTIL_BEAN, idProviderUtil);
		
	
		
	}

}
