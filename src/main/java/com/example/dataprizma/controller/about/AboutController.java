package com.example.dataprizma.controller.about;

import com.example.dataprizma.dto.About.AboutDto;
import com.example.dataprizma.dto.AdvrtiseDto;
import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.about.About;
import com.example.dataprizma.repository.About.AboutRepository;
import com.example.dataprizma.repository.AdvertiseRepository;
import com.example.dataprizma.service.About.AboutService;
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
@RequestMapping("api/v2/about")

public class AboutController {

        @Autowired
        ServletContext context;

        @Autowired
        AboutRepository aboutRepository;

        @Autowired
        AboutService aboutService;

        @GetMapping("/list")
        public ResponseEntity<List<AboutDto>> list() {
            return ResponseEntity.ok(aboutService.aboutList());
        }


        @PostMapping("/create")
        public ResponseEntity<String> create(@RequestParam(required = false) String textEn, String textRu, String textUz,
                                                      @RequestParam("file") MultipartFile multipartFile) {
            return ResponseEntity.ok(aboutService.save(multipartFile,textEn, textRu, textUz));
        }


        @GetMapping("/get-by-id/{id}")
        public ResponseEntity<AboutDto> getById(@PathVariable(value = "id") Long id) {
            About about = aboutService.get(id);
            if (about.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return ResponseEntity.ok(new AboutDto(about ));
        }


        @PutMapping("/update/{id}")
        public ResponseEntity<About> update(@PathVariable(value = "id") Long id,  String textEn, String textRu, String textUz,
                                                @RequestParam("file") MultipartFile multipartFile){
            return ResponseEntity.ok(aboutService.save(multipartFile, id, textEn, textRu, textUz));
        }


        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
            aboutService.delete(id);
            return ResponseEntity.ok("Deleted .....");
        }


    }


