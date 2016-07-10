package fr.cedricsevestre.service.engine.bo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.CustomServiceLocator;

@Service
@Scope(value = "singleton")
public class BackOfficeNoTranslationService implements IBackOfficeObjectService{

	private Logger logger = Logger.getLogger(BackOfficeNoTranslationService.class);

	@Autowired
	BackOfficeService backOfficeService;
	
	@Autowired
	CustomServiceLocator customServiceLocator;
	
	@SuppressWarnings("unchecked")
	private List<NoTranslation> getDatas(Class<?> entity) throws ServiceException{
		try {
			Class<?> params[] = {};
			Object paramsObj[] = {};
			Object service = customServiceLocator.getService(entity.getSimpleName());
			Class<?> clazz = Class.forName(service.getClass().getName());
			Method findAllFetched = clazz.getMethod("findAllFetched", params);
			return (List<NoTranslation>) findAllFetched.invoke(service, paramsObj);
		} catch (ClassNotFoundException e) {
			logger.error("getDatas -> ClassNotFoundException", e);
			throw new ServiceException("Error getList", e);
		} catch (NoSuchMethodException e) {
			logger.error("getDatas -> NoSuchMethodException", e);
			throw new ServiceException("Error getList", e);
		} catch (SecurityException e) {
			logger.error("getDatas -> SecurityException", e);
			throw new ServiceException("Error getList", e);
		} catch (IllegalAccessException e) {
			logger.error("getDatas -> IllegalAccessException", e);
			throw new ServiceException("Error getList", e);
		} catch (IllegalArgumentException e) {
			logger.error("getDatas -> IllegalArgumentException", e);
			throw new ServiceException("Error getList", e);
		} catch (InvocationTargetException e) {
			logger.error("getDatas -> InvocationTargetException", e);
			throw new ServiceException("Error getList", e);
		}
	}
	
	private List<NField> getNField(List<Field> fields) throws ServiceException{
		List<NField> nfFields = new ArrayList<>();
		for (Field field : fields) {
			BOField annotation = field.getAnnotation(BOField.class);
			if (annotation != null){
				BOField nType = (BOField) annotation;
				nfFields.add(new NField(nType.type(), nType.ofType(), field.getName(), field.getType().getName(), nType.inList()));
			}
		}
		return nfFields;
	}
	
	public NData findAll(Class<?> entity) throws ServiceException{
		List<Map<String, Object>> datas = new ArrayList<>();
		List<NoTranslation> noTranslations = getDatas(entity);
		List<Field> fields = backOfficeService.getFields(entity);
		List<NField> nfFields = getNField(fields);
		for (NoTranslation noTranslation : noTranslations) {
			Map<String, Object> record = new HashMap<>();
			for (Field field : fields) {
				record.put(field.getName(), backOfficeService.getFieldValue(noTranslation, field));
			}
			datas.add(record);
		}
		return new NData(nfFields, datas);
	}

	

}
