package fr.cedricsevestre.dao.front;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.entity.front.Tag;

@Repository
@Qualifier(value="frontEntityManagerFactory")
public interface TagDao extends JpaRepository<Tag, Integer> {
	
	@Query("SELECT f FROM Tag f WHERE f.word =:word")
	Tag FindByWord(@Param("word") String word);
	
	
	
}
