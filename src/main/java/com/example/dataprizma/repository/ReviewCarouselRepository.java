package com.example.dataprizma.repository;


import com.example.dataprizma.model.ReviewCarousel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewCarouselRepository extends CrudRepository<ReviewCarousel, Long> {
}
