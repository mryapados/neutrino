package fr.cedricsevestre.service.custom;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.custom.TagDao;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;

@Service
@Scope(value = "singleton")
public class TagService extends BaseService<Tag>{

	private Logger logger = Logger.getLogger(TagService.class);

	@Autowired
	private TagDao tagDao;

	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	





}
