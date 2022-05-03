package com.example.dataprizma.service;

import com.example.dataprizma.dto.AdvrtiseDto;
import com.example.dataprizma.dto.ReviewDto;
import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.Review;
import com.example.dataprizma.repository.AdvertiseRepository;
import com.example.dataprizma.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ServletContext context;
    private final UploadPathService uploadPathService;

    public List<ReviewDto> reviewList() {
        List<Review> reviewList = (List<Review>) reviewRepository.findAll();
        List<ReviewDto> reviewDtoList = new ArrayList<>(reviewList.size());
        reviewList.forEach(review -> reviewDtoList.add(new ReviewDto(review)));
        return reviewDtoList;

    }

    public File getFilePath(String modifiedFileName, String path) {

        boolean exists = new File(context.getRealPath("/" + path + "/")).exists();
        if (!exists) {
            new File(context.getRealPath("/" + path + "/")).mkdirs();
        }
        String modifiedFilePath = context.getRealPath("/" + path + "/" + File.separator + modifiedFileName);
        return new File(modifiedFilePath);
    }


    public String save(MultipartFile multipartFile, String topicEn, String topicRu, String topicUz,
                       String headerEn, String headerRu, String headerUz,
                       String paragraphEn, String paragraphRu, String paragraphUz) {
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {
            String fileName = multipartFile.getOriginalFilename();
            String currentMill = LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + fileName;
            File storeFile = getFilePath(currentMill, "imgs/review");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Review review = new Review();
            review.setFile(fileName);
            review.setExtension(FilenameUtils.getExtension(fileName));
            review.setUploadPath("imgs/review/" + currentMill);
            review.setTopicEn(topicEn);
            review.setHeaderEn(headerEn);
            review.setParagraphEn(paragraphEn);

            review.setTopicRu(topicRu);
            review.setHeaderRu(headerRu);
            review.setParagraphRu(paragraphRu);

            review.setTopicUz(topicUz);
            review.setHeaderUz(headerUz);
            review.setParagraphUz(paragraphUz);
//            if (storeFile != null)
//                advertise.setAbsolutePath(storeFile.getAbsolutePath());
            reviewRepository.save(review);
        }
        return "Successfully saved....";
    }

    public Review save(MultipartFile multipartFile, Long reviewId, String topicEn, String topicRu, String topicUz,
                       String headerEn, String headerRu, String headerUz,
                       String paragraphEn, String paragraphRu, String paragraphUz){
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {

            String fileName = multipartFile.getOriginalFilename();
            RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(64, 90).withinRange(97, 122).build();
            String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + pwdGenerator.generate(100) + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
//            File storeFile = uploadPathService.getFilePath(modifiedFileName, "images/employeePhoto");
            File storeFile = uploadPathService.getFilePath(modifiedFileName, "imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/review-photo");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Review review = reviewRepository.findById(reviewId).orElseThrow();
            String fileType = "review";
            review.setFile(fileName);
            review.setExtension(FilenameUtils.getExtension(fileName));
            review.setTopicEn(topicEn);
            review.setHeaderEn(headerEn);
            review.setParagraphEn(paragraphEn);

            review.setTopicRu(topicRu);
            review.setHeaderRu(headerRu);
            review.setParagraphRu(paragraphRu);

            review.setTopicUz(topicUz);
            review.setHeaderUz(headerUz);
            review.setParagraphUz(paragraphUz);
//             comfort.setm(modifiedFileName);
            review.setUploadPath("review/" + modifiedFileName);

            review.setUploadPath("imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/review-photo/" + modifiedFileName);
//            if (storeFile != null)
//                advertise.setAbsolutePath(storeFile.getAbsolutePath());
            if (reviewId != null)
                reviewRepository.findById(reviewId);
            reviewRepository.save(review);
            return review;
        } else return null;

    }




    public void saveReview(Review review) {
        reviewRepository.save(review);

    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public Review getReviewById(Long id) {
        Review review = reviewRepository.findById(id).orElse(null);
        if (review == null) {
            return null;
        } else {
            return review;
        }
    }
}
