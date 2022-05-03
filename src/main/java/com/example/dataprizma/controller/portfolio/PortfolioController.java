package com.example.dataprizma.controller.portfolio;


import com.example.dataprizma.dto.SerCarouselDto;
import com.example.dataprizma.dto.portfolio.PortfolioDto;
import com.example.dataprizma.model.Pagination;
import com.example.dataprizma.model.SerCarousel;
import com.example.dataprizma.model.portfolio.Portfolio;
import com.example.dataprizma.repository.SerCarouselRepository;
import com.example.dataprizma.repository.portfolio.PortfolioRepository;
import com.example.dataprizma.service.SerCarouselService;
import com.example.dataprizma.service.portfolio.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/api/v2/portfolio")
public class PortfolioController {

    @Autowired
    PortfolioService portfolioService;
    @Autowired
    PortfolioRepository portfolioRepository;

    @Autowired
    private ServletContext context;

    @PostMapping("/portfolios")
    public ResponseEntity<Map<String, Object>> getPaginatedCountries(@RequestBody Pagination<Portfolio> pagination) {
        return ResponseEntity.ok(portfolioService.findPaginated(pagination));
    }

    @GetMapping("/list")
    public ResponseEntity<List<PortfolioDto>> list() {
        return ResponseEntity.ok(portfolioService.List());
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestParam(required = false)String headerEn, String headerRu, String headerUz,
                                         String textEn, String textRu, String textUz,
                                         String clientNameEn, String clientNameRu, String clientNameUz
            , String location, String startDate, String endDate, String budget,
                                                    @RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(portfolioService.save(multipartFile, headerEn, headerRu, headerUz,
                textEn, textRu, textUz,
                clientNameEn, clientNameRu, clientNameUz, location, startDate, endDate, budget));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<PortfolioDto> getByid(@PathVariable(value = "id") Long id) {
        Portfolio portfolio = portfolioService.getById(id);
        if (portfolio.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(new PortfolioDto(portfolio));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Portfolio> update(@PathVariable(value = "id") Long id, String headerEn, String headerRu, String headerUz,
                                            String textEn, String textRu, String textUz,
                                            String clientNameEn, String clientNameRu, String clientNameUz,  String location, String startDate, String endDate, String budget,
                                            @RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(portfolioService.save(multipartFile, id, headerEn, headerRu, headerUz,
                textEn, textRu, textUz,
                clientNameEn, clientNameRu, clientNameUz, location, startDate, endDate, budget));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
        portfolioService.delete(id);
        return ResponseEntity.ok("Deleted .....");
    }

}
