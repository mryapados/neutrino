package fr.cedricsevestre.service.custom;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.entity.custom.Member;
import fr.cedricsevestre.exception.ServiceException;
import fr.cedricsevestre.service.engine.UserService;

@Service
@Scope(value = "singleton")
public class MemberService extends UserService{


}
