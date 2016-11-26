package fr.cedricsevestre.service.engine.bo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOField;
import fr.cedricsevestre.annotation.BOField.SortType;
import fr.cedricsevestre.bean.NData;
import fr.cedricsevestre.bean.NDatas;
import fr.cedricsevestre.bean.NField;
import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.EntityLocator;
import fr.cedricsevestre.service.engine.ServiceLocator;

@Service
@Scope(value = "singleton")
public class BackOfficeService<T extends IdProvider> implements IBackOfficeService{

	@Autowired
	ServiceLocator customServiceLocator;
	
	@Autowired
	EntityLocator entityLocator;
	
	private Logger logger = Logger.getLogger(BackOfficeService.class);

	public static final String TEMPLATE = "Template";
	public static final String PAGE = "Page";
	
	
	@Deprecated
	public static Class<?> getEntity(String entityName) throws ServiceException{
		System.out.println("getEntity " + entityName);
		try {
			switch (entityName) {
			case TEMPLATE:
				return Template.class;
			case PAGE:
				return fr.cedricsevestre.entity.engine.translation.objects.Page.class;
			default:
				return Class.forName(Common.CUSTOM_ENTITY_PACKAGE + "." + entityName);
			}

		} catch (ClassNotFoundException e) {
			throw new ServiceException("erreur getEntity", e);
		}
	}
	
	private List<Field> getFields(Class<?> classObject) throws ServiceException{
		List<Field> fields = new ArrayList<>();
		Class<?> superClass = classObject.getSuperclass();
		if (superClass != null){
			fields.addAll(getFields(classObject.getSuperclass()));
		}
		Field[] classObjectFields = classObject.getDeclaredFields();
		for (Field classObjectField : classObjectFields) {
			BOField annotation = classObjectField.getAnnotation(BOField.class);
			if (annotation != null){
				fields.add(classObjectField);
			}
		}
		return fields;
	}

	@Deprecated
	private Object getFieldValue(Object object, Field field) throws ServiceException {
	    try {
	        field.setAccessible(true);
	        return field.get(object);
	    } catch (IllegalAccessException e) {
	        logger.error("Failed to get value from field", e);
	        throw new ServiceException("Erreur getFieldValue", e);
	    }
	}

	@SuppressWarnings("unchecked")
	private Page<T> getDatas(Class<?> entity, Pageable pageable) throws ServiceException{
		try {
			Class<?> params[] = {Pageable.class};
			Object paramsObj[] = {pageable};
			
			System.out.println("LOOK FOR = " + entity.getSimpleName());
			
			Object service = customServiceLocator.getService(entity.getSimpleName());
			
			
			System.out.println("FOUND = " + service.getClass().getName());
			
			Class<?> clazz = Class.forName(service.getClass().getName());
			Method findAll;
			try {
				findAll = clazz.getMethod("findAllFetched", params);
			} catch (NoSuchMethodException e) {
				try {
					logger.debug("getDatas -> findAllFetched Not found for " + entity.getSimpleName());
					findAll = clazz.getMethod("findAll", params);
				} catch (NoSuchMethodException e1) {
					logger.error("getDatas -> NoSuchMethodException", e);
					throw new ServiceException("Error getList", e);
				}
			}
			return (Page<T>) findAll.invoke(service, paramsObj);
			
		} catch (ClassNotFoundException e) {
			logger.error("getDatas -> ClassNotFoundException", e);
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
	
	@SuppressWarnings("unchecked")
	private T getData(Class<?> entity, Integer id) throws ServiceException{
				
		try {
			Class<?> params[] = {Integer.class};
			Object paramsObj[] = {id};
			
			System.out.println("LOOK FOR = " + entity.getSimpleName());
			
			Object service = customServiceLocator.getService(entity.getSimpleName());

			System.out.println("FOUND = " + service.getClass().getName());
			

			Class<?> clazz = service.getClass();
			Method findById;
			try {
				findById = clazz.getMethod("findByIdFetched", params);
			} catch (NoSuchMethodException e) {
				try {
					logger.debug("getData -> findByIdFetched Not found for " + entity.getSimpleName());
					findById = clazz.getMethod("findById", params);
				} catch (NoSuchMethodException e1) {
					logger.error("getData -> NoSuchMethodException", e);
					throw new ServiceException("Error getData", e);
				}
			}
			return (T) findById.invoke(service, paramsObj);
			
			

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
	
	private NField mkNFieldFromBOField(Field field, BOField nType){
		List<String> enumDatas = null;
		if (!nType.ofEnum().equals(BOField.Default.class)){
			enumDatas = new ArrayList<>();
			for (Enum<?> e : nType.ofEnum().getEnumConstants()) {
				enumDatas.add(e.name());
			}
		}
		return new NField(nType.type(), nType.ofType(), field.getName(), field.getType().getSimpleName(), nType.inList(), nType.inView(), nType.editable(), nType.sortBy(), nType.sortPriority(), nType.defaultField(), nType.displayOrder(), nType.tabName(), nType.groupName(), enumDatas);
	}
	
	private List<NField> getNField(List<Field> fields) throws ServiceException{
		List<NField> nfFields = new ArrayList<>();
		for (Field field : fields) {
			BOField nType = field.getAnnotation(BOField.class);
			if (nType != null){
				nfFields.add(mkNFieldFromBOField(field, nType));
			}
		}
		return nfFields;
	}
	
	//Retourne une liste de NField par GroupName par TabName
	private Map<String, Map<String, List<NField>>> getMapNField(List<Field> fields) throws ServiceException{
		Map<String, Map<String, List<NField>>> nfTabsGroupsFields = new HashMap<>();
		for (Field field : fields) {
			BOField nType = field.getAnnotation(BOField.class);
			if (nType != null){
				if (!nfTabsGroupsFields.containsKey(nType.tabName())){
					nfTabsGroupsFields.put(nType.tabName(), new HashMap<>());
					System.out.println(nType.tabName());
				}
				Map<String, List<NField>> nfGroupsFields = nfTabsGroupsFields.get((nType.tabName()));
				
				if (!nfGroupsFields.containsKey(nType.groupName())){
					nfGroupsFields.put(nType.groupName(), new ArrayList<>());
				}
				List<NField> nfFields = nfGroupsFields.get((nType.groupName()));
				nfFields.add(mkNFieldFromBOField(field, nType));
			}
		}
		return nfTabsGroupsFields;
	}

	@Override
	public NDatas<T> findAll(Class<?> entity) throws ServiceException{
		return findAll(entity, null);
	}
	@Override
	public NDatas<T> findAll(Class<?> entity, Pageable pageRequest) throws ServiceException{		
		List<Field> fields = getFields(entity);
		List<NField> nFields = getNField(fields);
		pageRequest = transformPageRequest(nFields, pageRequest);
		return new NDatas<T>(nFields, getDatas(entity, pageRequest));
	}
	
	private Pageable transformPageRequest(List<NField> nfFields, Pageable pageRequest){
		Sort sort = pageRequest.getSort();
		TreeMap<Integer, Sort> treeMap = new TreeMap<>();
		for (NField nField : nfFields) {
			if (nField.getSortBy() != SortType.NULL){
				Direction direction = null;
				if (nField.getSortBy() == SortType.ASC) direction = Direction.ASC;
				else direction = Direction.DESC;
				Integer key = nField.getSortPriority() * 100;
				while (treeMap.containsKey(key)) key += 1;
				treeMap.put(key, new Sort(direction, nField.getName()));
			}
		}
		for (Map.Entry<Integer, Sort> andSort : treeMap.entrySet()) {
			if (sort == null) sort = andSort.getValue();
			else sort = sort.and(andSort.getValue());
		}
		return new PageRequest(pageRequest.getPageNumber(), pageRequest.getPageSize(), sort);
	}

	@Override
	public NData<T> findOne(Class<?> entity, Integer id) throws ServiceException {
		List<Field> fields = getFields(entity);
		Map<String, Map<String, List<NField>>> nMapFields = getMapNField(fields);
		return new NData<T>(nMapFields, getData(entity, id));
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public T saveData(T data) throws ServiceException{
		try {
			Class<?> entity = data.getClass();
			
			
			
			
			
			Class<?> params[] = { Object.class };
			Object paramsObj[] = { data };

			System.out.println("LOOK FOR = " + entity.getSimpleName());

			Object service = customServiceLocator.getService(entity.getSimpleName());

			System.out.println("FOUND = " + service.getClass().getName());

			Class<?> clazz = service.getClass();
			
			
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				System.out.println(method.getName());
				
				Class<?>[] parameterTypes = method.getParameterTypes();
				for (Class<?> class1 : parameterTypes) {
					System.out.println("	" + class1.getName());
				}
				
			}
			
			
			
			Method save = clazz.getMethod("save", params);
			return (T) save.invoke(service, paramsObj);

		} catch (NoSuchMethodException e) {
			logger.error("saveData -> NoSuchMethodException", e);
			throw new ServiceException("Error saveData", e);
		} catch (IllegalAccessException e) {
			logger.error("saveData -> IllegalAccessException", e);
			throw new ServiceException("Error saveData", e);
		} catch (InvocationTargetException e) {
			logger.error("saveData -> InvocationTargetException", e);
			throw new ServiceException("Error saveData", e);
		}
	}
	
}
