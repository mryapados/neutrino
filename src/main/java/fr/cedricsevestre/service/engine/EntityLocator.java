package fr.cedricsevestre.service.engine;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;


@Component
public class EntityLocator{

    @Autowired
    private ApplicationContext context;
    private Map<String, Object> entities;

    public Object getEntity(String entityName) {
    	checkEntities();
        return entities.get(entityName.toUpperCase());
    }

    private void checkEntities() {
        if (entities == null) {
        	entities = new HashMap<String, Object>();
            Map<String, Object> beans = context.getBeansWithAnnotation(Entity.class);
            for (Map.Entry<String, Object> bean : beans.entrySet()) {
            	entities.put(bean.getKey().toUpperCase(),  bean.getValue());
            }
        }
    }   
}

