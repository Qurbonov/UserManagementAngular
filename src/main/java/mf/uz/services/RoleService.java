package mf.uz.services;

import mf.uz.domain.Role;
import mf.uz.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qurbonov on 10/6/2015.
 */
@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
    }

    public Role findOne(Long id) {
        return roleRepository.findOne(id);
    }

}
