package fr.cedricsevestre.service.engine;

import java.io.File;
import java.util.ArrayList;
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
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import fr.cedricsevestre.common.Common;
import fr.cedricsevestre.dao.engine.NDataDao;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.MapTemplate;
import fr.cedricsevestre.entity.engine.NData;
import fr.cedricsevestre.entity.engine.Template;
import fr.cedricsevestre.entity.engine.Translation;
import fr.cedricsevestre.exception.ServiceException;

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
	
	public Object getNDataValue(NData nData) throws ServiceException {
		try {
			switch (nData.getVarType()) {
			case INTEGER:
				return nData.getvInteger();
			case FLOAT:
				return nData.getvFloat();
			case DOUBLE:
				return nData.getvDouble();
			case DATETIME:
				return nData.getvDateTime();
			case VARCHAR50:
				return nData.getvVarchar50();
			case VARCHAR255:
				return nData.getvVarchar255();
			case TEXT:
				return nData.getvText();
			case IMAGE:
				return nData.getvPathFile();
			case HTML:
				return nData.getvHtml();
			case FILE:
				return nData.getvPathFile();
			case OBJECT:
				
				Translation dataObject = nData.getvObject();
				System.out.println("ndata object = " + dataObject.getName());
				
				return nData.getvObject();
				
				
				
			case COLLECTION:
				System.out.println("collection");
				List<NData> nDatas = nData.getDatas();
				SortedMap<Integer, Object> objects = new TreeMap<>();
				for (NData data : nDatas) {
					//System.out.println(data.getOrdered() + " ; " + getNDataValue(data));
					objects.put(data.getOrdered(), getNDataValue(data));
				}
				return objects.values();
			default:
				throw new ServiceException("getNDataValue No Type");
			}
			
			
		} catch (PersistenceException e) {
			throw new ServiceException("erreur getNDataValue NData", e);
		}
	}
		
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}



}
