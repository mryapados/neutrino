package fr.cedricsevestre.specification.engine;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import fr.cedricsevestre.entity.engine.translation.Translation;

public class TranslationSpecification<T extends Translation> extends IdProviderSpecification<T>{

	public Specification<T> someThingToDo(String someThing) {
		return new Specification<T>() {
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.like(root.get("name"), "%" + someThing + "%");
			}
		};
	}

}
