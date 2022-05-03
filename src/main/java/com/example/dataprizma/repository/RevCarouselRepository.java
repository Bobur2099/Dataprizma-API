package com.example.dataprizma.repository;

//import com.example.dataprizma.model.FactCounter;
import com.example.dataprizma.model.RevCarousel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RevCarouselRepository extends CrudRepository<RevCarousel, Long> {
    @Transactional(readOnly = true)
    @Query("select p from RevCarousel p order by p.id")
    Page<RevCarousel> findAll(PageRequest pageRequest);
}
