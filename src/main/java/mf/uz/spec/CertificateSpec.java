package mf.uz.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mf.uz.domain.Certificate;
import mf.uz.domain.Certificate_;
import mf.uz.domain.Users_;
import org.springframework.data.jpa.domain.Specification;

public class CertificateSpec {
    public static Specification<Certificate> byUserId(Long userId) {
        return (Root<Certificate> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            return cb.equal(root.get(Certificate_.user).get(Users_.id), userId);
        };
    }
}
