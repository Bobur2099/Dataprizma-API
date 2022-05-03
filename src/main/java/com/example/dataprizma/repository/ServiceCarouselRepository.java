package com.example.dataprizma.repository;


import com.example.dataprizma.model.ServicesCarousel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceCarouselRepository extends CrudRepository<ServicesCarousel, Long> {

}
