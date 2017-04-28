package fr.cedricsevestre.service.custom;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.annotation.BOService;
import fr.cedricsevestre.dao.custom.ContactDao;
import fr.cedricsevestre.entity.custom.Contact;
import fr.cedricsevestre.service.engine.BaseService;

@BOService
@Service
@Scope(value = "singleton")
public class ContactService extends BaseService<Contact>{

	private Logger logger = Logger.getLogger(ContactService.class);

	@Autowired
	private ContactDao dao;



}
