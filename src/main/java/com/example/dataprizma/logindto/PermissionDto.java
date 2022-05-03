package com.example.dataprizma.logindto;


import com.example.dataprizma.loginmodel.Permission;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PermissionDto {
    private Long id;
    private String name;
    private String type;
    private String bl;
    private Boolean aBoolean;

    public PermissionDto(Permission permission) {
        if (permission.getId() != null)
            setId(permission.getId());
        setName(permission.getName());
        setType(permission.getType());
        setBl(permission.getBl());
        setABoolean(permission.getABoolean());
    }

    public Permission convertToPermission() {
        Permission permission = new Permission();
        return convertToPermission(permission);
    }

    public Permission convertToPermission(Permission permission) {
        if (id != null)
            permission.setId(id);
        permission.setName(name);
        permission.setType(type);
        permission.setBl(bl);
        permission.setABoolean(aBoolean);
        return permission;
    }
}
