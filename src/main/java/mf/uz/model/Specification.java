package mf.uz.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public interface Specification<T> {

    Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb);
}
