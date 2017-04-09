package fr.cedricsevestre.service.engine.independant.objects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.persistence.PersistenceException;
import javax.servlet.jsp.PageContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.bean.NDataValue;
import fr.cedricsevestre.dao.engine.NDataDao;
import fr.cedricsevestre.entity.engine.independant.objects.MapTemplate;
import fr.cedricsevestre.entity.engine.independant.objects.NData;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema;
import fr.cedricsevestre.entity.engine.independant.objects.NSchema.ScopeType;
import fr.cedricsevestre.entity.engine.independant.objects.NType.ValueType;
import fr.cedricsevestre.entity.engine.notranslation.NoTranslation;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.entity.engine.translation.objects.Template;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;

@Service
@Scope(value = "singleton")
public class NDataService extends BaseService<NData>{

	private Logger logger = Logger.getLogger(NDataService.class);

	@Autowired
	private NDataDao nDataDao;

	public List<NData> findAllForTemplate(Template template) throws ServiceException {
		try {
			return nDataDao.findAllForTemplate(template);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllForTemplate NData", e);
		}
	}
	public List<NData> findAllForMapTemplate(MapTemplate mapTemplate) throws ServiceException {
		try {
			return nDataDao.findAllForMapTemplate(mapTemplate);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAllForMapTemplate NData", e);
		}
	}
	
	public NDataValue getNDataValue(NData nData) throws ServiceException {
		try {
			ValueType nTypeValueType = nData.getVarType();
			
			switch (nData.getVarType()) {
			case INTEGER:
				return new NDataValue(nTypeValueType, nData.getvInteger());
			case FLOAT:
				return new NDataValue(nTypeValueType, nData.getvFloat());
			case DOUBLE:
				return new NDataValue(nTypeValueType, nData.getvDouble());
			case DATETIME:
				return new NDataValue(nTypeValueType, nData.getvDateTime());
			case VARCHAR50:
				return new NDataValue(nTypeValueType, nData.getvVarchar50());
			case VARCHAR255:
				return new NDataValue(nTypeValueType, nData.getvVarchar255());
			case TEXT:
				return new NDataValue(nTypeValueType, nData.getvText());
			case IMAGE:
				return new NDataValue(nTypeValueType, nData.getvPathFile());
			case HTML:
				return new NDataValue(nTypeValueType, nData.getvHtml());
			case FILE:
				return new NDataValue(nTypeValueType, nData.getvPathFile());
			case TOBJECT:
				Translation dataTObject = nData.getvTObject();
				System.out.println("ndata object = " + dataTObject.getName());
				return  new NDataValue(nTypeValueType, dataTObject, dataTObject.getObjectType().toUpperCase());
			case NTOBJECT:
				NoTranslation dataNTObject = nData.getvNTObject();
				System.out.println("ndata object = " + dataNTObject.getName());
				return new NDataValue(nTypeValueType, dataNTObject, dataNTObject.getObjectType().toUpperCase());
			case OBJECT:
				NData dataObject = nData.getvObject();
//				if (dataObject.getVarType() != ValueType.OBJECT){
//					return new NDataValue(nTypeValueType, dataObject);
//				} else {
					return getNDataValue(dataObject);
//				}
				
				
				
			case COLLECTION:
				System.out.println("collection");
				List<NData> nDatas = nData.getDatas();
				SortedMap<Integer, Object> objects = new TreeMap<>();
				for (NData data : nDatas) {
					//System.out.println(data.getOrdered() + " ; " + getNDataValue(data));
					objects.put(data.getOrdered(), getNDataValue(data));
				}
				return new NDataValue(nTypeValueType, objects.values());
			default:
				throw new ServiceException("getNDataValue No Type");
			}
			
			
		} catch (PersistenceException e) {
			throw new ServiceException("erreur getNDataValue NData", e);
		}
	}
		
	public Map<String, Object> getNDatas(MapTemplate mapTemplate) throws ServiceException{
		Template block = mapTemplate.getBlock();
		Map<String, Object> mapNDatas = new HashMap<>(); 
		NSchema nSchema =  block.getSchema();
		List<NData> nDatas = null;
		if (nSchema != null){
			//pb lazy
			//block ne contient pas datas qui n'est pas initialisé car lazy
			//Il faut donc recharger le template en demandant explicitement les datas.
			//Ou charger les datas directement, c'est la méthode choisie ici.
			if (nSchema.getScope() == ScopeType.ALL){
				nDatas = findAllForTemplate(block);
			} else if (nSchema.getScope() == ScopeType.ONE){
				nDatas = findAllForMapTemplate(mapTemplate);
			}
			for (NData nData : nDatas) {
				mapNDatas.put(nData.getPropertyName(), getNDataValue(nData));
			}
		}
		return mapNDatas;
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}



}