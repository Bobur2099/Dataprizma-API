package com.example.dataprizma.controller;


import com.example.dataprizma.dto.ServicesCarouselDto;
import com.example.dataprizma.model.SerCarousel;
import com.example.dataprizma.model.Services;
import com.example.dataprizma.model.ServicesCarousel;
import com.example.dataprizma.repository.SerCarouselRepository;
import com.example.dataprizma.repository.ServiceCarouselRepository;
import com.example.dataprizma.repository.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v2/serCar")
public class ServicesCarouselController {

//    @Autowired
//    ServiceCarouselRepository serviceCarouselRepository;
//
//    @Autowired
//    ServicesRepository servicesRepository;
//    @Autowired
//    SerCarouselRepository serCarouselRepository;
//
//    @GetMapping("/list")
//    public List<ServicesCarouselDto> list() {
//        List<ServicesCarousel> servicesCarouselList = (List<ServicesCarousel>) serviceCarouselRepository.findAll();
//        List<ServicesCarouselDto> servicesCarouselDtoList = new ArrayList<>(servicesCarouselList.size());
//        servicesCarouselList.forEach(servicesCarousel -> servicesCarouselDtoList.add(new ServicesCarouselDto(servicesCarousel)));
//        return servicesCarouselDtoList;
//    }
//
//    @PostMapping("/create")
//    public ServicesCarousel create(@RequestBody ServicesCarouselDto servicesCarouselDto) {
//        ServicesCarousel servicesCarousel = new ServicesCarousel();
//        servicesCarousel.setId(servicesCarouselDto.getId());
//        Services services = servicesRepository.findById(servicesCarouselDto.getServicesId()).orElseThrow();
//        servicesCarousel.setServices(services);
//        SerCarousel serCarousel = serCarouselRepository.findById(servicesCarouselDto.getCarouselId()).orElseThrow();
//        servicesCarousel.setSerCarousel(serCarousel);
//        return serviceCarouselRepository.save(servicesCarousel);
//    }
//
//    @GetMapping("/get-by-id/{id}")
//    public ResponseEntity<ServicesCarouselDto> getById(@PathVariable(value = "id") Long id) {
//        ServicesCarousel servicesCarousel = serviceCarouselRepository.findById(id).orElseThrow();
//        return ResponseEntity.ok().body(new ServicesCarouselDto(servicesCarousel));
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<ServicesCarouselDto> update(@PathVariable(value = "id")Long id, @RequestBody ServicesCarouselDto dto) {
//        ServicesCarousel servicesCarousel = serviceCarouselRepository.findById(id).orElseThrow();
//        Services services = servicesRepository.findById(dto.getServicesId()).orElseThrow();
//        servicesCarousel.setServices(services);
//        SerCarousel serCarousel = serCarouselRepository.findById(dto.getCarouselId()).orElseThrow();
//        servicesCarousel.setSerCarousel(serCarousel);
//        serviceCarouselRepository.save(servicesCarousel);
//        return ResponseEntity.ok(new ServicesCarouselDto(servicesCarousel));
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> delete(@PathVariable(value = "id")Long id){
//        serviceCarouselRepository.deleteById(id);
//        return ResponseEntity.ok("Deleted ....");
//    }
}
