package com.example.dataprizma.controller;


import com.example.dataprizma.dto.AdvrtiseDto;
import com.example.dataprizma.dto.ComfortDto;
import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.Comfort;
import com.example.dataprizma.repository.AdvertiseRepository;
import com.example.dataprizma.service.AdvertiseService;
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
@RequestMapping("/api/v2/advertise")
public class  AdvertiseController {

    @Autowired
    ServletContext context;

    @Autowired
    AdvertiseRepository advertiseRepository;

    @Autowired
    AdvertiseService advertiseService;

    @GetMapping("/list")
    public ResponseEntity<List<AdvrtiseDto>> list() {
        return ResponseEntity.ok(advertiseService.advrtiseList());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAdvertise(@RequestParam(required = false) String topicEn, String topicRu, String topicUz,
                                                  String headerEn, String headerRu, String headerUz,
                                                  String primaryEn, String primaryRu, String primaryUz,
                                                  String paragraphEn, String paragraphRu, String paragraphUz,
                                                @RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(advertiseService.save(multipartFile, topicEn, topicRu, topicUz,
                headerEn, headerRu, headerUz,
                primaryEn, primaryRu, primaryUz,
                paragraphEn, paragraphRu, paragraphUz));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<AdvrtiseDto> getById(@PathVariable(value = "id") Long id) {
        Advertise advertise = advertiseService.getAdvertiseById(id);
        if (advertise.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(new AdvrtiseDto(advertise));
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Advertise> update(@PathVariable(value = "id") Long id, String topicEn, String topicRu, String topicUz,
                                            String headerEn, String headerRu, String headerUz,
                                            String primaryEn, String primaryRu, String primaryUz,
                                            String paragraphEn, String paragraphRu, String paragraphUz,
                                          @RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(advertiseService.save(multipartFile, id, topicEn, topicRu, topicUz,
                headerEn, headerRu, headerUz,
                primaryEn, primaryRu, primaryUz,
                paragraphEn, paragraphRu, paragraphUz));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
        advertiseService.deleteAdvertise(id);
        return ResponseEntity.ok("Deleted .....");
    }


}
