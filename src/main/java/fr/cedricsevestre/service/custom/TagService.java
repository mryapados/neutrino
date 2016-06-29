package fr.cedricsevestre.service.custom;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.dao.custom.TagDao;
import fr.cedricsevestre.entity.custom.Tag;
import fr.cedricsevestre.service.engine.BaseService;

@Service
@Scope(value = "singleton")
public class TagService extends BaseService<Tag>{

	private Logger logger = Logger.getLogger(TagService.class);

	@Autowired
	private TagDao tagDao;

	






}
