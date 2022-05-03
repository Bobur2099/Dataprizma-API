package com.example.dataprizma.loginService;


import com.example.dataprizma.exeption.RecordNotFoundException;
import com.example.dataprizma.logindto.PermissionDto;
import com.example.dataprizma.loginmodel.Permission;
import com.example.dataprizma.loginrepository.PermissionRepository;
import com.example.dataprizma.loginrepository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    RoleRepository roleRepository;
    public List<Permission> findAllByStaff() {
        List<Permission> listStaffList = permissionRepository.findAllByTypeStartingWithOrderByName("Staff");
        if (listStaffList.size() > 0) {
            log.info("In findAllStaff - {} listStaffList found", listStaffList);
            return listStaffList;
        } else throw new RecordNotFoundException("Not Found StaffTypes in Permission");
    }

    public List<Permission> findAllByEmployee() {
        List<Permission> listEmployeeList = permissionRepository.findAllByTypeStartingWithOrderByName("User");
        if (listEmployeeList.size() > 0) {
            log.info("In findAllStaff - {} listUserList found", listEmployeeList);
            return listEmployeeList;
        } else throw new RecordNotFoundException("Not Found EmployeeTypes in Permission");
    }

    public List<Permission> findAllByDirectory() {
        List<Permission> listDirectoryList = permissionRepository.findAllByTypeStartingWithOrTypeStartingWithOrderByName("Directory", "Reference");
        if (listDirectoryList.size() > 0) {
            log.info("In findAllStaff - {} listDirectoryList found", listDirectoryList);
            return listDirectoryList;
        } else throw new RecordNotFoundException("Not Found DirectoryTypes in Permission");
    }

    public List<Permission> findAllByOtherTypes() {
        List<Permission> otherList =
                permissionRepository.findAllByTypeIsNotContainingAndTypeIsNotContainingAndTypeIsNotContainingAndTypeIsNotContainingOrderByName("Employee", "Directory", "Staff", "Lawyer");
        if (otherList.size() > 0) {
            log.info("In findAllStaff - {} OtherLists found", otherList);
            return otherList;
        } else throw new RecordNotFoundException("Not Found OtherLists in Permission");
    }

    public HashMap<String, ArrayList> byClass(String userName){
        ArrayList<PermissionDto> permissions = new ArrayList<>();
        HashSet<String> classOfPerm = new HashSet<String>();
        HashMap<String, ArrayList> permissionsMap = new HashMap<>();
        List<Permission> permissionList = (List<Permission>) permissionRepository.findAll();
        for (Permission permission : permissionList) {
            classOfPerm.add(permission.getBl());
        }

        for (String i:classOfPerm) {
            permissions.clear();
            for (Permission permission : permissionList) {
                if (i.equals(permission.getBl()) && permission.getType().equals(userName)) {
                    permissions.add(new PermissionDto(permission));
                }
            }
            ArrayList<PermissionDto> savedPermissions = new ArrayList<PermissionDto>(permissions);
            permissionsMap.put(i, savedPermissions);

        }
        return permissionsMap;
    }

    public Map<String, Object> findAll(String userName) {
//        Page<Permission> permissionPage = permissionRepository.findAll(PageRequest.of(pagination.getPage(), pagination.getLimit()));
        Map<String, Object> map = new HashMap<>();
//        List<Permission> permissionList = permissionPage.getContent();
//        List<PermissionDto> permissionDtoList = new ArrayList<>(permissionList.size());
//        for (Permission permission : permissionList){
//            permissionDtoList.add(new PermissionDto(permission));
//        }
//        Role role = roleRepository.findByName();
        List<Permission> permissionList = (List<Permission>) permissionRepository.findAll();
        List<PermissionDto> permissionDtoList = new ArrayList<>(permissionList.size());
        List<PermissionDto> permissionDtoList1 = new ArrayList<>(permissionList.size());
        for(Permission permission : permissionList){
            if (permission.getType().equals(userName)){
                permissionDtoList.add(new PermissionDto(permission));
                map.put("list", permissionDtoList);

            }

        }
//        permissionList.forEach(permission -> permissionDtoList.add(new PermissionDto(permission)));

//        map.put("list", permissionDtoList);
//        map.put("total", permissionPage.getTotalElements());
        return map;
    }

    public void savePermission(Permission permission) {
        permissionRepository.save(permission);
        log.info("IN save - permission: {} successfully saved", permission);
    }

    public Permission getByPermissionId(Long id) {
        Permission permission = permissionRepository.findById(id).orElse(null);
        if (permission == null) {
            log.warn("IN findById - no permission found by id: {}", id);
            return null;
        }

        log.info("IN findById - permission: {} found by id: {}", permission, id);
        return permission;
    }

    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
        log.info("IN delete - permission with id: {} successfully deleted", id);
    }

    public Permission getPermissionByName(String name) {
        return permissionRepository.findByName(name);
    }

    public List<Permission> findNotInRole(Long roleId) {
        return permissionRepository.findNotInRole(roleId);
    }

    public List<Permission> findAllByRoleId(Long roleId) {
        return permissionRepository.findAllByRolesId(roleId);
    }

    public List<Permission> getByPermissionIds(List<Long> permissionIds) {
        return permissionRepository.findAllByPermissionIds(permissionIds);
    }

    public List<Object> hasUserPermission(String username, String perm, String dxaYurDepartment) {
        return permissionRepository.hasUserPermission(username, perm, dxaYurDepartment);
    }

}

