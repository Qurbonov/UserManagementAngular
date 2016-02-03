package mf.uz.util;

import mf.uz.model.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JpaUtils {

    public static Predicate[] toArray(List<Predicate> predicates) {
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    public static <T> List<Predicate> getPredicates(List<Specification<T>> specs, Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        final List<Predicate> predicates = new ArrayList<>();
        for (Specification<T> spec : specs) {
            if (spec != null) {
                Predicate predicate = spec.toPredicate(root, cq, cb);
                if (predicate != null) {
                    predicates.add(predicate);
                }
            }
        }
        return predicates;
    }

    public static <T> Predicate[] getPredicateArray(List<Specification<T>> specs, Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return toArray(getPredicates(specs, root, cq, cb));
    }

    @SafeVarargs
    public static <T> Specification<T> and(Specification<T>... specs) {
        return and(Arrays.asList(specs));
    }

    public static <T> Specification<T> and(final List<Specification<T>> specs) {
        return (root, cq, cb) -> cb.and(toArray(getPredicates(specs, root, cq, cb)));
    }

    @SafeVarargs
    public static <T> Specification<T> or(Specification<T>... specs) {
        return or(Arrays.asList(specs));
    }

    public static <T> Specification<T> or(final List<Specification<T>> specs) {
        return (root, cq, cb) -> cb.or(toArray(getPredicates(specs, root, cq, cb)));
    }
}
