package fr.cedricsevestre.service.engine;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOService;
import fr.cedricsevestre.annotation.CustomService;


@Component
public class ServiceLocator{

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
            beans.putAll(context.getBeansWithAnnotation(BOService.class));
            for (Map.Entry<String, Object> bean : beans.entrySet()) {
            	services.put(bean.getKey().toUpperCase(),  bean.getValue());
            	
            }
        }
    }   
}

