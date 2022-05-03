package com.example.dataprizma.repository.Service;

import com.example.dataprizma.model.service.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, Long> {
}
