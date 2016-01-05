package mf.uz.domain;

import javax.persistence.*;

/**
 * Created by qurbonov on 10/6/2015.
 */
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "rolesName", length = 50)
    private String roleName;

    public Role(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
