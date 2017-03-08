package fr.cedricsevestre.service.engine;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import fr.cedricsevestre.annotation.BlockController;
import fr.cedricsevestre.annotation.BlockMapping;
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
		public BlockControllerBean(Object object, Method method) {
			super();
			this.object = object;
			this.method = method;
		}
		public Object getObject() {
			return object;
		}
		public Method getMethod() {
			return method;
		}	
	}
	
    @Autowired
    private ApplicationContext context;
    private Map<String, BlockControllerBean> blockControllers;
    
    public ModelMap execute(String controllerName, Translation model, Translation activeObject, Template block, PageContext pageContext) throws ServiceException{
    	if (controllerName == null) return null;
    	
        checkTemplateControllers();
        String lookingFor = controllerName.toUpperCase();
        BlockControllerBean blockControllerBean = blockControllers.get(lookingFor);
        if (blockControllerBean == null) {
        	return null;
        }
        
        Object object = blockControllerBean.getObject();
        Method method = blockControllerBean.getMethod();

		try {
			Object paramsObj[] = {model, activeObject, block, pageContext};
			return (ModelMap) method.invoke(object, paramsObj);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new ServiceException("Error execute", e);//TODO TemplateControllerException
		}
        
    }

    private void checkTemplateControllers() {
        if (blockControllers == null) {
            blockControllers = new HashMap<String, BlockControllerBean>();
            Map<String, Object> beans = context.getBeansWithAnnotation(BlockController.class);
            for (Map.Entry<String, Object> bean : beans.entrySet()) {

            	System.out.println("   bean name = " + bean.getKey() + " - " + bean.getValue().getClass().getName());
            	for (Method method : bean.getValue().getClass().getDeclaredMethods())
            	{
            		System.out.println("   methode = " + method.getName());
            		BlockMapping blockMapping = method.getAnnotation(BlockMapping.class);
        	    	if (blockMapping != null){
        	    		String blockControllerName = blockMapping.blockControllerName().toUpperCase();
        	    		if (blockControllers.containsKey(blockControllerName)){
        	    			logger.warn("Ambiguous block controller name '" + blockControllerName + "' found !");
        	    		}
        	    		blockControllers.put(blockControllerName, new BlockControllerBean(bean.getValue(), method));
        	    	}
        	    	
            	}
            }
        }
    }   
}

