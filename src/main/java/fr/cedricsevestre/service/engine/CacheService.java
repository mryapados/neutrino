package fr.cedricsevestre.service.engine;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "singleton")
public class CacheService{

	private Logger logger = Logger.getLogger(CacheService.class);

	
	private Map<String, Boolean> jsps;
	
	public Boolean jspPathExist(String path){
		if (jsps == null){
			jsps = new HashMap<>();
		}
		return jsps.get(path);
	}

	public void addJspPath(String path, Boolean exist){
		jsps.put(path, exist);
		
		System.out.println(path);
	}
	


}
