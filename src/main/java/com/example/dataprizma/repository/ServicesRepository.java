package com.example.dataprizma.repository;

//import com.example.dataprizma.model.FactCounter;
import com.example.dataprizma.model.Services;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ServicesRepository extends CrudRepository<Services, Long> {
    @Transactional(readOnly = true)
    @Query("select p from Services p order by p.id")
    Page<Services> findAll(PageRequest pageRequest);
}
