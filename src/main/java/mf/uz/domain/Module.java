package mf.uz.domain;

import javax.persistence.*;

/**
 * Created by qurbonov on 9/2/2015.
 */
@Entity
@Table(name = "Modules")
public class Module {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "moduleName", length = 50)
    private String moduleName;
    @Column(name = "description", length = 150)
    private String description;

    public Module() {
    }

    public Module(Long id, String moduleName, String description) {
        this.id = id;
        this.moduleName = moduleName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
