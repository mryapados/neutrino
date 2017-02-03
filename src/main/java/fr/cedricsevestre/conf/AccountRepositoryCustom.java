package fr.cedricsevestre.conf;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AccountRepositoryCustom {
	void sharedCustomMethod(int id);
}