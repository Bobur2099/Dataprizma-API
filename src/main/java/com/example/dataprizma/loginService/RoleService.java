package com.example.dataprizma.loginService;//package com.example.firstProject.Service;
//
//
//import com.example.firstProject.model.Pagination;
//import com.example.firstProject.model.Role;
//import com.example.firstProject.repository.RoleRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.*;
//import org.springframework.stereotype.Service;
//import org.springframework.validation.annotation.Validated;
//
//@Service
//@Slf4j
//@Validated
//public class RoleService {
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    public Page<Role> findAll(Pagination pagination) {
//
//        Pageable paging;
//        if (pagination.getOrder() == null || pagination.getOrder().equals(""))
//            paging = PageRequest.of(pagination.getPage(), pagination.getLimit(), Sort.by("id").descending());
//        else {
//            if (pagination.getType().toUpperCase().equals("ASC"))
//                paging = PageRequest.of(pagination.getPage(), pagination.getLimit(), Sort.by(pagination.getOrder()).ascending());
//            else
//                paging = PageRequest.of(pagination.getPage(), pagination.getLimit(), Sort.by(pagination.getOrder()).descending());
//        }
//        Page<Role> pagedResult;
//        if (pagination.getSearch() == null)
//            pagedResult = roleRepository.findAll(paging);
//        else {
//            Role role = new ObjectMapper().convertValue(pagination.getSearch(), Role.class);
//            ExampleMatcher matcher = ExampleMatcher.matchingAll().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
//            Example<Role> employeeExample = Example.of(role, matcher);
//            pagedResult = roleRepository.findAll(employeeExample, paging);
//        }
//        if (pagedResult.hasContent()) {
//            log.info("IN findAll - {} roles found", pagedResult.getTotalElements());
//            return pagedResult;
//        } else return null;
//    }
//
//    public Role getRoleById(Long id) {
//        Role role = roleRepository.findById(id).orElse(null);
//
//        if (role == null) {
//            log.warn("IN findById - no role found by id: {}", id);
//            return null;
//        }
//        log.info("IN findById - role: {} found by id: {}", role, id);
//        return role;
//    }
//
//    public void deleteRole(Long id) {
//        roleRepository.deleteById(id);
//        log.info("IN delete - role with id: {} successfully deleted", id);
//    }
//
//    public Role createRole(Role role) {
//        Role roleDB = findByCode(role.getCode());
//        if (roleDB == null) {
//            roleRepository.save(role);
//            return role;
//        } else {
//            log.info("IN create - user with username: {} exists", role.getCode());
//            return null;
//        }
//    }
//
//    public Role findByCode(String code) {
//        Role role = roleRepository.findByCode(code);
//        if (role == null) {
//            log.warn("IN findByCode - no role found by code: {}", code);
//            return null;
//        }
//        log.info("IN findByCode - role: {} found by code: {}", role, code);
//        return role;
//    }
//
//    public void updateRole(Role role) {
//        roleRepository.save(role);
//    }
//}
//
