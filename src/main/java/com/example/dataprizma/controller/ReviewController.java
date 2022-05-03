package com.example.dataprizma.controller;

import com.example.dataprizma.dto.AdvrtiseDto;
import com.example.dataprizma.dto.ReviewDto;
import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.Review;
import com.example.dataprizma.repository.AdvertiseRepository;
import com.example.dataprizma.repository.ReviewRepository;
import com.example.dataprizma.service.AdvertiseService;
import com.example.dataprizma.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.util.List;


@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v2/review")
@RestController
public class ReviewController {

    @Autowired
    ServletContext context;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ReviewService reviewService;

    @GetMapping("/list")
    public ResponseEntity<List<ReviewDto>> list() {
        return ResponseEntity.ok(reviewService.reviewList());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAdvertise(@RequestParam(required = false) String topicEn, String topicRu, String topicUz,
                                                  String headerEn, String headerRu, String headerUz,
                                                  String paragraphEn, String paragraphRu, String paragraphUz,
                                                  @RequestParam("file") MultipartFile multipartFile) {
        return ResponseEntity.ok(reviewService.save(multipartFile, topicEn, topicRu, topicUz,
                headerEn, headerRu, headerUz,
                paragraphEn, paragraphRu, paragraphUz));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ReviewDto> getById(@PathVariable(value = "id") Long id) {
        Review review = reviewService.getReviewById(id);
        if (review.getId() == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(new ReviewDto(review));
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<Review> update(@PathVariable(value = "id") Long id, String topicEn, String topicRu, String topicUz,
                                         String headerEn, String headerRu, String headerUz,
                                         String paragraphEn, String paragraphRu, String paragraphUz,
                                            @RequestParam("file") MultipartFile multipartFile){
        return ResponseEntity.ok(reviewService.save(multipartFile, id, topicEn, topicRu, topicUz,
                headerEn, headerRu, headerUz,
                paragraphEn, paragraphRu, paragraphUz));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "id")Long id){
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Deleted .....");
    }


}
