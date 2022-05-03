package com.example.dataprizma.dto;


import com.example.dataprizma.model.SerCarousel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SerCarouselDto {

    private Long Id;
    private String file;
    private String headerEn;
    private String textEn;
    private String headerUz;
    private String textUz;
    private String headerRu;
    private String textRu;
    private String uploadPath;

    public SerCarouselDto(SerCarousel serCarousel){
        if(serCarousel.getId() != null)
            setId(serCarousel.getId());
        setFile(serCarousel.getFile());
        setHeaderEn(serCarousel.getHeaderEn());
        setTextEn(serCarousel.getTextEn());
        setHeaderRu(serCarousel.getHeaderRu());
        setTextRu(serCarousel.getTextRu());
        setHeaderUz(serCarousel.getHeaderUz());
        setTextUz(serCarousel.getTextUz());
        setUploadPath(serCarousel.getUploadPath());
    }

}
