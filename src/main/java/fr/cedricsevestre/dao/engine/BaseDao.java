package fr.cedricsevestre.dao.engine;

import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import fr.cedricsevestre.entity.engine.IdProvider;
import fr.cedricsevestre.repository.CustomRepository;
import fr.cedricsevestre.repository.CustomRepositoryFactoryBean;

@NoRepositoryBean
public interface BaseDao<T> extends CustomRepository<T, Integer>, JpaSpecificationExecutor<T> {
	
	
}
