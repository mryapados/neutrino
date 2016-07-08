package fr.cedricsevestre.service.engine.bo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.bean.NData;
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

	public NData findAllForType(String type, List<Field> fields) throws ServiceException{
		List<Translation> translations = tObjectService.findAllForType(type);
		List<Map<String, Object>> datas = new ArrayList<>();
		
		List<NField> nfFields = new ArrayList<>();
		for (Field field : fields) {
			
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				Class<? extends Annotation> annotationType = annotation.annotationType();
				System.out.println("--- --- annotation = " + annotationType.getName());
				if(annotation instanceof BOField){
					BOField nType = (BOField) annotation;
			        System.out.println("--- --- --- type : " + nType.type());
			        System.out.println("--- --- --- ofType: " + nType.ofType());
			        
			        nfFields.add(new NField(nType.type(), nType.ofType(), field.getName(), field.getType().getName(), nType.inList()));
					
			        
				}
			}
			
			
			
		}
		
		for (Translation translation : translations) {
			System.out.println(translation.getName());
			Map<String, Object> record = new HashMap<>();
			for (Field field : fields) {
				System.out.println(translation.getName() + " field = " + field.getName() + " - " + "value = " + getFieldValue(translation, field));
				record.put(field.getName(), getFieldValue(translation, field));
			}
			datas.add(record);
		}
		return new NData(nfFields, datas);
	}

	

}
