package fr.cedricsevestre.service.engine.independant.objects;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.cedricsevestre.dao.engine.UserDao;
import fr.cedricsevestre.entity.engine.independant.objects.User;
import fr.cedricsevestre.entity.engine.translation.Translation;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.BaseService;

@Service
@Scope(value = "singleton")
public class UserService extends BaseService<User>{

	private Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;

	public User findByLogin(String login) throws ServiceException {
		try {
			return userDao.FindByLogin(login);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findByLogin player", e);
		}
	}

	

	
	

}
