package mf.uz.controller;

import java.security.Principal;
import java.util.*;
import mf.uz.domain.Department;
import mf.uz.domain.Users;
import mf.uz.services.DepartmentService;
import mf.uz.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mf.uz.domain.Module;
import mf.uz.domain.Role;
import mf.uz.services.ModuleService;
import mf.uz.services.RoleService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by qurbonov on 10/9/2015.
 */
@RestController
public class RestApi {

    @Autowired
    UsersService usersService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    ModuleService moduleService;
    @Autowired
    RoleService roleService;
    
    @RequestMapping("/api/auth")
    public Object auth(Principal principal) {
        Map<String, Object> result = new TreeMap<>();
            result.put("username", principal);
        return result; 
    } 

//  Find all users
    @RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Users>> getUsers(Principal principal) {
        Collection<Users> userses = usersService.findAll();
        return new ResponseEntity<>(userses, HttpStatus.OK);
    }
//  Find one user
    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Users> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(usersService.findOne(id), HttpStatus.OK);
    }
//  Save user
    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public Users setUsers(@RequestBody Users users) {
        return usersService.save(users);
    }
//  Edit user
    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.PUT)
    public Users editUsers(@RequestBody Users users) {
        return usersService.save(users);
    }
//  Delete user
    @RequestMapping(value = "/api/users/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteUser(@PathVariable(value = "id") String id) {
        usersService.delete(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

//  Find all departments
    @RequestMapping(value = "/api/departments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Department>> getDepartments() {
        Collection<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }   
            
//  Find one department
    @RequestMapping(value = "/api/departments/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        return new ResponseEntity<>(departmentService.findOne(id), HttpStatus.OK); 
    }

//  Save department
    @RequestMapping(value = "/api/departments", method = RequestMethod.POST)
    public Department setDepartments(@RequestBody Department department) {
        return departmentService.save(department);
    }
//  Edit department
    @RequestMapping(value = "/api/departments/{id}", method = RequestMethod.PUT)
    public Department editDepartment(@RequestBody Department department) {
        return departmentService.save(department);
    }
//  Remove department
    @RequestMapping(value = "/api/departments/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteDepartments(@PathVariable(value = "id") String id) {
        departmentService.delete(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

//  Find all modules
    @RequestMapping(value = "/api/modules", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Module>> getModules() {
        Collection<Module> modules = moduleService.findAll();
        return new ResponseEntity<>(modules, HttpStatus.OK);
    } 
//  Find one module
    @RequestMapping(value = "/api/modules/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Module> getModule(@PathVariable Long id) {
        return new ResponseEntity<>(moduleService.findOne(id), HttpStatus.OK);
    }
//  Save module
    @RequestMapping(value = "/api/modules", method = RequestMethod.POST)
    public Module setModule(@RequestBody Module module) {
        return moduleService.save(module);
    }
//  Edit module
    @RequestMapping(value = "/api/modules/{id}", method = RequestMethod.PUT)
    public Module editModule(@RequestBody Module module) {
        return moduleService.save(module);
    }
//  Delete module
    @RequestMapping(value = "/api/modules/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteModule(@PathVariable(value = "id") String id) {
        moduleService.delete(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
   
//    Role JSON
    @RequestMapping(value = "/api/roles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Role>> getRoles() {
        Collection<Role> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

}
