package fr.cedricsevestre.dao.front;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.engine.User;

@Repository
@Qualifier(value="frontEntityManagerFactory")
public interface UserDao extends JpaRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.login =:login")
	User FindByLogin(@Param("login") String login);
	
	
	
}
