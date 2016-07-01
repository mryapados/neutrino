package fr.cedricsevestre.service.engine.bo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.bean.NField;
import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TObjectService;

@Service
@Scope(value = "singleton")
public class BackOfficeTranslationService implements IBackOfficeObjectService{

	private Logger logger = Logger.getLogger(BackOfficeTranslationService.class);

	@Autowired
	private TObjectService tObjectService;

	public List<Map<String, Object>> findAllForType(String type) throws ServiceException{
		List<Translation> translations = tObjectService.findAllForType(type);
		
		List<Map<String, Object>> result = new ArrayList<>();
		for (Translation translation : translations) {
			
			
			
			
		}
		
		
		
		
		
		
		
		return result;
	}

	
	
//	public static Object runGetter(Field field, Class<?> o)
//	{
//	    // MZ: Find the correct method
//	    for (Method method : o.getMethods())
//	    {
//	        if ((method.getName().startsWith("get")) && (method.getName().length() == (field.getName().length() + 3)))
//	        {
//	            if (method.getName().toLowerCase().endsWith(field.getName().toLowerCase()))
//	            {
//	                // MZ: Method found, run it
//	                try
//	                {
//	                    return method.invoke(o);
//	                }
//	                catch (IllegalAccessException e)
//	                {
//	                	e.printStackTrace();
//	                    //Logger.fatal("Could not determine method: " + method.getName());
//	                }
//	                catch (InvocationTargetException e)
//	                {
//	                	e.printStackTrace();
//	                    //Logger.fatal("Could not determine method: " + method.getName());
//	                }
//
//	            }
//	        }
//	    }
//
//
//	    return null;
//	}

}
