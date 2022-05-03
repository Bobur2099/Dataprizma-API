package com.example.dataprizma.loginmodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
//@EqualsAndHashCode(callSuper = true)
@Table(name = "permissions")
public class Permission{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSION_SEQUENCE")
    @SequenceGenerator(sequenceName = "PERMISSION_SEQUENCE", allocationSize = 1, name = "PERMISSION_SEQUENCE")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "class")
    private String bl;

    @Column(name = "bool_p")
    private Boolean aBoolean;

    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "Permission{" +
                "id= " + id + ", " +
                "name= " + name + ", " +
                "type= " + type + ", " +
                "bl= " + bl  +
                "}";
    }
}

