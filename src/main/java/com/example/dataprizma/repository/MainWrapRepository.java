package com.example.dataprizma.repository;

import com.example.dataprizma.model.MainWrap;
import com.example.dataprizma.model.portfolio.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MainWrapRepository extends CrudRepository<MainWrap, Long> {
    @Transactional(readOnly = true)
    @Query("select m from MainWrap m order by m.id ASC")
    Page<MainWrap> findAll(PageRequest pageRequest);

}
