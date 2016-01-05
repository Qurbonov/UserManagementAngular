package mf.uz.domain;

import javax.persistence.*;

/**
 * Created by qurbonov on 10/6/2015.
 */
@Entity
@Table(name = "Permission")
public class Permission {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "permissionName", length = 50)
    private String permissionName;
}
