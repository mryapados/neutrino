package fr.cedricsevestre.dao.engine;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.custom.Member;
import fr.cedricsevestre.entity.engine.Lang;
import fr.cedricsevestre.entity.engine.User;

@NoRepositoryBean
public interface UserDao extends BaseDao<User> {
	
	@Query("SELECT u FROM #{#entityName} u WHERE u.login =:login")
	User FindByLogin(@Param("login") String login);

}
