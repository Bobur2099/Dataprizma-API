package com.example.dataprizma.loginrepository;

import com.example.dataprizma.loginmodel.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>, JpaRepository<Role, Long>, QueryByExampleExecutor<Role> {
    Role findByName(String name);

    Role findByCode(String code);
}

