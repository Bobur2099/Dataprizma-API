package com.example.dataprizma.controller;


import com.example.dataprizma.dto.ComfortDto;
import com.example.dataprizma.model.Comfort;
import com.example.dataprizma.model.Company;
import com.example.dataprizma.repository.ComfortRepository;
import com.example.dataprizma.service.ComfortService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/api/v2/comfort")
public class ComfortController {

    @Autowired
    ComfortService comfortService;
    @Autowired
    ComfortRepository comfortRepository;

    @Autowired
    private ServletContext context;

    @GetMapping("/list")
    public ResponseEntity<List<ComfortDto>> list() {
        return ResponseEntity.ok(comfortService.comfortList());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createComfort(@RequestParam(required = false) String headerEn, String headerRu, String headerUz,
                                                String textEn, String textRu, String textUz,
                                                @RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(comfortService.save(multipartFile, headerEn, headerRu, headerUz,
                textEn, textRu, textUz));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ComfortDto> getByid(@PathVariable(value = "id") Long id) {
        Comfort comfort = comfortService.getComfortById(id);
        if (comfort.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(new ComfortDto(comfort));
    }

//    @GetMapping("/preview/ {id}")
//    public void previewFile(HttpServletResponse response, @PathVariable(value = "id") Long id) throws IOException {
//        Comfort comfort = comfortRepository.findById(id).orElse(null);
//        InputStream in = context.getResourceAsStream("/" + comfort.getUploadPath());
//        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
//        IOUtils.copy(in, response.getOutputStream());
//    }



//    @PutMapping("/update/{id}")
//    public ResponseEntity<String> updateById(@PathVariable(value = "id") Long Id){
//        Comfort comfort = comfortService.save();
//    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Comfort> update(@PathVariable(value = "id") Long id, String headerEn, String headerRu, String headerUz,
                                          String textEn,  String textRu, String textUz,
                                          @RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(comfortService.save(multipartFile, id,headerEn, headerRu, headerUz,
                textEn, textRu, textUz));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
        comfortService.deleteComfort(id);
        return ResponseEntity.ok("Deleted .....");
    }

}
