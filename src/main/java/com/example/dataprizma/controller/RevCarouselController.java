package com.example.dataprizma.controller;


import com.example.dataprizma.dto.RevCarouselDto;
import com.example.dataprizma.dto.SerCarouselDto;
import com.example.dataprizma.model.RevCarousel;
import com.example.dataprizma.model.SerCarousel;
import com.example.dataprizma.repository.RevCarouselRepository;
import com.example.dataprizma.repository.SerCarouselRepository;
import com.example.dataprizma.service.RevCarouselService;
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
@RequestMapping("/api/v2/revCarousel")
public class RevCarouselController {


    @Autowired
    RevCarouselService revCarouselService;
    @Autowired
    RevCarouselRepository revCarouselRepository;

    @Autowired
    private ServletContext context;

    @GetMapping("/list")
    public ResponseEntity<List<RevCarouselDto>> list() {
        return ResponseEntity.ok(revCarouselService.revCarouselList());
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam(required = false) String textEn,String textRu, String textUz,
                                         String authorEn, String authorRu, String authorUz,
                                         String positionEn, String positionRu, String positionUz,
                                                    @RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(revCarouselService.save(multipartFile, textEn, textRu, textUz,
                authorEn, authorRu, authorUz,
                positionEn, positionRu, positionUz));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<RevCarouselDto> getByid(@PathVariable(value = "id") Long id) {
        RevCarousel revCarousel = revCarouselService.getRevCarouselByID(id);
        if (revCarousel.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(new RevCarouselDto(revCarousel));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<RevCarousel> update(@PathVariable(value = "id") Long id, String textEn,String textRu, String textUz,
                                              String authorEn, String authorRu, String authorUz,
                                              String positionEn, String positionRu, String positionUz,
                                              @RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(revCarouselService.save(multipartFile, id, textEn, textRu, textUz,
                authorEn, authorRu, authorUz,
                positionEn, positionRu, positionUz));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
        revCarouselService.deleteRevCarousel(id);
        return ResponseEntity.ok("Deleted .....");
    }

}
