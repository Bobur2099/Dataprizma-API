package com.example.dataprizma.loginController;

import com.example.dataprizma.loginService.PermissionService;
import com.example.dataprizma.logindto.PermissionDto;
import com.example.dataprizma.loginmodel.Permission;
import com.example.dataprizma.loginrepository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller


@RequestMapping("/api/v1/permission")
public class PermissionController {
    @Autowired

    private PermissionService permissionService;

    @Autowired
    private PermissionRepository permissionRepository;

//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public ResponseEntity<Map<String, Map<String, List<PermissionDto>>>> listPermission() {
////        <!--- Create Map --->
//        Map<String, List<PermissionDto>> mapStaff = new LinkedHashMap<>();
//        Map<String, List<PermissionDto>> mapEmployee = new LinkedHashMap<>();
//        Map<String, List<PermissionDto>> mapDirectory = new LinkedHashMap<>();
//        Map<String, List<PermissionDto>> mapOther = new LinkedHashMap<>();
//        Map<String, List<PermissionDto>> mapLawyer = new LinkedHashMap<>();
//
//        List<Permission> listStaffList = permissionService.findAllByStaff();
//        List<Permission> listEmployeeList = permissionService.findAllByEmployee();
//        List<Permission> listDirectoryList = permissionService.findAllByDirectory();
//        List<Permission> lawyerList = permissionService.findAllByLawyer();
//        List<Permission> otherList = permissionService.findAllByOtherTypes();
////        StaffList
//        for (Permission permission : listStaffList) {
//            String key = permission.getType();
//            if (key == null || key.equals("")) {
//                key = "Undefined";
//            }
//            List<PermissionDto> dtos;
//            if (mapStaff.containsKey(key)) {
//                dtos = mapStaff.get(key);
//            } else {
//                dtos = new ArrayList<>();
//                mapStaff.put(key, dtos);
//            }
//            dtos.add(new PermissionDto(permission));
//        }
////        EmployeeList
//        for (Permission permission : listEmployeeList) {
//            String key = permission.getType();
//            if (key == null || key.equals("")) {
//                key = "Undefined";
//            }
//            List<PermissionDto> dtos;
//            if (mapEmployee.containsKey(key)) {
//                dtos = mapEmployee.get(key);
//            } else {
//                dtos = new ArrayList<>();
//                mapEmployee.put(key, dtos);
//            }
//            dtos.add(new PermissionDto(permission));
//        }
////        Directory
//        for (Permission permission : listDirectoryList) {
//            String key = permission.getType();
//            if (key == null || key.equals("")) {
//                key = "Undefined";
//            }
//            List<PermissionDto> dtos;
//            if (mapDirectory.containsKey(key)) {
//                dtos = mapDirectory.get(key);
//            } else {
//                dtos = new ArrayList<>();
//                mapDirectory.put(key, dtos);
//            }
//            dtos.add(new PermissionDto(permission));
//        }
//        // Lawyer
//        for (Permission permission : lawyerList) {
//            String key = permission.getType();
//            if (key == null || key.equals("")) {
//                key = "Undefined";
//            }
//            List<PermissionDto> dtos;
//            if (mapLawyer.containsKey(key)) {
//                dtos = mapLawyer.get(key);
//            } else {
//                dtos = new ArrayList<>();
//                mapLawyer.put(key, dtos);
//            }
//            dtos.add(new PermissionDto(permission));
//        }
////        OtherType
//        for (Permission permission : otherList) {
//            String key = permission.getType();
//            if (key == null || key.equals("")) {
//                key = "Undefined";
//            }
//            List<PermissionDto> dtos;
//            if (mapOther.containsKey(key)) {
//                dtos = mapOther.get(key);
//            } else {
//                dtos = new ArrayList<>();
//                mapOther.put(key, dtos);
//            }
//            dtos.add(new PermissionDto(permission));
//        }
//        Map<String, Map<String, List<PermissionDto>>> map = new HashMap<>(4);
//        map.put("User", mapStaff);
//        map.put("Manager", mapEmployee);
////        map.put("Directory", mapDirectory);
////        map.put("Other", mapOther);
////        map.put("Lawyer", mapLawyer);
//        return new ResponseEntity<>(map, HttpStatus.OK);
//    }

    @RequestMapping(value = "/list-by-roleId/{roleId}", method = RequestMethod.GET)
    public ResponseEntity<List<PermissionDto>> listPermissionByRoleId(@PathVariable("roleId") Long roleId) {
        List<Permission> permissionList = permissionService.findAllByRoleId(roleId);
        List<PermissionDto> permissionDto = new ArrayList<>(permissionList.size());
        permissionList.forEach(permission -> permissionDto.add(new PermissionDto(permission)));
        return new ResponseEntity<>(permissionDto, HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/list", method = RequestMethod.POST)
//    public ResponseEntity<Map<String, Object>> listPermissionByRoleId(@RequestBody() Pagination<Permission> pagination) {
//        return new ResponseEntity(permissionService.findAll(pagination), HttpStatus.OK);
//    }

    @PostMapping("/list/{name}")
    public ResponseEntity<Map<String, Object>> getAll(@PathVariable(value = "name")String username){
        List<Permission> permissionList = (List<Permission>) permissionRepository.findAll();
        List<PermissionDto> permissionDtoList = new ArrayList<>(permissionList.size());
        permissionList.forEach(permission -> permissionDtoList.add(new PermissionDto(permission)));

//        for (Permission permission : permissionList){
//            if(permission.getType().equals(username)){
//                return new ResponseEntity(permissionService.findAll(username), HttpStatus.OK);
//            }

//        }

        return new ResponseEntity(permissionService.findAll(username), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PermissionDto> permissionById(@PathVariable(name = "id") Long id) {
        Permission permission = permissionService.getByPermissionId(id);
        if (permission == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(new PermissionDto(permission), HttpStatus.OK);
    }

    @PostMapping("/class/{name}")
    public ResponseEntity<Map<String, ArrayList>> list(@PathVariable(value = "name")String username){
        return ResponseEntity.ok(permissionService.byClass(username));
    }

    @PostMapping("/create")
    public ResponseEntity<String> createPermission(@RequestBody PermissionDto permissionDto) {
        Permission permission = permissionDto.convertToPermission();
        permissionService.savePermission(permission);
        return ResponseEntity.ok("SUCCESSFULLY Saved...");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<String> updatePermission(@RequestBody PermissionDto permissionDto) {
//        if (permissionDto.getId() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        Permission permission = permissionService.getByPermissionId(permissionDto.getId());
//
//        if (permission == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        permissionDto.convertToPermission(permission);
//        permissionService.savePermission(permission);
        Permission permission = permissionRepository.findById(permissionDto.getId()).orElseThrow();
        permission.setABoolean(permissionDto.getABoolean());
        permissionRepository.save(permission);
        return ResponseEntity.ok("SUCCESSFULLY Updated...");
    }

    @RequestMapping(value = "/upgrade", method = RequestMethod.POST)
    public ResponseEntity<String> upgradePermission(@RequestBody PermissionDto permissionDto) {
        Permission permission = permissionRepository.findById(permissionDto.getId()).orElseThrow();
        permission.setABoolean(permissionDto.getABoolean());
        permission.setBl(permissionDto.getBl());
        permissionRepository.save(permission);
        return ResponseEntity.ok("SUCCESSFULLY Updated...");
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePermission(@PathVariable(name = "id") Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.ok("SUCCESSFUL Deleted...");
    }
}
