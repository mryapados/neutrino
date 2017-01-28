package fr.cedricsevestre.service.engine.bo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

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
public class BackOfficeService implements IBackOfficeService{
	@PersistenceContext
	private EntityManager em;
	  
	@Autowired
	ServiceLocator customServiceLocator;
	
	@Autowired
	EntityLocator entityLocator;
	
	private Logger logger = Logger.getLogger(BackOfficeService.class);
		
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

	private Map<String, Field> getMapFields(Class<?> classObject) throws ServiceException{
		Map<String, Field> fields = new HashMap<>();
		Class<?> superClass = classObject.getSuperclass();
		if (superClass != null){
			fields.putAll(getMapFields(classObject.getSuperclass()));
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
	private Page<IdProvider> getDatas(Class<?> entity, Pageable pageable) throws ServiceException{
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
			return (Page<IdProvider>) findAll.invoke(service, paramsObj);
			
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
	public IdProvider getData(Class<?> entity, Integer id) throws ServiceException{
				
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
			return (IdProvider) findById.invoke(service, paramsObj);
			
			

		} catch (ClassNotFoundException e) {
			logger.error("getData -> ClassNotFoundException", e);
			throw new ServiceException("Error getList", e);
		} catch (SecurityException e) {
			logger.error("getData -> SecurityException", e);
			throw new ServiceException("Error getList", e);
		} catch (IllegalAccessException e) {
			logger.error("getData -> IllegalAccessException", e);
			throw new ServiceException("Error getList", e);
		} catch (IllegalArgumentException e) {
			logger.error("getData -> IllegalArgumentException", e);
			throw new ServiceException("Error getList", e);
		} catch (InvocationTargetException e) {
			logger.error("getData -> InvocationTargetException", e);
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
		if (revesibleJoin != null && revesibleJoin.equals("")) revesibleJoin = null;
		nField.setRevesibleJoin(revesibleJoin);

		return nField;
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
	public NDatas<IdProvider> findAll(Class<?> entity) throws ServiceException{
		return findAll(entity, null);
	}
	@Override
	public NDatas<IdProvider> findAll(Class<?> entity, Pageable pageRequest) throws ServiceException{		
		List<Field> fields = getFields(entity);
		List<NField> nFields = getNField(fields);
		pageRequest = transformPageRequest(nFields, pageRequest);
		return new NDatas<IdProvider>(nFields, getDatas(entity, pageRequest));
	}
	
	private Pageable transformPageRequest(List<NField> nfFields, Pageable pageRequest){
		Sort sort = pageRequest.getSort();
		Map<Integer, Sort> treeMap = new TreeMap<>();
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
	public NData<IdProvider> findOne(Class<?> entity, Integer id) throws ServiceException {
		List<Field> fields = getFields(entity);
		Map<String, Map<String, List<NField>>> nMapFields = getMapNField(fields);
		return new NData<IdProvider>(nMapFields, getData(entity, id));
	}
	
	
	
	
	@SuppressWarnings("unchecked")
	public IdProvider add(Class<?> entity) throws ServiceException {
		try {
			return (IdProvider) entity.newInstance();
		} catch (InstantiationException e) {
			throw new ServiceException("add -> Error", e) ;
		} catch (IllegalAccessException e) {
			throw new ServiceException("add -> Error", e) ;
		} 
	}
	
	@Override
	public NData<IdProvider> copy(Class<?> entity, Integer id) throws ServiceException {
		List<Field> fields = getFields(entity);
		Map<String, Map<String, List<NField>>> nMapFields = getMapNField(fields);
		IdProvider data = null;
		if (id == 0){
			data = add(entity);
		} else {
			data = getData(entity, id);
			data.setId(null);
			saveData(data);

		}
		return new NData<IdProvider>(nMapFields, data);
	}
	
	private IdProvider completeData(Class<?> entity, IdProvider data, List<NField> nFields, IdProvider origin) throws ServiceException{
		// get data original if id != null
		IdProvider result = data;
		// for each field not editable, set original value to data field
		for (NField nField : nFields) {
			if (!nField.isEditable()){
				Field field = nField.getField();
				setFieldValue(result, field, getFieldValue(origin, field));
			}
		}
		return result;
	}
	
	private IdProvider persistData(Class<?> entity, IdProvider data) throws ServiceException{
		try {
			Class<?> params[] = { Object.class };
			Object paramsObj[] = { data };
	
			logger.debug("persistData -> Look for " + entity.getSimpleName());			
			Object service = customServiceLocator.getService(entity.getSimpleName());
			logger.debug("persistData -> Entity found " + entity.getSimpleName());	
	
			Class<?> clazz = service.getClass();
	
			Method save = clazz.getMethod("save", params);
			IdProvider saved = (IdProvider) save.invoke(service, paramsObj);
			
			return saved;
		} catch (ClassNotFoundException e) {
			logger.error("persistData -> ClassNotFoundException", e);
			throw new ServiceException("Error getList", e);
		} catch (NoSuchMethodException e) {
			logger.error("persistData -> NoSuchMethodException", e);
			throw new ServiceException("Error saveData", e);
		} catch (IllegalAccessException e) {
			logger.error("persistData -> IllegalAccessException", e);
			throw new ServiceException("Error saveData", e);
		} catch (InvocationTargetException e) {
			logger.error("persistData -> InvocationTargetException", e);
			throw new ServiceException("Error saveData", e);
		}
	}

	public IdProvider saveData(IdProvider data) throws ServiceException{
		IdProvider result = data;

		Class<?> entity = data.getClass();

		List<Field> fields = getFields(entity);
		List<NField> nFields = getNField(fields);
		
		IdProvider origin = null; 
		if (data != null) {
			origin = getData(entity, data.getId());
		}
		
		
		result = completeData(entity, result, nFields, origin);
		result = persistData(entity, result);

		persistReverse(data, entity, nFields, origin);
		
		return result;
	}
	

	
	

	
	public List<?> getAllNotAffected(String entityName, String fieldName, Integer ownerId, Integer startPosition, Integer maxResult) throws ServiceException{
		String query = "SELECT e FROM " + entityName + " e WHERE e." + fieldName + " IS NULL OR e." + fieldName + ".id = :ownerId";
		System.out.println(query);
		return em.createQuery(query)
				.setParameter("ownerId", ownerId)
				.setFirstResult(startPosition)
				.setMaxResults(maxResult)
				.getResultList();
	}
	public List<?> getAll(String entityName, Integer startPosition, Integer maxResult) throws ServiceException{
		String query = "SELECT e FROM " + entityName + " e";
		System.out.println(query);
		return em.createQuery(query)
				.setFirstResult(startPosition)
				.setMaxResults(maxResult)
				.getResultList();
	}
	
	public static Type[] findGenericTypeOfField(Field field) throws IllegalArgumentException{
		Type genericFieldType = field.getGenericType();
		if(genericFieldType instanceof ParameterizedType){
		    ParameterizedType aType = (ParameterizedType) genericFieldType;
		    return  aType.getActualTypeArguments();
		} else throw new IllegalArgumentException();
	}
	
	public void persistReverse(IdProvider data, Class<?> classObject, List<NField> nFields, IdProvider origin) throws ServiceException{
		for (NField nField : nFields) {
			if (nField.getRevesibleJoin() != null){
				Object object = getFieldValue(data, nField.getField());
				if (object instanceof Iterable){
					Object originObject = null;
					if (origin != null) originObject = getFieldValue(origin, nField.getField());	
					
					System.out.println("         INSTANCE OF Iterable");
					Class<?> clazz = (Class<?>) findGenericTypeOfField(nField.getField())[0];
					Map<String, Field> clazzFields = getMapFields(clazz);
					System.out.println("             clazz " + clazz);
					System.out.println("             Field : " + nField.getRevesibleJoin());
					for (Map.Entry<String, Field> e : clazzFields.entrySet()) {
						Field field = e.getValue();
						System.out.println("             	 Field : " + field.getName());
					}
					Field clazzField = clazzFields.get(nField.getRevesibleJoin());
					System.out.println("             Field found : " + (clazzField != null));
					
					List<?> list = (List<?>) object;
//					List<?> originList = null;
//					if (originObject != null) {
//						originList = (List<?>) originObject;
//						for (Object object2 : originList) {
//							IdProvider mapped = (IdProvider) object2;
//							System.out.println("             MAPPED " + mapped.getName() + " - " + mapped.getClass() + " - " + clazzField);
//	
//							Object mappedFieldValue = getFieldValue(mapped, clazzField);
//							
//							
//							
//							
//							if (mappedFieldValue instanceof Iterable){
//								// ManyToMany
//								List<Object> mappedList = (List<Object>) getFieldValue(mapped, clazzField);
//								System.out.println("             DEJA DANS LA LISTE : " + mappedList.contains(data));
//								if (!mappedList.contains(data) && !list.contains(data)){
//									System.out.println("             REMOVE : " + data.getId());
//									
////									mappedList.remove(data);
////									setFieldValue(mapped, clazzField, mappedList);
////									saveData(mapped);
//								}
//							} else {
//								// ManyToOne
//	
//							}
//						}
//					}
					
					
					
					
					
					List<?> originList = (List<?>) originObject;
					for (Object object2 : originList) {
						IdProvider mapped = (IdProvider) object2;
						System.out.println("             MAPPED " + mapped.getName() + " - " + mapped.getClass());

						Object mappedFieldValue = getFieldValue(mapped, clazzField);
						if (mappedFieldValue instanceof Iterable){
							// ManyToMany
							List<Object> mappedList = (List<Object>) getFieldValue(mapped, clazzField);
							System.out.println("             mappedList : " + mappedList.toString());
							for (Object object3 : mappedList) {
								System.out.println("             object3 : ");
							}
							
							
							
							
							
							
							
							//System.out.println("             DEJA DANS LA LISTE : " + mappedList.contains(data));

							
							
							
							
							
							
							
							
						} else {
							// ManyToOne
		
						}
					}
					
					
					

					
					for (Object object2 : list) {
						IdProvider mapped = (IdProvider) object2;
						System.out.println("             MAPPED " + mapped.getName() + " - " + mapped.getClass());

						Object mappedFieldValue = getFieldValue(mapped, clazzField);
						if (mappedFieldValue instanceof Iterable){
							// ManyToMany
							List<Object> mappedList = (List<Object>) getFieldValue(mapped, clazzField);
							System.out.println("             DEJA DANS LA LISTE : " + mappedList.contains(data));
							if (!mappedList.contains(data)){
								mappedList.add(data);
							}
							setFieldValue(mapped, clazzField, mappedList);
							saveData(mapped);
						} else {
							// ManyToOne
							boolean different = !mappedFieldValue.equals(data);
							if (mappedFieldValue != null && different){
								throw new ServiceException("Can't override field value on " + nField.getRevesibleJoin());
							}
							if (mappedFieldValue != null && different){
								setFieldValue(mapped, clazzField, data);
								saveData(mapped);
							}
						}
					}
				} else {
					if (object != null) System.out.println("		object is : " + object.getClass().toString());
				}				
				
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
	public IdProvider removeDatas(List<IdProvider> datas) throws ServiceException{
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
			return (IdProvider) remove.invoke(service, paramsObj);
			
		} catch (ClassNotFoundException e) {
			logger.error("removeDatas -> ClassNotFoundException", e);
			throw new ServiceException("Error getList", e);
		} catch (NoSuchMethodException e) {
			logger.error("removeDatas -> NoSuchMethodException", e);
			throw new ServiceException("Error removeDatas", e);
		} catch (IllegalAccessException e) {
			logger.error("removeDatas -> IllegalAccessException", e);
			throw new ServiceException("Error removeDatas", e);
		} catch (InvocationTargetException e) {
			logger.error("removeDatas -> InvocationTargetException", e);
			throw new ServiceException("Error removeDatas", e);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
}
