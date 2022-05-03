package com.example.dataprizma.logindto;


import com.example.dataprizma.loginmodel.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RoleDto {

    private Long id;
    private String name;
    private String code;
    private List<Long> permissionIds;


    public RoleDto(Role role){
        if(role.getId() != null)
            setId(role.getId());
        setName(role.getName());

    }

    public Role convertToRole() {
        Role role = new Role();
        return convertToRole(role);
    }

    public Role convertToRole(Role role) {
        if (id != null)
            role.setId(id);
        role.setName(name);
        role.setCode(code);
        return role;
    }
}
