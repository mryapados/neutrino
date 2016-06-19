package fr.cedricsevestre.dao.custom;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.cedricsevestre.dao.engine.BaseDao;
import fr.cedricsevestre.entity.custom.Tag;

@Repository
public interface TagDao extends BaseDao<Tag> {
	
	@Query("SELECT f FROM Tag f WHERE f.word =:word")
	Tag FindByWord(@Param("word") String word);
	
	
	
}
