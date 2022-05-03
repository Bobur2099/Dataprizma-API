package com.example.dataprizma.dto;


import com.example.dataprizma.model.RevCarousel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RevCarouselDto {
    private Long id;
    private String file;
    private String textEn;
    private String authorEn;
    private String positionEn;
    private String textRu;
    private String authorRu;
    private String positionRu;
    private String textUz;
    private String authorUz;
    private String positionUz;
   private String uploadPath;
    public RevCarouselDto(RevCarousel revCarousel){
        if(revCarousel.getId() != null)
            setId(revCarousel.getId());
        setFile(revCarousel.getFile());

        setTextEn(revCarousel.getTextEn());
        setAuthorEn(revCarousel.getAuthorEn());
        setPositionEn(revCarousel.getPositionEn());

        setTextRu(revCarousel.getTextRu());
        setAuthorRu(revCarousel.getAuthorRu());
        setPositionRu(revCarousel.getPositionRu());

        setTextUz(revCarousel.getTextUz());
        setAuthorUz(revCarousel.getAuthorUz());
        setPositionUz(revCarousel.getPositionUz());

        setUploadPath(revCarousel.getUploadPath());
    }
}
