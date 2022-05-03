package com.example.dataprizma.logindto;


import com.example.dataprizma.loginmodel.Status;
import com.example.dataprizma.loginmodel.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private Status status;
    private List<PermissionDto> permissionDtoList;
    private Long roleId;
    private String name;
    private String token;
    private String password1;


    public UserDto(User user){
        if(user.getId() != null)
            setId(user.getId());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
        setStatus(Status.ACTIVE);
        setPassword1(user.getPassword1());
        if (user.getRole() != null){
            setRoleId(user.getRole().getId());
            setName(user.getRole().getName());
        }
    }


}
