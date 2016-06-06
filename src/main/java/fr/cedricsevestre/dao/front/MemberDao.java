package fr.cedricsevestre.dao.front;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.front.Member;

@Repository
@Qualifier(value="frontEntityManagerFactory")
public interface MemberDao extends JpaRepository<Member, Integer> {
	
	@Query("SELECT m FROM Member m WHERE m.login =:login")
	Member FindByLogin(@Param("login") String login);
	
}
