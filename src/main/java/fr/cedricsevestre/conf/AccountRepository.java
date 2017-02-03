package fr.cedricsevestre.conf;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepository<T> extends JpaRepository<T, Long>, AccountRepositoryCustom  {

}