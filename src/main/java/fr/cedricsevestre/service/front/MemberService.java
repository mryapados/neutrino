package fr.cedricsevestre.service.front;

import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.cedricsevestre.dao.front.MemberDao;
import fr.cedricsevestre.entity.front.Member;
import fr.cedricsevestre.exception.ServiceException;

@Service
@Scope(value = "singleton")
public class MemberService{

	private Logger logger = Logger.getLogger(MemberService.class);

	@Autowired
	private MemberDao memberDao;

	@Transactional
	public Member save(Member member) throws ServiceException {
		logger.debug("appel de la methode save Member " + member.getLogin());
		try {
			member.setLogin(member.getLogin().toLowerCase());
			return memberDao.save(member);
		} catch (PersistenceException e) {
			logger.error("erreur save Member " + e.getMessage());
			throw new ServiceException("erreur save Member", e);
		}
	}

	@Transactional
	public void remove(Member member) throws ServiceException {
		logger.debug("appel de la methode remove Member " + member.getLogin());
		try {
			memberDao.delete(member);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Member " + e.getMessage());
			throw new ServiceException("Member id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Member " + e.getMessage());
			throw new ServiceException("erreur remove Member", e);
		} catch (Exception e) {
			logger.error("erreur remove Member " + e.getMessage());
			throw new ServiceException("erreur remove Member", e);
		}
	}

	@Transactional(rollbackFor = ServiceException.class)
	public void removeById(Integer id) throws ServiceException {
		logger.debug("appel de la methode removeById Member id " + id);
		try {
			memberDao.delete(id);
		} catch (EmptyResultDataAccessException e) {
			logger.error("erreur remove Member " + e.getMessage());
			throw new ServiceException("Member id doesn't exist", e);
		} catch (PersistenceException e) {
			logger.error("erreur remove Member " + e.getMessage());
			throw new ServiceException("erreur removeById Member", e);
		} catch (Exception e) {
			logger.error("erreur remove Member " + e.getMessage());
			throw new ServiceException("erreur removeById Member", e);
		}
	}
	
	public Member findById(Integer id) throws ServiceException {
		try {
			return memberDao.findOne(id);
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findById player", e);
		}
	}
	public List<Member> findAll() throws ServiceException {
		try {
			return memberDao.findAll();
		} catch (PersistenceException e) {
			throw new ServiceException("erreur findAll Member", e);
		}
	}
	
	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}






}
