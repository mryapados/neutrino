package fr.cedricsevestre.service.engine.bo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

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
import fr.cedricsevestre.entity.engine.IdProvider;
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
		
	private Map<String, Field> getFields(Class<?> classObject) throws ServiceException{
		Map<String, Field> fields = new HashMap<>();
		Class<?> superClass = classObject.getSuperclass();
		if (superClass != null){
			fields.putAll(getFields(classObject.getSuperclass()));
		}
		Field[] classObjectFields = classObject.getDeclaredFields();
		for (Field classObjectField : classObjectFields) {
			BOField annotation = classObjectField.getAnnotation(BOField.class);
			if (annotation != null){
				fields.put(classObjectField.getName(), classObjectField);
			}
		}
		return fields;
	}

	@SuppressWarnings("unchecked")
	private Page<T> getDatas(Class<?> entity, Pageable pageable) throws ServiceException{
		try {
			Class<?> params[] = {Pageable.class};
			Object paramsObj[] = {pageable};
			
			logger.debug("getDatas -> Look for " + entity.getSimpleName());			
			Object service = customServiceLocator.getService(entity.getSimpleName());
			logger.debug("getDatas -> Entity found " + entity.getSimpleName());		

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
	public T getData(Class<?> entity, Integer id) throws ServiceException{
				
		try {
			Class<?> params[] = {Integer.class};
			Object paramsObj[] = {id};
			
			logger.debug("getDatas -> Look for " + entity.getSimpleName());			
			Object service = customServiceLocator.getService(entity.getSimpleName());
			logger.debug("getDatas -> Entity found " + entity.getSimpleName());	

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
		NField nField = new NField(field, nType.type(), nType.ofType(), field.getName(), field.getType().getSimpleName(), nType.inList(), nType.inView(), nType.editable(), nType.sortBy(), nType.sortPriority(), nType.defaultField(), nType.displayOrder(), nType.tabName(), nType.groupName(), enumDatas);
	
		String revesibleJoin = null;
		if (revesibleJoin == null){
			OneToMany oneToAnyAnnotation = field.getAnnotation(OneToMany.class);
			if (oneToAnyAnnotation != null) revesibleJoin = oneToAnyAnnotation.mappedBy();
		}
		if (revesibleJoin == null){
			ManyToMany manyToAnyAnnotation = field.getAnnotation(ManyToMany.class);
			if (manyToAnyAnnotation != null) revesibleJoin = manyToAnyAnnotation.mappedBy();
		}
		nField.setRevesibleJoin(revesibleJoin);

		return nField;
	}
	
	private List<NField> getNField(Map<String, Field> fields) throws ServiceException{
		List<NField> nfFields = new ArrayList<>();
		for (Map.Entry<String, Field> e : fields.entrySet()) {
			Field field = e.getValue();
			BOField nType = field.getAnnotation(BOField.class);
			if (nType != null){
				nfFields.add(mkNFieldFromBOField(field, nType));
			}
		}
		return nfFields;
	}
	
	//Retourne une liste de NField par GroupName par TabName
	private Map<String, Map<String, List<NField>>> getMapNField(Map<String, Field> fields) throws ServiceException{
		Map<String, Map<String, List<NField>>> nfTabsGroupsFields = new HashMap<>();
		for (Map.Entry<String, Field> e : fields.entrySet()) {
			Field field = e.getValue();
			BOField nType = field.getAnnotation(BOField.class);
			if (nType != null){
				if (!nfTabsGroupsFields.containsKey(nType.tabName())){
					nfTabsGroupsFields.put(nType.tabName(), new HashMap<>());
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
		Map<String, Field> fields = getFields(entity);
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
		Map<String, Field> fields = getFields(entity);
		Map<String, Map<String, List<NField>>> nMapFields = getMapNField(fields);
		return new NData<T>(nMapFields, getData(entity, id));
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public T add(Class<?> entity) throws ServiceException {
		try {
			return (T) entity.newInstance();
		} catch (InstantiationException e) {
			throw new ServiceException("add -> Error", e) ;
		} catch (IllegalAccessException e) {
			throw new ServiceException("add -> Error", e) ;
		} 
	}
	
	@Override
	public NData<T> copy(Class<?> entity, Integer id) throws ServiceException {
		Map<String, Field> fields = getFields(entity);
		Map<String, Map<String, List<NField>>> nMapFields = getMapNField(fields);
		T data = null;
		if (id == 0){
			data = add(entity);
		} else {
			data = getData(entity, id);
			data.setId(null);
			
			
			
			
			
			
			
			
			
			
			
			
		}
		return new NData<T>(nMapFields, data);
	}
	
	
	
	@SuppressWarnings("unchecked")
	public T saveData(T data) throws ServiceException{
		try {
			Class<?> entity = data.getClass();

			Class<?> params[] = { Object.class };
			Object paramsObj[] = { data };

			logger.debug("getDatas -> Look for " + entity.getSimpleName());			
			Object service = customServiceLocator.getService(entity.getSimpleName());
			logger.debug("getDatas -> Entity found " + entity.getSimpleName());	

			Class<?> clazz = service.getClass();

			Method save = clazz.getMethod("save", params);
			T saved = (T) save.invoke(service, paramsObj);

			persistReverse(data, entity);
			
			return saved;
			
			
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
	
	//TODO persist reverse mapped field
	public void persistReverse(T data, Class<?> classObject) throws ServiceException{
		Map<String, Field> fields = getFields(classObject);
		List<NField> nFields = getNField(fields);
		
		for (NField nField : nFields) {
			if (nField.getRevesibleJoin() != null){
				
				Object object = getFieldValue(data, nField.getField());
				
				if (object instanceof Iterable){
					Iterable list = (Iterable) object;
					for (Object object2 : list) {
						
						IdProvider mapped = (IdProvider) object2;
						
						
						
						Map<String, Field> mappedFields = getFields(mapped.getClass());
						Field mappedField = mappedFields.get(nField.getRevesibleJoin());
						
						setFieldValue(mapped, mappedField, data);
						
//						saveData(mapped);
						
						
						
						
						
						
						System.out.println("             HELLLLLLLLLLLLLLLLLLLLLLLOOOOOOOOOOO " + mapped.getName() + " - " + mapped.getClass());
						
						
						
						
					}
					
					
				}
				
				
				
				
				
				
				//Object mapped = getData(object.getClass(), id)
				
				
			}
		}
		
		return;
	}
	
	
	
	
	private Object getFieldValue(Object object, Field field) throws ServiceException {
		try {
			field.setAccessible(true);
			return field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("Failed to get value from field", e);
			throw new ServiceException("Erreur getFieldValue", e);
		}
	}
	
	private void setFieldValue(Object object, Field field, Object value) throws ServiceException {
		try {
			field.setAccessible(true);
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("Failed to get value from field", e);
			throw new ServiceException("Erreur setFieldValue", e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public T removeDatas(List<T> datas) throws ServiceException{
		try {
			Class<?> entity = datas.get(0).getClass();

			Class<?> params[] = { Iterable.class };
			Object paramsObj[] = { datas };

			logger.debug("getDatas -> Look for " + entity.getSimpleName());			
			Object service = customServiceLocator.getService(entity.getSimpleName());
			logger.debug("getDatas -> Entity found " + entity.getSimpleName());	

			Class<?> clazz = service.getClass();
//			Method[] methods = clazz.getMethods();
//			for (Method method : methods) {
//				System.out.println(method.getName());
//				Class<?>[] parameterTypes = method.getParameterTypes();
//				for (Class<?> class1 : parameterTypes) {
//					System.out.println("	" + class1.getName());
//				}
//			}
			Method remove = clazz.getMethod("remove", params);
			return (T) remove.invoke(service, paramsObj);
			
		} catch (NoSuchMethodException e) {
			logger.error("saveData -> NoSuchMethodException", e);
			throw new ServiceException("Error removeDatas", e);
		} catch (IllegalAccessException e) {
			logger.error("saveData -> IllegalAccessException", e);
			throw new ServiceException("Error removeDatas", e);
		} catch (InvocationTargetException e) {
			logger.error("saveData -> InvocationTargetException", e);
			throw new ServiceException("Error removeDatas", e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
