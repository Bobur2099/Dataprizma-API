package com.example.dataprizma.loginrepository;

import com.example.dataprizma.loginmodel.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Long> {
    Permission findByName(String name);

    @Query(value = "SELECT p FROM Permission p WHERE p.id NOT IN (SELECT p.id FROM Permission p LEFT JOIN p.roles r WHERE r.id = ?1)")
    List<Permission> findNotInRole(Long roleId);

    List<Permission> findAllByRolesId(Long roleId);

    List<Permission> findAllByTypeStartingWithOrderByName(String type);

    List<Permission> findAllByTypeStartingWithOrTypeStartingWithOrderByName(String type, String type1);

    List<Permission> findAllByTypeIsNotContainingAndTypeIsNotContainingAndTypeIsNotContainingAndTypeIsNotContainingOrderByName(String eType, String dType, String sType, String lType);

    @Query(value = "SELECT p FROM Permission p WHERE p.id IN (?1)")
    List<Permission> findAllByPermissionIds(List<Long> permissionIds);

    @Query(value = "SELECT rp.* FROM role_permissions rp LEFT JOIN users u ON rp.role_id = u.role_id  LEFT JOIN permissions p ON p.id = rp.permission_id WHERE u.username = ?1 AND (p.name = ?2 or p.name = ?3)", nativeQuery = true)
    List<Object> hasUserPermission(String username, String perm, String dxaYurDepartment);

    @Query("select p from Permission p where p.name like ?1% ")
    List<Permission> findAllByLawyer(String lawyer);
}