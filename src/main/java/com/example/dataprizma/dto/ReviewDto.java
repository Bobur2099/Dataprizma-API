package com.example.dataprizma.dto;


import com.example.dataprizma.model.Review;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewDto {

    private Long id;
    private String topicEn;
    private String headerEn;
    private String paragraphEn;
    private String topicRu;
    private String headerRu;
    private String paragraphRu;
    private String topicUz;
    private String headerUz;
    private String paragraphUz;
    private String file;
    private String uploadPath;

    public ReviewDto(Review review){
        if(review.getId() != null)
            setId(review.getId());
        setTopicEn(review.getTopicEn());
        setHeaderEn(review.getHeaderEn());
        setParagraphEn(review.getParagraphEn());
        setTopicRu(review.getTopicRu());
        setHeaderRu(review.getHeaderRu());
        setParagraphRu(review.getParagraphRu());
        setTopicUz(review.getTopicUz());
        setHeaderUz(review.getHeaderUz());
        setParagraphUz(review.getParagraphUz());
        setFile(review.getFile());
        setUploadPath(review.getUploadPath());
    }

}
