package com.example.dataprizma.controller;

//import com.example.dataprizma.dto.FactCounterDto;
//import com.example.dataprizma.dto.ReviewCarouselDto;
//import com.example.dataprizma.model.*;
//import com.example.dataprizma.repository.CounterRepposirtory;
//import com.example.dataprizma.repository.FactCounterRepository;
//import com.example.dataprizma.repository.FactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


//@CrossOrigin(origins = "http://localhost:8080")
//@RestController
//@RequestMapping("/api/v2/factCounter")
//public class FactCounterController {
//
//
//    @Autowired
//    FactRepository factRepository;
//    @Autowired
//    CounterRepposirtory counterRepposirtory;
//    @Autowired
//    FactCounterRepository factCounterRepository;
//
//    @GetMapping("/list")
//    public List<FactCounterDto> list() {
//        List<FactCounter> factCounters  = (List<FactCounter>) factCounterRepository.findAll();
//        List<FactCounterDto> factCounterDtoList = new ArrayList<>(factCounters.size());
//        factCounters.forEach(factCounter -> factCounterDtoList.add(new FactCounterDto(factCounter)));
//        return factCounterDtoList;
//    }
//
//    @PostMapping("/create")
//    public FactCounter create(@RequestBody FactCounterDto factCounterDto) {
//        FactCounter factCounter = new FactCounter();
//        factCounter.setId(factCounter.getId());
//        Facts facts = factRepository.findById(factCounterDto.getFactId()).orElseThrow();
//        factCounter.setFacts(facts);
//        CounterBlock counterBlock = counterRepposirtory.findById(factCounterDto.getCounterId()).orElseThrow();
//        factCounter.setCounterBlock(counterBlock);
//        return factCounterRepository.save(factCounter);
//    }
//
//    @GetMapping("/get-by-id/{id}")
//    public ResponseEntity<FactCounterDto> getById(@PathVariable(value = "id") Long id) {
//        FactCounter factCounter = factCounterRepository.findById(id).orElseThrow();
//        return ResponseEntity.ok().body(new FactCounterDto(factCounter));
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<FactCounterDto> update(@PathVariable(value = "id")Long id, @RequestBody FactCounterDto factCounterDto) {
//        FactCounter factCounter = factCounterRepository.findById(id).orElseThrow();
//        factCounter.setId(factCounter.getId());
//        Facts facts = factRepository.findById(factCounterDto.getCounterId()).orElseThrow();
//        factCounter.setFacts(facts);
//        CounterBlock counterBlock = counterRepposirtory.findById(factCounterDto.getCounterId()).orElseThrow();
//        factCounter.setCounterBlock(counterBlock);
//        return ResponseEntity.ok(new FactCounterDto(factCounter));
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> delete(@PathVariable(value = "id")Long id){
//        factCounterRepository.deleteById(id);
//        return ResponseEntity.ok("Deleted ....");
//    }

//}
