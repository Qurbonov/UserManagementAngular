package mf.uz.services;

import mf.uz.domain.Module;
import mf.uz.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qurbonov on 9/2/2015.
 */
@Service
public class ModuleService {
    @Autowired
    ModuleRepository moduleRepository;

    public Module findOne(Long id) {
        return moduleRepository.findOne(id);
    }

    public Module save(Module module) {
        return moduleRepository.save(module);
    }

    public List<Module> findAll() {
        return moduleRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
    }

    @Transactional
    public void delete(Long moduleID) {
        moduleRepository.delete(moduleID);
    }

}
