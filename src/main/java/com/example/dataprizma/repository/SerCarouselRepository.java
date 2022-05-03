package com.example.dataprizma.repository;

//import com.example.dataprizma.model.FactCounter;
import com.example.dataprizma.model.SerCarousel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SerCarouselRepository extends CrudRepository<SerCarousel, Long> {
    @Transactional(readOnly = true)
    @Query("select p from SerCarousel p order by p.id")
    Page<SerCarousel> findAll(PageRequest pageRequest);
}
