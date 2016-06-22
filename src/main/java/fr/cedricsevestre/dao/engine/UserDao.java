package fr.cedricsevestre.dao.engine;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import fr.cedricsevestre.entity.engine.User;

@NoRepositoryBean
public interface UserDao extends BaseDao<User> {
	
	@Query("SELECT u FROM #{#entityName} u WHERE u.login =:login")
	User FindByLogin(@Param("login") String login);

}
