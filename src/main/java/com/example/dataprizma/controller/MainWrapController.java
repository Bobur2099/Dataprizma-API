package com.example.dataprizma.controller;


import com.example.dataprizma.dto.ComfortDto;
import com.example.dataprizma.dto.MainWrapDto;
import com.example.dataprizma.model.Comfort;
import com.example.dataprizma.model.MainWrap;
import com.example.dataprizma.repository.MainWrapRepository;
import com.example.dataprizma.service.MainWrapService;
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
@RequestMapping("/api/v2/main-wrap")
public class MainWrapController {

    @Autowired
    MainWrapRepository mainWrapRepository;
    @Autowired
    MainWrapService mainWrapService;
    @Autowired
    private ServletContext context;

    @GetMapping("/list")
    public ResponseEntity<List<MainWrapDto>> list() {
        return ResponseEntity.ok(mainWrapService.mainWrapList());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createMain(@RequestParam(required = false) String textEn, String textRu, String textUz,
                                                @RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(mainWrapService.save(multipartFile, textEn, textRu, textUz));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<MainWrapDto> getById(@PathVariable(value = "id") Long id) {
        MainWrap mainWrap = mainWrapService.getMainById(id);
        if (mainWrap.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(new MainWrapDto(mainWrap));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<MainWrap> update(@PathVariable(value = "id") Long id, String textEn, String textRu, String textUz,
                                           @RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(mainWrapService.save(multipartFile, id, textEn, textRu, textUz));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
        mainWrapService.deleteMain(id);
        return ResponseEntity.ok("Deleted .....");
    }



}
