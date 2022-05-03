package com.example.dataprizma.dto;

import com.example.dataprizma.model.MainWrap;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MainWrapDto {

    private Long Id;
    private String name;
    private String textEn;
    private String textUz;
    private String textRu;
    private String uploadPath;


    public MainWrapDto(MainWrap mainWrap){
        if(mainWrap.getId() != null)
            setId(mainWrap.getId());
        setName(mainWrap.getName());
        setTextEn(mainWrap.getTextEn());
        setTextRu(mainWrap.getTextRu());
        setTextUz(mainWrap.getTextUz());
        setUploadPath(mainWrap.getUploadPath());
    }
}
