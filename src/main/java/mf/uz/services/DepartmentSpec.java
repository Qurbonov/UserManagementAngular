/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mf.uz.services;

import javax.persistence.criteria.*;
import mf.uz.domain.Department;
import mf.uz.domain.Department_;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author Qurbonov
 */
public class DepartmentSpec {

    public static Specification<Department> byParentId(final Long parentId) {
        return (Root<Department> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            return parentId == null
                    ? cb.isNull(root.get(Department_.parent))
                    : cb.equal(root.get(Department_.parent).get(Department_.id), parentId);
        };
    }

    public static Specification<Department> getDepartmentsByUser(final Long id) {
        return (Root<Department> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            
            //// add to here user ID = department ID
            return null;
        };
    }
}
