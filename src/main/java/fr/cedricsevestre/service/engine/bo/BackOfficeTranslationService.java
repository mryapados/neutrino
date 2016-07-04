package fr.cedricsevestre.service.engine.bo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.bean.NField;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.translation.TObjectService;

@Service
@Scope(value = "singleton")
public class BackOfficeTranslationService extends BackOfficeService{

	private Logger logger = Logger.getLogger(BackOfficeTranslationService.class);

	@Autowired
	private TObjectService tObjectService;

	public List<Map<String, Object>> findAllForType(String type, List<Field> fields) throws ServiceException{
		List<Translation> translations = tObjectService.findAllForType(type);
		List<Map<String, Object>> result = new ArrayList<>();
		
//		List<NField> nfFields =  
		for (Translation translation : translations) {
			System.out.println(translation.getName());
			Map<String, Object> record = new HashMap<>();
			for (Field field : fields) {
				System.out.println(translation.getName() + " field = " + field.getName() + " - " + "value = " + getFieldValue(translation, field));
				record.put(field.getName(), getFieldValue(translation, field));
			}
			result.add(record);
		}
		return result;
	}

	

}
