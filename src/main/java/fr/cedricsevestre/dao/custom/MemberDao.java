package fr.cedricsevestre.dao.custom;

import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.UserDao;

@Repository
public interface MemberDao extends UserDao {
	
//	@Query("SELECT m FROM Member m WHERE m.login =:login")
//	Member FindByLogin(@Param("login") String login);
	
}
