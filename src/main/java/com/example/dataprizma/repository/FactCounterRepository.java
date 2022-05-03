//package com.example.dataprizma.repository;
//
//import com.example.dataprizma.model.CounterBlock;
//import com.example.dataprizma.model.FactCounter;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//
//@Repository
//public interface FactCounterRepository extends CrudRepository<FactCounter, Long> {
//    @Transactional(readOnly = true)
//    @Query("select p from FactCounter p order by p.id")
//    Page<FactCounter> findAll(PageRequest pageRequest);
//}
