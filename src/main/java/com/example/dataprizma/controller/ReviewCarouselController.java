package com.example.dataprizma.controller;


import com.example.dataprizma.dto.ReviewCarouselDto;
import com.example.dataprizma.dto.ServicesCarouselDto;
import com.example.dataprizma.model.*;
import com.example.dataprizma.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v2/reviewCarousel")
public class ReviewCarouselController {



    @Autowired
    ReviewCarouselRepository reviewCarouselRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    RevCarouselRepository revCarouselRepository;

    @GetMapping("/list")
    public List<ReviewCarouselDto> list() {
        List<ReviewCarousel> reviewCarouselList = (List<ReviewCarousel>) reviewCarouselRepository.findAll();
        List<ReviewCarouselDto> reviewCarouselDtoList = new ArrayList<>(reviewCarouselList.size());
        reviewCarouselList.forEach(reviewCarousel -> reviewCarouselDtoList.add(new ReviewCarouselDto(reviewCarousel)));
        return reviewCarouselDtoList;
    }

    @PostMapping("/create")
    public ReviewCarousel create(@RequestBody ReviewCarouselDto reviewCarouselDto) {
        ReviewCarousel reviewCarousel = new ReviewCarousel();
        reviewCarousel.setId(reviewCarouselDto.getId());
        Review review = reviewRepository.findById(reviewCarouselDto.getReviewId()).orElseThrow();
        reviewCarousel.setReview(review);
        RevCarousel revCarousel = revCarouselRepository.findById(reviewCarouselDto.getCarouselId()).orElseThrow();
        reviewCarousel.setRevCarousel(revCarousel);
        return reviewCarouselRepository.save(reviewCarousel);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ReviewCarouselDto> getById(@PathVariable(value = "id") Long id) {
        ReviewCarousel reviewCarousel = reviewCarouselRepository.findById(id).orElseThrow();
        return ResponseEntity.ok().body(new ReviewCarouselDto(reviewCarousel));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ReviewCarouselDto> update(@PathVariable(value = "id")Long id, @RequestBody ReviewCarouselDto dto) {
        ReviewCarousel reviewCarousel = reviewCarouselRepository.findById(id).orElseThrow();
        Review review = reviewRepository.findById(dto.getReviewId()).orElseThrow();
        reviewCarousel.setReview(review);
        RevCarousel revCarousel = revCarouselRepository.findById(dto.getCarouselId()).orElseThrow();
        reviewCarousel.setRevCarousel(revCarousel);
        reviewCarouselRepository.save(reviewCarousel);
        return ResponseEntity.ok(new ReviewCarouselDto(reviewCarousel));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable(value = "id")Long id){
        reviewCarouselRepository.deleteById(id);
        return ResponseEntity.ok("Deleted ....");
    }
}
