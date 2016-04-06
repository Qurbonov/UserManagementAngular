/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mf.uz.spec;

import javax.persistence.criteria.*;
import mf.uz.domain.Department;
import mf.uz.domain.Department_;
import mf.uz.domain.Users;
import mf.uz.domain.Users_;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Qurbonov
 */
public class UserSpec {

    public static Specification<Users> findByUsername(final String username) {
        return (Root<Users> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> cb.equal(root.get(Users_.username), username);
    }

    public static Specification<Users> byDerpartmentId(Long departmentId) {
        return (Root<Users> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            Root<Department> department = cq.from(Department.class);
            return cb.and(
                    cb.equal(department.get(Department_.id), departmentId),
                    cb.isMember(department, root.get(Users_.department))
            );
        };
    }
}
