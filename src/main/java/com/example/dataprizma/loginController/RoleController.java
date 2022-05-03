package com.example.dataprizma.loginController;

import com.example.dataprizma.loginService.PermissionService;
import com.example.dataprizma.logindto.RoleDto;
import com.example.dataprizma.loginmodel.Role;
import com.example.dataprizma.loginrepository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/role")
public class RoleController {
//
//    @Autowired
//    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    RoleRepository roleRepository;

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    @PreAuthorize("hasAuthority('List Roles')")
//    public ResponseEntity<Map<String, Object>> listRole(@RequestBody Pagination pagination) {
//        Page<Role> rolePage = roleService.findAll(pagination);
//        Map<String, Object> map = new HashMap<>();
//        if (rolePage != null) {
//            List<Role> roles = rolePage.getContent();
//            List<RoleDto> roleDto = new ArrayList<>(roles.size());
//            roles.forEach(role -> roleDto.add(new RoleDto(role)));
//            map.put("list", roleDto);
//            map.put("total", rolePage.getTotalElements());
//        } else {
//            map.put("list", new ArrayList<>());
//            map.put("total", 0);
//        }
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }

    @GetMapping("/list")
//    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<RoleDto>> getAllUsers() {
        List<Role> roleList = roleRepository.findAll();
        List<RoleDto> roleDtoList = new ArrayList<>(roleList.size());
        roleList.forEach(role -> roleDtoList.add(new RoleDto(role)));
        return ResponseEntity.ok(roleDtoList);
    }

    @RequestMapping(value = "/add-permissions", method = RequestMethod.POST)
    public ResponseEntity<String> listRoleAddPermissionId(@RequestBody RoleDto dto) {
        Role role = roleRepository.findById(dto.getId()).orElseThrow();
        role.getPermissions().clear();
        role.getPermissions().addAll(permissionService.getByPermissionIds(dto.getPermissionIds()));
        roleRepository.save(role);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }


    @RequestMapping(value = "/get-by-id/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<RoleDto> roleById(@PathVariable(name = "id") Long id) {
//        Role role = roleService.getRoleById(id);
//        if (role == null) return new ResponseEntity("NOT_FOUND", HttpStatus.NOT_FOUND);
//        return new ResponseEntity<>(new RoleDto(role), HttpStatus.OK);

        Role role = roleRepository.findById(id).orElseThrow();
        if(role == null) return  new ResponseEntity("NotFound", HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(new RoleDto(role));
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('Admin')")
    public RoleDto createRole(@RequestBody RoleDto roleDto) {
//        Role role = roleDto.convertToRole();
//        role.setCode("ROLE_" + role.getName().toUpperCase());
//        Role roleDB = roleService.createRole(role);
//        if (roleDB == null) return new ResponseEntity<>("Such RoleName exists...", HttpStatus.BAD_REQUEST);
//        return ResponseEntity.ok("SUCCESSFULLY Saved...");

        Role role = new Role();
        role.setName(roleDto.getName());
        role.setCode(roleDto.getCode());
        roleRepository.save(role);
        return new RoleDto(role);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
//    @PreAuthorize("hasAuthority('Admin')"/
    public ResponseEntity<String> updateRole(@PathVariable(value = "id") Long roleId, @RequestBody RoleDto roleDto) {
//        if (roleDto.getId() == null) return new ResponseEntity<>("Such RoleName exists...", HttpStatus.BAD_REQUEST);
//        Role role = roleService.getRoleById(roleDto.getId());
//        if (role == null) return new ResponseEntity<>("NOT_FOUND", HttpStatus.NOT_FOUND);
//        roleDto.convertToRole(role);
//        role.setCode("ROLE_" + roleDto.getName().toUpperCase());
//        Role roleDB = roleService.createRole(role);
//        if (roleDB == null) return new ResponseEntity<>("Such RoleName exists...", HttpStatus.BAD_REQUEST);
//        else return ResponseEntity.ok("SUCCESSFULLY Updated...");

        Role role = roleRepository.findById(roleId).orElseThrow();
        role.setName(roleDto.getName());
        role.setCode(roleDto.getName());
        roleRepository.save(role);
        return ResponseEntity.ok("updated");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<String> deleteRole(@PathVariable(name = "id") Long id) {
//        roleService.deleteRole(id);
//        return ResponseEntity.ok("SUCCESSFUL Deleted...");

        roleRepository.deleteById(id);
        return ResponseEntity.ok("deleted");
    }
}
