//package com.example.dataprizma.repository;
//
//import com.example.dataprizma.model.CounterBlock;
//import com.example.dataprizma.model.portfolio.Portfolio;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
//import org.springframework.transaction.annotation.Transactional;
//
//public interface CounterRepposirtory extends CrudRepository<CounterBlock, Long> {
//    @Transactional(readOnly = true)
//    @Query("select p from CounterBlock p order by p.id")
//    Page<CounterBlock> findAll(PageRequest pageRequest);
//}
