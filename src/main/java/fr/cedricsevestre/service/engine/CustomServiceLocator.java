package fr.cedricsevestre.service.engine;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.CustomService;


@Component
public class CustomServiceLocator{

    @Autowired
    private ApplicationContext context;
    private Map<String, Object> services;

    public Object getService(String entityName) {
        checkServices();
        return services.get(entityName.toUpperCase() + "SERVICE");
    }

    private void checkServices() {
        if (services == null) {
            services = new HashMap<String, Object>();
            Map<String, Object> beans = context.getBeansWithAnnotation(CustomService.class);
            for (Map.Entry<String, Object> bean : beans.entrySet()) {
            	services.put(bean.getKey().toUpperCase(),  bean.getValue());
            	
            }
            
//            
//            for (Object bean : beans.values()) {
//            	
//            	System.out.println(bean.getClass().getName());
//            	System.out.println(bean.getClass().getCanonicalName());
//            	System.out.println(bean.getClass().getSimpleName());
//            	
//
//            	
//            	
//            	CustomService ann = bean.getClass().getAnnotation(CustomService.class);
//				if (ann == null){
//					
//	            	
//					Class<?> superClass = bean.getClass().getSuperclass();
//					if (superClass != null){
//						ann = bean.getClass().getAnnotation(CustomService.class);
//					}
//					
//					
//				}
//				            	
//
//            	
//            	
//            	
//            	System.out.println(ann.getClass().getName());
////            	System.out.println(ann.value());
//            	
//            	
//            	
////    			Annotation[] annotations = bean.getClass().getAnnotations();
////    			for (Annotation annotation : annotations) {
////    				Class<? extends Annotation> annotationType = annotation.annotationType();
////    				System.out.println("--- --- annotation = " + annotationType.getName());
////    				if(annotation instanceof CustomService){
////    					CustomService nType = (CustomService) annotation;
////    			        services.put(nType.value(),  bean);
////    				}
////    			}
//            	
//            	
//            	
//            	
//            	
//            	
//                //services.put("Project",  bean);
//            }
        }
    }   
}

