package com.example.dataprizma.loginService;

import com.example.dataprizma.loginmodel.Pagination;
import com.example.dataprizma.loginmodel.Role;
import com.example.dataprizma.loginmodel.User;
import com.example.dataprizma.loginrepository.RoleRepository;
import com.example.dataprizma.loginrepository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.example.dataprizma.loginmodel.Status.ACTIVE;
import static com.example.dataprizma.loginmodel.Status.DELETED;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleUser);
        user.setStatus(ACTIVE);

        User registeredUser = userRepository.save(user);

        log.info("IN register - user: {} successfully registered", registeredUser);

        return registeredUser;
    }

    public Page<User> findAll(String name, com.example.dataprizma.loginmodel.Pagination pagination) {
        Pageable paging;
        if (pagination.getOrder() == null || pagination.getOrder().equals(""))
            paging = PageRequest.of(pagination.getPage(), pagination.getLimit(), Sort.by("id").descending());
        else {
            if (pagination.getType().toUpperCase().equals("ASC"))
                paging = PageRequest.of(pagination.getPage(), pagination.getLimit(), Sort.by(pagination.getOrder()).ascending());
            else
                paging = PageRequest.of(pagination.getPage(), pagination.getLimit(), Sort.by(pagination.getOrder()).descending());
        }
        Page<User> pagedResult;
        if (name != null && !name.equals(""))
            pagedResult = (Page<User>) userRepository.findAll(name.toLowerCase(Locale.ROOT), paging);
        else pagedResult = (Page<User>) userRepository.findAll(paging);
        if (pagedResult.hasContent()) {
            log.info("IN findAll - {} roles found", pagedResult.getTotalElements());
            return pagedResult;
        } else return null;
    }

    public User findByUsername(String username) {
        User result = userRepository.findByEmailAndStatus(username, ACTIVE);
        log.info("IN findByUsername - user: {} found by username: {}", result, username);
        return result;
    }

    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN findById - user: {} found by id: {}", result, id);
        return result;
    }

    public User saveUser(User user) {
        User result = findByUsername(user.getEmail());
        if (result == null) {
            userRepository.save(user);
            return user;
        } else return result;
    }

    public void delete(Long id) {
        User user = findById(id);
        user.setStatus(DELETED);
        saveUser(user);
        log.info("IN delete - user with id: {} successfully deleted", id);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public Page<User> findAllByDepartmentId(Long depId, Pagination pagination) {
        Pageable paging;
        if (pagination.getOrder() == null || pagination.getOrder().equals(""))
            paging = PageRequest.of(pagination.getPage(), pagination.getLimit(), Sort.by("id").descending());
        else {
            if (pagination.getType().toUpperCase().equals("ASC"))
                paging = PageRequest.of(pagination.getPage(), pagination.getLimit(), Sort.by(pagination.getOrder()).ascending());
            else
                paging = PageRequest.of(pagination.getPage(), pagination.getLimit(), Sort.by(pagination.getOrder()).descending());
        }
        Page<User> pagedResult = (Page<User>) userRepository.findAll(String.valueOf(depId), paging);
        if (pagedResult.hasContent()) {
            log.info("IN findAll - {} roles found", pagedResult.getTotalElements());
            return pagedResult;
        } else return null;
    }

}
