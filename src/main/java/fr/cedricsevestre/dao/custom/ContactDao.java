package fr.cedricsevestre.dao.custom;

import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.BaseDao;
import fr.cedricsevestre.entity.custom.Contact;

@Repository
public interface ContactDao extends BaseDao<Contact> {
		
}
