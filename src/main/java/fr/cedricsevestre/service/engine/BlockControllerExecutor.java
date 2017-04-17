package fr.cedricsevestre.service.engine;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import fr.cedricsevestre.annotation.BlockController;
import fr.cedricsevestre.annotation.BlockMapping;
import fr.cedricsevestre.entity.engine.independant.objects.Folder;
import fr.cedricsevestre.entity.engine.translation.Lang;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.bo.BackOfficeService;


@Component
public class BlockControllerExecutor {

	private Logger logger = Logger.getLogger(BlockControllerExecutor.class);
	
	private class BlockControllerBean implements Serializable{
		private static final long serialVersionUID = 1L;
		private Object object;
		private Method method;
		private List<Class<?>> parameters;
		public BlockControllerBean(Object object, Method method, List<Class<?>> parameters) {
			super();
			this.object = object;
			this.method = method;
			this.parameters = parameters;
		}
		public Object getObject() {
			return object;
		}
		public Method getMethod() {
			return method;
		}
		public List<Class<?>> getParameters() {
			return parameters;
		}
	}
	
    @Autowired
    private ApplicationContext context;
    private Map<String, BlockControllerBean> blockControllers;
    
    public ModelMap execute(String controllerName, Translation model, Translation activeObject, Template block, Folder folder, Lang lang, PageContext pageContext) throws ServiceException{
    	logger.debug("Enter in 'execute'");	
    	
    	if (controllerName == null) return null;
    	
        checkTemplateControllers();
        String lookingFor = controllerName.toUpperCase();
        BlockControllerBean blockControllerBean = blockControllers.get(lookingFor);
        if (blockControllerBean == null) {
        	return null;
        }

		try {
			Object paramsObj[] = mkParameters(blockControllerBean.getParameters(), model, activeObject, block, folder, lang, pageContext);
			return (ModelMap) blockControllerBean.getMethod().invoke(blockControllerBean.getObject(), paramsObj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ServiceException("Error execute", e);//TODO TemplateControllerException
		}
        
    }
    
    private Object[] mkParameters(List<Class<?>> parameters, Translation model, Translation activeObject, Template block, Folder folder, Lang lang, PageContext pageContext){
    	logger.debug("Enter in 'mkParameters'");	
    	Boolean firstTranslation = true;
		List<Object> objects = new ArrayList<>();
		for (Class<?> parameter : parameters) {
			if (firstTranslation && parameter == Translation.class){
				objects.add(model);
				firstTranslation = false;
			} else if (!firstTranslation && parameter == Translation.class){
				objects.add(activeObject);
			} else if (parameter == Template.class){
				objects.add(block);
			} else if (parameter == Folder.class){
				objects.add(folder);
			} else if (parameter == Lang.class){
				objects.add(lang);
			} else if (parameter == PageContext.class){
				objects.add(pageContext);
			}
		}
		Object paramsObj[] = new Object[objects.size()];
		objects.toArray(paramsObj);
		return paramsObj;
    }
    

    private void checkTemplateControllers() {
    	logger.debug("Enter in 'checkTemplateControllers'");	
        if (blockControllers == null) {
            blockControllers = new HashMap<String, BlockControllerBean>();
            Map<String, Object> beans = context.getBeansWithAnnotation(BlockController.class);
            for (Map.Entry<String, Object> bean : beans.entrySet()) {
            	
            	logger.debug("Bean found '" +  bean.getValue().getClass().getName() + "'");
            	for (Method method : bean.getValue().getClass().getDeclaredMethods())
            	{
            		BlockMapping blockMapping = method.getAnnotation(BlockMapping.class);
        	    	if (blockMapping != null){
        	    		logger.debug("blockMapping found on method '" +  method.getName() + "'");
        	    		String blockControllerName = blockMapping.blockControllerName().toUpperCase();
        	    		if (blockControllers.containsKey(blockControllerName)){
        	    			logger.warn("Ambiguous block controller name '" + blockControllerName + "' found !");
        	    		}
        	    		String key = blockControllerName.toUpperCase();
        				List<Class<?>> classes = new ArrayList<>();
        	    		Parameter[] parameters = method.getParameters();
        	    		for (Parameter parameter : parameters) {
        	    			classes.add(parameter.getType());
						}
        	    		blockControllers.put(key, new BlockControllerBean(bean.getValue(), method, classes));
        	    	}
        	    	
            	}
            }
        }
    }   
}

