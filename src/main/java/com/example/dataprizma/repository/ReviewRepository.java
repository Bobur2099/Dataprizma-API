package com.example.dataprizma.repository;

//import com.example.dataprizma.model.FactCounter;
import com.example.dataprizma.model.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
    @Transactional(readOnly = true)
    @Query("select p from Review p order by p.id")
    Page<Review> findAll(PageRequest pageRequest);
}
