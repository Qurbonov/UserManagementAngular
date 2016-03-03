package mf.uz.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;

@Entity
@Table(name = "Users")
public class Users implements Serializable, UserDetails, Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "firstname")
    private String firstname;

    @Column(name = "lastname")
    private String lastname;
    
    @Column(name = "hash")
    private String hash;

    @ManyToMany
    private List<Department> department;

    @ManyToMany
    private List<Module> modules;

    @ManyToMany
    private List<Role> roles;

    @ManyToMany
    @Column(name = "permissions")
    private List<Permission> permissions;

    @Override
    public String getName() {
        return username;
    }

    public Users() {
    }

    public Users(Long id, String username, String password, String firstname, String lastname, String hash, List<Department> department, List<Module> modules, List<Role> roles, List<Permission> permissions) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.hash = hash;
        this.department = department;
        this.modules = modules;
        this.roles = roles;
        this.permissions = permissions;
    }

    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", username=" + username + ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname + ", hash=" + hash + ", department=" + department + ", modules=" + modules + ", roles=" + roles + ", permissions=" + permissions + '}';
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<Department> getDepartment() {
        return department;
    }

    public void setDepartment(List<Department> department) {
        this.department = department;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    

}
