package com.example.dataprizma;

import com.example.dataprizma.loginService.PermissionService;
import com.example.dataprizma.loginService.UserService;
import com.example.dataprizma.loginmodel.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.Set;

import static com.example.dataprizma.loginmodel.Status.ACTIVE;


@Slf4j
@Configuration
public class AnnotationInitialScan {
    @Autowired
    private PermissionService permissionService;
//    @Autowired
//    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
//    @Autowired
//    private DirectorySystemService directorySystemService;

//    @PostConstruct
//    public void postConstruct() {
//        this.startView();
//        this.startPerm();
//        this.startRole();
//        this.startUser();
//        this.startReasonForDismissal();
//    }
//
//    private void startReasonForDismissal() {
//        DirectorySystem directorySystemDB = directorySystemService.getDirectorySystemByCode(1);
//        if (directorySystemDB == null) {
//            DirectorySystem directorySystem = new DirectorySystem();
//            directorySystem.setNameLt("Oʼzbekiston Respublikasi Adliya vazirligi");
//            directorySystem.setNameUz("Ўзбекистон Республикаси Адлия вазирлиги");
//            directorySystem.setNameRu("Министерство юстиции Республики Узбекистан");
//            directorySystem.setCode(1);
//            directorySystemService.saveToDirectorySystem(directorySystem);
//        }
//    }

//    public void startView() {
//        log.info("---------------------------------------");
//        log.info("-------Scan for View annotations-------");
//        log.info("---------------------------------------");
//        Reflections reflections = new Reflections("uz.hr.controller.support", new MethodAnnotationsScanner());
//        Set<Method> methods = reflections.getMethodsAnnotatedWith(View.class);
//        methods.forEach(method -> {
//            View perm = method.getAnnotation(View.class);
//            for (String code : perm.value()) {
//                try {
//                    String methodType = method.getDeclaringClass().getSimpleName();
//                    String type = null;
//                    if (methodType.contains("Controller"))
//                        type = methodType.replace("Controller", "");
//                    if (type != null) {
//                        Permission permissionOpt = permissionService.getPermissionByName(code);
//                        if (permissionOpt == null) {
//                            Permission permission = new Permission();
//                            permission.setName(code);
//                            permission.setType(type);
//                            permissionService.savePermission(permission);
//                            log.info("Found a permission '" + code + "'");
//                        }
//                    }
//                } catch (Throwable e) {
//                    log.error(e.getMessage(), e);
//                }
//            }
//        });
//    }

//    public void startPerm() {
//        log.info("---------------------------------------");
//        log.info("----Scan for Permission annotations----");
//        log.info("---------------------------------------");
//        Reflections reflections = new Reflections("uz.hr.controller", new MethodAnnotationsScanner());
//        Set<Method> methods = reflections.getMethodsAnnotatedWith(PreAuthorize.class);
//        methods.forEach(method -> {
//            PreAuthorize perm = method.getAnnotation(PreAuthorize.class);
//            String name = perm.value();
//            if (name.contains("hasAuthority")) {
//                String code = name.replace("hasAuthority('", "").replace("')", "");
//                try {
//                    String methodType = method.getDeclaringClass().getSimpleName();
//                    String type = null;
//                    if (methodType.contains("Controller"))
//                        type = methodType.replace("Controller", "");
//                    if (type != null) {
//                        Permission permissionOpt = permissionService.getPermissionByName(code);
//                        if (permissionOpt == null) {
//                            Permission permission = new Permission();
//                            if (code.equals("View Reference")) {
//                                permission.setName(code);
//                                permission.setType("References");
//                            } else {
//                                permission.setName(code);
//                                permission.setType(type);
//                            }
//                            permissionService.savePermission(permission);
//                            log.info("Found a permission '" + code + "'");
//                        }
//                    }
//                } catch (Throwable e) {
//                    log.error(e.getMessage(), e);
//                }
//            }
//        });
//    }

//    public void startRole() {
//        Role role = roleService.findByCode("ROLE_ADMIN");
//        if (role == null) {
//            role = new Role();
//            role.setName("ADMIN");
//            role.setCode("ROLE_ADMIN");
//            roleService.createRole(role);
//        }
//        role.getPermissions().addAll(permissionService.findNotInRole(role.getId()));
//        roleService.updateRole(role);
//        Role roleU = roleService.findByCode("ROLE_USER");
//        if (roleU == null) {
//            roleU = new Role();
//            roleU.setName("USER");
//            roleU.setCode("ROLE_USER");
//            roleService.createRole(roleU);
//        }
//        Role roleE = roleService.findByCode("ROLE_EMPLOYEE");
//        if (roleE == null) {
//            roleE = new Role();
//            roleE.setName("EMPLOYEE");
//            roleE.setCode("ROLE_EMPLOYEE");
//            roleE.getPermissions().add(permissionService.getPermissionByName("Get Employee"));
//            roleE.getPermissions().add(permissionService.getPermissionByName("Update Employee"));
//            roleE.getPermissions().add(permissionService.getPermissionByName("List Employees"));
//            roleE.getPermissions().add(permissionService.getPermissionByName("List Users"));
//            roleService.createRole(roleE);
//        }
//        Role roleS = roleService.findByCode("ROLE_SUPER_CADR");
//        if (roleS == null) {
//            roleS = new Role();
//            roleS.setName("SUPER_CADR");
//            roleS.setCode("ROLE_SUPER_CADR");
//            roleService.createRole(roleS);
//        }
//        Role roleP = roleService.findByCode("ROLE_PROPERTY");
//        if (roleP == null) {
//            roleP = new Role();
//            roleP.setName("PROPERTY");
//            roleP.setCode("ROLE_PROPERTY");
//            roleService.createRole(roleP);
//        }
//        Role roleC = roleService.findByCode("ROLE_COMPETITION");
//        if (roleC == null) {
//            roleC = new Role();
//            roleC.setName("COMPETITION");
//            roleC.setCode("ROLE_COMPETITION");
//            roleService.createRole(roleC);
//        }
//        Role roleL = roleService.findByCode("ROLE_LAWYER");
//        if (roleL == null) {
//            roleL = new Role();
//            roleL.setName("LAWYER");
//            roleL.setCode("ROLE_LAWYER");
//            roleService.createRole(roleL);
//        }
//        Role roleML = roleService.findByCode("ROLE_MAIN_LAWYER");
//        if (roleML == null) {
//            roleML = new Role();
//            roleML.setName("MAIN_LAWYER");
//            roleML.setCode("ROLE_MAIN_LAWYER");
//            roleService.createRole(roleML);
//        }
//    }

    public void startUser() {
        User user = userService.findByUsername("admin@gmail.com");
        if (user == null) {
            user = new User();
            user.setEmail("admin");
            user.setStatus(ACTIVE);
            user.setPassword(passwordEncoder.encode("qwerty123"));
            user.setRole(user.getRole());
            userService.saveUser(user);
        }
//        User userU = userService.findByUsername("user");
//        if (userU == null) {
//            userU = new User();
//            userU.setEmail("user");
//            userU.setStatus(ACTIVE);
//            userU.setPassword(passwordEncoder.encode("user"));
////            userU.setRole(roleService.findByCode("ROLE_USER"));
//            userService.saveUser(userU);
//        }
//        User userP = userService.findByUsername("eMulk");
//        if (userP == null) {
//            userP = new User();
//            userP.setEmail("eMulk");
//            userP.setStatus(ACTIVE);
//            userP.setPassword(passwordEncoder.encode("e@Mulk$UZ"));
////            userP.setRole(roleService.findByCode("ROLE_PROPERTY"));
//            userService.saveUser(userP);
//        }
    }
}
