package com.example.dataprizma.repository;

import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.Comfort;
import com.example.dataprizma.model.portfolio.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ComfortRepository extends CrudRepository<Comfort, Long> {
    @Transactional(readOnly = true)
    @Query("select p from Comfort p order by p.id")
    Page<Comfort> findAll(PageRequest pageRequest);
}
