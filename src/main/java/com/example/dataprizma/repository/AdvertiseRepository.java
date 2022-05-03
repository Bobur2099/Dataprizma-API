package com.example.dataprizma.repository;

import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.portfolio.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdvertiseRepository extends CrudRepository<Advertise, Long> {
    @Transactional(readOnly = true)
    @Query("select p from Advertise p order by p.id")
    Page<Advertise> findAll(PageRequest pageRequest);
}
