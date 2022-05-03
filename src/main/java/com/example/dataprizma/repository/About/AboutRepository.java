package com.example.dataprizma.repository.About;


import com.example.dataprizma.model.about.About;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends CrudRepository<About, Long> {
}
