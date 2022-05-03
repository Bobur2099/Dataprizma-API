package com.example.dataprizma.loginrepository;

import com.example.dataprizma.loginmodel.Status;
import com.example.dataprizma.loginmodel.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>, PagingAndSortingRepository<User, Long> {
    @Transactional(readOnly = true)
    @Query(value = "select u from User u where u.email =?1")
    User findByEmail(String email);

    @Query(value = "select p from User p where p.password =?1")
    User findByToken(String token);

    User findByEmailAndStatus(String username, Status active);

    @Query("SELECT u FROM User u where u.email = ?1 ")
    Page<User> findAll(String name, Pageable paging);

}
