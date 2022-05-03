package com.example.dataprizma.dto;

import com.example.dataprizma.model.RevCarousel;
import com.example.dataprizma.model.ReviewCarousel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewCarouselDto {

    private Long Id;
    private Long reviewId;
    private Long carouselId;

    public ReviewCarouselDto(ReviewCarousel reviewCarousel){
            if(reviewCarousel.getId() != null)
                setId(reviewCarousel.getId());
            setReviewId(reviewCarousel.getReview().getId());
            setCarouselId(reviewCarousel.getRevCarousel().getId());
    }

}
