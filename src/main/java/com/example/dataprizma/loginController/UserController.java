package com.example.dataprizma.loginController;

import com.example.dataprizma.jwt.JwtTokenUtil;
import com.example.dataprizma.jwt.JwtUserDetailsService;
import com.example.dataprizma.loginService.PermissionService;
import com.example.dataprizma.logindto.PermissionDto;
import com.example.dataprizma.logindto.UserDto;
import com.example.dataprizma.loginmodel.Permission;
import com.example.dataprizma.loginmodel.Role;
import com.example.dataprizma.loginmodel.Status;
import com.example.dataprizma.loginmodel.User;
import com.example.dataprizma.loginrepository.PermissionRepository;
import com.example.dataprizma.loginrepository.RoleRepository;
import com.example.dataprizma.loginrepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    PermissionRepository permissionRepository;


    @PostMapping("/tokenizer")
//    @PreAuthorize("hasAuthority('Admin')")

    public ResponseEntity<UserDto> getLoginById(@RequestBody UserDto userDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        } catch (AuthenticationException e) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userDto.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        User user = userRepository.findByEmail(userDto.getEmail());
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
        dto.setRoleId(user.getRole().getId());
        dto.setName(user.getRole().getName());
        dto.setToken(token);
        dto.setStatus(Status.ACTIVE);
        dto.setId(user.getId());
        List<PermissionDto> permissionDtoList = new ArrayList<>();
//        List<Permission> permissionList = permissionService.findAllByRoleId(dto.getRoleId());
        List<Permission> permissionList = (List<Permission>) permissionRepository.findAll();
        for (Permission permission : permissionList){
            PermissionDto permissionDto = new PermissionDto(permission);
            permissionDto.setABoolean(permission.getABoolean());
            permissionDtoList.add(permissionDto);
        }
        dto.setPermissionDtoList(permissionDtoList);
        dto.setPassword("password is invisible");
        return ResponseEntity.ok(dto);
    }



    @PostMapping("/check")
    public ResponseEntity<?> checkToken(){

        return ResponseEntity.ok("");
    }


    @GetMapping("/list")
    public List<UserDto> getAllUsers()
    {

        List<User> userList = (List<User>) userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>(userList.size());
        userList.forEach(user -> userDtoList.add(new UserDto(user)));
        return userDtoList;
    }


    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserDto userDto) {

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPassword1(userDto.getPassword());
        Role role = roleRepository.findById(userDto.getRoleId()).orElseThrow();
        user.setRole(role);
        userRepository.save(user);
        user.setStatus(Status.ACTIVE);
        return new UserDto(user);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateById(@PathVariable(value = "id") Long userId,  @RequestBody UserDto userDto){
        User user = userRepository.findById(userId).orElseThrow();
        user.setEmail(userDto.getEmail());
        user.setStatus(Status.ACTIVE);
        user.setPassword1(userDto.getPassword());
        Role role = roleRepository.findById(userDto.getRoleId()).orElseThrow();
        user.setRole(role);
        userRepository.save(user);
        return ResponseEntity.ok(new UserDto(user));
    }



    @GetMapping("/get-by-email/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(value = "id") Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        user.setStatus(Status.ACTIVE);
        return ResponseEntity.ok().body(new UserDto(user));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id") long userId){
        User user = userRepository.findById(userId).orElseThrow();
        user.setStatus(Status.DELETED);
        userRepository.deleteById(userId);
        return ResponseEntity.ok("succesfully deleted");
    }
}
