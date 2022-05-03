package com.example.dataprizma.controller;


import com.example.dataprizma.dto.ComfortDto;
import com.example.dataprizma.dto.SerCarouselDto;
import com.example.dataprizma.model.Comfort;
import com.example.dataprizma.model.SerCarousel;
import com.example.dataprizma.repository.ComfortRepository;
import com.example.dataprizma.repository.SerCarouselRepository;
import com.example.dataprizma.service.ComfortService;
import com.example.dataprizma.service.SerCarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/api/v2/serCarousel")
public class SerCarouselController {

    @Autowired
    SerCarouselService serCarouselService;
    @Autowired
    SerCarouselRepository serCarouselRepository;

    @Autowired
    private ServletContext context;

    @GetMapping("/list")
    public ResponseEntity<List<SerCarouselDto>> list() {
        return ResponseEntity.ok(serCarouselService.carouselList());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createSerCarousel(@RequestParam(required = false) String headerEn, String headerRu, String headerUz,
                                                    String textEn, String textRu, String textUz,
                                                @RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(serCarouselService.save(multipartFile, headerEn, headerRu, headerUz,
                textEn, textRu, textUz));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<SerCarouselDto> getByid(@PathVariable(value = "id") Long id) {
        SerCarousel serCarousel = serCarouselService.getSerCarouselId(id);
        if (serCarousel.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(new SerCarouselDto(serCarousel));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<SerCarousel> update(@PathVariable(value = "id") Long id, String headerEn, String headerRu, String headerUz,
                                              String textEn, String textRu, String textUz,
                                              @RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(serCarouselService.save(multipartFile, id, headerEn, headerRu, headerUz,
                textEn, textRu, textUz));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
        serCarouselService.deleteComfort(id);
        return ResponseEntity.ok("Deleted .....");
    }

}
