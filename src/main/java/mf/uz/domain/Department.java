package mf.uz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

/**
 * Created by qurbonov on 9/3/2015.
 */
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "departmentName")
    private String departmentName;

    @Column(name = "core_depId")
    private String core_depId;

    @Column(name = "KEYS_ID")
    private String KEYS_ID;

    @Column(name = "IS_HAS_CHILDREN")
    private Boolean hasChildren;
    
    @ManyToOne
    private Department parent;

    public Department() {
    }

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCore_depId() {
        return core_depId;
    }

    public void setCore_depId(String core_depId) {
        this.core_depId = core_depId;
    }

    public String getKEYS_ID() {
        return KEYS_ID;
    }

    public void setKEYS_ID(String KEYS_ID) {
        this.KEYS_ID = KEYS_ID;
    }

    @Override
    public String toString() {
        return "Department{" + "id=" + id + ", departmentName=" + departmentName + ", core_depId=" + core_depId + ", KEYS_ID=" + KEYS_ID + ", haschild=" + hasChildren + '}';
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Department getParent() {
        return parent;
    }

    public void setParent(Department parent) {
        this.parent = parent;
    }

}
