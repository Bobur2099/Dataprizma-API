package com.example.dataprizma.repository.portfolio;

import com.example.dataprizma.model.portfolio.Portfolio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PortfolioRepository extends PagingAndSortingRepository<Portfolio, Long> {
//    @Transactional(readOnly = true)
//    @Query("select p from Portfolio p where lower(p.text) like %?1% order by p.text")
//    Page<Portfolio> findAllBySearch(PageRequest of);
//        Page<Portfolio> findAll(Pageable pageable);
//        Iterable<Portfolio> findAll(Sort sort);

    @Transactional(readOnly = true)
    @Query("select p from Portfolio p order by p.id")
    Page<Portfolio> findAll(PageRequest pageRequest);
}
