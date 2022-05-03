package com.example.dataprizma.controller.service;

import com.example.dataprizma.dto.About.AboutDto;
import com.example.dataprizma.dto.Service.ServiceDto;
import com.example.dataprizma.dto.ServicesDto;
import com.example.dataprizma.model.about.About;
import com.example.dataprizma.repository.About.AboutRepository;
import com.example.dataprizma.repository.Service.ServiceRepository;
import com.example.dataprizma.service.About.AboutService;
import com.example.dataprizma.service.service.ServiceService;
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
@RequestMapping("/api/v2/service")
public class Service {


    @Autowired
    ServletContext context;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ServiceService serviceService;

    @GetMapping("/list")
    public ResponseEntity<List<ServiceDto>> list() {
        return ResponseEntity.ok(serviceService.List());
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam(required = false) String headerEn, String headerRu, String headerUz,
                                         String primaryEn, String primaryRu, String primaryUz,
                                         String textEn, String textRu, String textUz,
                                         String text1En, String text1Ru, String text1Uz,
                                         String text2En, String  text2Ru, String text2Uz,
                                         String text3En, String text3Ru, String text3Uz,
                                         String sphereText1En, String sphereText1Ru, String sphereText1Uz,
                                         String sphereText2En, String sphereText2Ru, String sphereText2Uz,
                                         String sphereText3En, String sphereText3Ru, String sphereText3Uz,
                                         @RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(serviceService.save(multipartFile,
                headerEn, headerRu, headerUz,
                primaryEn, primaryRu, primaryUz,
                textEn, textRu, textUz,
                text1En, text1Ru, text1Uz,
                text2En, text2Ru, text2Uz,
                text3En, text3Ru, text3Uz,
                sphereText1En, sphereText1Ru, sphereText1Uz,
                sphereText2En, sphereText2Ru, sphereText2Uz,
                sphereText3En, sphereText3Ru, sphereText3Uz));
    }


    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ServiceDto> getById(@PathVariable(value = "id") Long id) {
        com.example.dataprizma.model.service.Service service = serviceService.getById(id);
        if (service.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(new ServiceDto(service));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<com.example.dataprizma.model.service.Service> update(@PathVariable(value = "id") Long id, String headerEn, String headerRu, String headerUz,
                                                                               String primaryEn, String primaryRu, String primaryUz,
                                                                               String textEn, String textRu, String textUz,
                                                                               String text1En, String text1Ru, String text1Uz,
                                                                               String text2En, String  text2Ru, String text2Uz,
                                                                               String text3En, String text3Ru, String text3Uz,
                                                                               String sphereText1En, String sphereText1Ru, String sphereText1Uz,
                                                                               String sphereText2En, String sphereText2Ru, String sphereText2Uz,
                                                                               String sphereText3En, String sphereText3Ru, String sphereText3Uz,
                                                                               @RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(serviceService.save(multipartFile, id,
                headerEn, headerRu, headerUz,
                primaryEn, primaryRu, primaryUz,
                textEn, textRu, textUz,
                text1En, text1Ru, text1Uz,
                text2En, text2Ru, text2Uz,
                text3En, text3Ru, text3Uz,
                sphereText1En, sphereText1Ru, sphereText1Uz,
                sphereText2En, sphereText2Ru, sphereText2Uz,
                sphereText3En, sphereText3Ru, sphereText3Uz));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
        serviceService.delete(id);
        return ResponseEntity.ok("Deleted .....");
    }


}
