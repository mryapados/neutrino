package fr.cedricsevestre.dao.engine;

import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import fr.cedricsevestre.entity.engine.IdProvider;

@NoRepositoryBean
public interface BaseDao<T> extends PagingAndSortingRepository<T, Integer> {
	

	
	
//	  T findOne(Long id);
//	  Iterable<T> findAll();
//	  Iterable<T> findAll(Sort sort);
//	  Page<T> findAll(Pageable pageable);
	
	
//	@Query(nativeQuery = true, value = "SELECT * FROM template INNER JOIN translation ON template.id = translation.id")
//	List<T> test();
//
//	@Query(nativeQuery = true, value = "SELECT * FROM template INNER JOIN translation ON template.id = translation.id WHERE template.:field = :value")
//	List<T> test2(@Param("field") String field, @Param("value") Integer value);
//	
//	
	
	
	
}
