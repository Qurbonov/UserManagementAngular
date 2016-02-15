package mf.uz.services;

import mf.uz.domain.Department;
import mf.uz.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qurbonov on 9/3/2015.
 */
@Service
@Transactional(readOnly = true)
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Transactional(readOnly = false)
    public Department save(Department department) {
        if (department.getId() == null) {
            department = departmentRepository.save(department);
        }
        String keysId = "";

        if (department.getParent() != null) {
            Department parent = findOne(department.getParent().getId());
            parent.setHasChildren(Boolean.TRUE);
            System.out.println(parent.getId());
            keysId = parent.getKEYS_ID() + "-";
            // my changes 
            String DepCoreId = department.getCore_depId();
            if (DepCoreId == null) {
                department.setCore_depId("1");
            } else {
                department.setCore_depId(department.getParent().getCore_depId());
            }
            // 
        } else {
            System.out.println(department.getParent().getId());
        }
        keysId = keysId + department.getId();
        department.setKEYS_ID(keysId);
        return departmentRepository.save(department);
    }

    @Transactional(readOnly = false)
    public Department update(Department department) {
        return departmentRepository.saveAndFlush(department);
    }

    public Department findOne(Long departmentId) {
        return departmentRepository.findOne(departmentId);
    }

    public List<Department> findAll() {
        return departmentRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
    }
    
    
//    find by parent     
    public List<Department> findByParentId(Long parentId) {
        return departmentRepository.findAll(DepartmentSpec.byParentId(parentId));
    }

    public Long countByParentId(Long parentId) {
        return departmentRepository.count(DepartmentSpec.byParentId(parentId));
    }

    @Transactional(readOnly = false)
    public void delete(Long departmentID) {
        Department department = findOne(departmentID);
        Department parent = department.getParent();
        departmentRepository.delete(departmentID);
        if (parent != null) {
            if (countByParentId(parent.getId()) == 0) {
                parent.setHasChildren(Boolean.FALSE);
                departmentRepository.save(parent);
            }
        }
    }

    @Transactional
    public Department create(Department department, Long parentId) {
        Department parent = findOne(parentId);
        department.setParent(parent);
        department = save(department);
        department.setKEYS_ID(parent.getKEYS_ID() + "." + department.getId());
        return save(department);
    }
}
