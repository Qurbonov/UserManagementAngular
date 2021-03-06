package mf.uz.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
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
import javax.servlet.http.HttpServletRequest;
import mf.uz.domain.*;
import mf.uz.services.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.intsoft.UZDSTCertificate;

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
    @Autowired
    TokenService tokenService;
    @Autowired
    CertificateService certificateService;

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

    @RequestMapping(value = "/api/certificates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Certificate>> getCertificates() {
        Collection<Certificate> certs = certificateService.findAll();
        return new ResponseEntity<>(certs, HttpStatus.OK);
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

    @RequestMapping(value = "/api/users/{userId}/certificates", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Certificate>> getUserCertificates(@PathVariable Long userId) {
        Collection<Certificate> certs = certificateService.findByUserId(userId);
        return new ResponseEntity<>(certs, HttpStatus.OK);
    }

//  Find all departments
    @RequestMapping(value = "/api/departments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Department>> getDepartments() {
        Collection<Department> departments = departmentService.findByParentId(null);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @RequestMapping(value = "/api/departments/{depertmentId}/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Users>> getUserDepartments(@PathVariable Long depertmentId) {
        Collection<Users> userses = usersService.findByUserId(depertmentId);
//        Collection<Certificate> certs = departmentService.findByUserId(userId);
//        return new ResponseEntity<>(certs, HttpStatus.OK);
        return new ResponseEntity<>(userses, HttpStatus.OK);
    } 

    @RequestMapping(value = "/api/allDepartments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Department>> getAllDepartments() {
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

    @RequestMapping(value = "/api/departments/{id}/children", method = RequestMethod.GET)
    @ResponseBody
    public Object getChildren(@PathVariable Long id) {
        Collection<Department> departments = departmentService.findByParentId(id);
        return new ResponseEntity<>(departments, HttpStatus.OK);
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
//    Generate UUID

    @RequestMapping(value = "/api/uuid", method = RequestMethod.POST)
    public ResponseEntity<Token> SetUUID(HttpServletRequest request) {
        return new ResponseEntity<>(tokenService.generate(request.getRemoteAddr()), HttpStatus.OK);
    }

//    Get UUID
    @RequestMapping(value = "/api/uuid", method = RequestMethod.GET)
    public ResponseEntity<Collection<Token>> GetUUID() {
        Collection<Token> token = tokenService.findAll();
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam Long userId) {
        try {
            Certificate certificate = new Certificate();
            UZDSTCertificate tCertificate = new UZDSTCertificate(file.getBytes());
            certificate.setContent(tCertificate.getSignature());
            certificate.setDateFrom(tCertificate.getNotAfter());
            certificate.setDateTo(tCertificate.getNotBefore());
            certificate.setCertFileName(file.getOriginalFilename());
            certificate.setId(Long.MIN_VALUE);
            usersService.addCertificate(userId, certificate);
            String filename = file.getOriginalFilename();
            String directory = "e://";
            String filepath = Paths.get(directory, filename).toString();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(file.getBytes());
            stream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
