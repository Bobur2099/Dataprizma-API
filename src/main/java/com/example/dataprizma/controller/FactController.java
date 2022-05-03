package com.example.dataprizma.controller;

//import com.example.dataprizma.dto.FactDto;
//import com.example.dataprizma.dto.ServicesDto;
//import com.example.dataprizma.model.Facts;
//import com.example.dataprizma.model.Services;
//import com.example.dataprizma.repository.FactRepository;
//import com.example.dataprizma.repository.ServicesRepository;
//import com.example.dataprizma.service.ServicesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@CrossOrigin(origins = "http://localhost:8080")
//@RestController
//@RequestMapping("/api/v2/fact")
//public class FactController {
//
//    @Autowired
//    FactRepository factRepository;
//
//    @GetMapping("/list")
//    public ResponseEntity<List<FactDto>> list() {
//        List<Facts> factsList = (List<Facts>) factRepository.findAll();
//        List<FactDto> factDtos = new ArrayList<>(factsList.size());
//        factsList.forEach(facts -> factDtos.add(new FactDto(facts)));
//        return ResponseEntity.ok(factDtos);
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<FactDto> create(@RequestBody FactDto factDto) {
//        Facts facts = new Facts();
//        facts.setHeader(factDto.getHeader());
//        factRepository.save(facts);
//        return ResponseEntity.ok(new FactDto(facts));
//    }
//
//    @GetMapping("/get-by-id/{id}")
//    public ResponseEntity<FactDto> getById(@PathVariable(value = "id") Long id) {
//        Facts facts = factRepository.findById(id).orElse(null);
//        if (facts.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        return ResponseEntity.ok(new FactDto(facts));
//    }
//
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<FactDto> update(@PathVariable(value = "id") Long id, @RequestBody FactDto factDto){
//
//        Facts facts = factRepository.findById(id).orElseThrow();
//        facts.setHeader(factDto.getHeader());
//        factRepository.save(facts);
//        return ResponseEntity.ok(new FactDto(facts));
//
//    }
//
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
//        factRepository.deleteById(id);
//        return ResponseEntity.ok("Deleted .....");
//    }
//}
