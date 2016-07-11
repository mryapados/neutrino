package fr.cedricsevestre.dao.engine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BaseDao<T> extends PagingAndSortingRepository<T, Integer> {
	

	
	
//	  T findOne(Long id);
//	  Iterable<T> findAll();
//	  Iterable<T> findAll(Sort sort);
//	  Page<T> findAll(Pageable pageable);
	
}
