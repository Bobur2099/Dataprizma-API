package com.example.dataprizma.controller;


import com.example.dataprizma.dto.AdvrtiseDto;
import com.example.dataprizma.dto.CompanyDto;
import com.example.dataprizma.exception.ExceptionHandlerValid;
import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.Company;
import com.example.dataprizma.repository.AdvertiseRepository;
import com.example.dataprizma.repository.CompanyRepository;
import com.example.dataprizma.service.AdvertiseService;
import com.example.dataprizma.service.CompanyService;
import org.hibernate.validator.internal.engine.messageinterpolation.parser.MessageDescriptorFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("api/v2/company")
public class CompanyController {

    @Autowired
    ServletContext context;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    CompanyService companyService;

    @GetMapping("/list")
    public ResponseEntity<List<CompanyDto>> list() {
        return ResponseEntity.ok(companyService.companyList());
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam(required = false) String phone, String addressEn, String addressRu, String addressUz,
                                         @Valid String email, String x, String y,
                                         @RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(companyService.save(multipartFile, phone, addressEn, addressRu, addressUz,
                email, x, y));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<CompanyDto> getById(@PathVariable(value = "id") Long id) {
        Company company = companyService.getById(id);
        if (company.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(new CompanyDto(company));
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Company> update(@PathVariable(value = "id") Long id, String phone, String addressEn, String addressRu, String addressUz,
                                          @Valid String email, String x, String y,
                                            @RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(companyService.save(multipartFile, id, phone, addressEn, addressRu, addressUz,
                email, x, y));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
        companyRepository.deleteById(id);
        return ResponseEntity.ok("Deleted .....");
    }
}
