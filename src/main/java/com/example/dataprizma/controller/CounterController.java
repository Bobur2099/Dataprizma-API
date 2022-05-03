package com.example.dataprizma.controller;

//import com.example.dataprizma.dto.CounterDto;
//import com.example.dataprizma.dto.FactDto;
//import com.example.dataprizma.model.CounterBlock;
//import com.example.dataprizma.model.Facts;
//import com.example.dataprizma.repository.CounterRepposirtory;
//import com.example.dataprizma.repository.FactRepository;
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
//@RequestMapping("/api/v2/counter")
//public class CounterController {

//    @Autowired
//    CounterRepposirtory counterRepposirtory;
//
//    @GetMapping("/list")
//    public ResponseEntity<List<CounterDto>> list() {
//        List<CounterBlock> counterBlocks = (List<CounterBlock>) counterRepposirtory.findAll();
//        List<CounterDto> counterDtoList = new ArrayList<>(counterBlocks.size());
//        counterBlocks.forEach(counterBlock -> counterDtoList.add(new CounterDto(counterBlock)));
//        return ResponseEntity.ok(counterDtoList);
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<CounterDto> create(@RequestBody CounterDto counterDto) {
//        CounterBlock counterBlock = new CounterBlock();
//        counterBlock.setCounter(counterDto.getCounter());
//        counterBlock.setText(counterDto.getText());
//        counterRepposirtory.save(counterBlock);
//        return ResponseEntity.ok(new CounterDto(counterBlock));
//    }
//
//    @GetMapping("/get-by-id/{id}")
//    public ResponseEntity<CounterDto> getById(@PathVariable(value = "id") Long id) {
//        CounterBlock counterBlock = counterRepposirtory.findById(id).orElse(null);
//        if (counterBlock.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        return ResponseEntity.ok(new CounterDto(counterBlock));
//    }
//
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<CounterDto> update(@PathVariable(value = "id") Long id, @RequestBody CounterDto counterDto){
//
//        CounterBlock counterBlock = counterRepposirtory.findById(id).orElseThrow();
//        counterBlock.setCounter(counterDto.getCounter());
//        counterBlock.setText(counterDto.getText());
//        counterRepposirtory.save(counterBlock);
//        return ResponseEntity.ok(new CounterDto(counterBlock));
//
//    }
//
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
//        counterRepposirtory.deleteById(id);
//        return ResponseEntity.ok("Deleted .....");
//    }
//}
