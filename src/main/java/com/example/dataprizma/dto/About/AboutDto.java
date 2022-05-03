package com.example.dataprizma.dto.About;


import com.example.dataprizma.model.about.About;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class AboutDto {

    private Long Id;
    private String file;
    private String paragraphEn;
    private String uploadPath;
    private String paragraphUz;
    private String paragraphRu;

    public AboutDto(About about){
        if(about.getId() != null)
            setId(about.getId());
        setFile(about.getFile());
        setParagraphEn(about.getParagraphEn());
        setUploadPath(about.getUploadPath());
        setParagraphRu(about.getParagraphRu());
        setParagraphUz(about.getParagraphUz());
    }
}
