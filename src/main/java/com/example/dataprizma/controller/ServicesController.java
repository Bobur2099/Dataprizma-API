package com.example.dataprizma.controller;


import com.example.dataprizma.dto.SerCarouselDto;
import com.example.dataprizma.dto.ServicesDto;
import com.example.dataprizma.model.SerCarousel;
import com.example.dataprizma.model.Services;
import com.example.dataprizma.repository.ServicesRepository;
import com.example.dataprizma.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/api/v2/services")
public class ServicesController {

    @Autowired
    ServicesService servicesService;
    @Autowired
    ServicesRepository servicesRepository;

    @GetMapping("/list")
    public ResponseEntity<List<ServicesDto>> list() {
        return ResponseEntity.ok(servicesService.servicesList());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSerCarousel(@RequestBody ServicesDto servicesDto) {
        Services services = new Services();
        services.setHeaderEn(servicesDto.getHeaderEn());
        services.setTopicEn(servicesDto.getTopicEn());

        services.setHeaderRu(servicesDto.getHeaderRu());
        services.setTopicRu(servicesDto.getTopicRu());

        services.setHeaderUz(servicesDto.getHeaderUz());
        services.setTopicUz(servicesDto.getTopicUz());
        servicesRepository.save(services);
        return ResponseEntity.ok("saved ....");
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ServicesDto> getById(@PathVariable(value = "id") Long id) {
        Services services = servicesService.getServicesById(id);
        if (services.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(new ServicesDto(services));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ServicesDto> update(@PathVariable(value = "id") Long id, @RequestBody ServicesDto servicesDto){

        Services services = servicesRepository.findById(id).orElseThrow();
        services.setHeaderEn(servicesDto.getHeaderEn());
        services.setTopicEn(servicesDto.getTopicEn());

        services.setHeaderRu(servicesDto.getHeaderRu());
        services.setTopicRu(servicesDto.getTopicRu());

        services.setHeaderUz(servicesDto.getHeaderUz());
        services.setTopicUz(servicesDto.getTopicUz());
        servicesRepository.save(services);
        return ResponseEntity.ok(new ServicesDto(services));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
        servicesService.deleteServices(id);
        return ResponseEntity.ok("Deleted .....");
    }
}
