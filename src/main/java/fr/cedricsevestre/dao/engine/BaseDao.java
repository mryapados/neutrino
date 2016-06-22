package fr.cedricsevestre.dao.engine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseDao<T> extends JpaRepository<T, Integer> {
	

	
	
//	  T findOne(Long id);
//	  Iterable<T> findAll();
//	  Iterable<T> findAll(Sort sort);
//	  Page<T> findAll(Pageable pageable);
	
}
