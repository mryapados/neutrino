package fr.cedricsevestre.dao.engine;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import fr.cedricsevestre.entity.engine.independant.objects.User;
import fr.cedricsevestre.entity.engine.translation.Translation;

@NoRepositoryBean
public interface UserDao extends BaseDao<User> {
	
	@Query("SELECT u FROM #{#entityName} u WHERE u.login =:login")
	User FindByLogin(@Param("login") String login);

}
