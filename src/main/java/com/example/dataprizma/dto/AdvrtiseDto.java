package com.example.dataprizma.dto;


import com.example.dataprizma.model.Advertise;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdvrtiseDto {

    private Long Id;
    private String file;
    private String topicEn;
    private String headerEn;
    private String primaryTextEn;
    private String topicRu;
    private String headerRu;
    private String primaryTextRu;
    private String topicUz;
    private String headerUz;
    private String primaryTextUz;
    private String paragraphEn;
    private String paragraphRu;
    private String paragraphUz;
    private String uploadPath;


    public AdvrtiseDto(Advertise advertise){

        if(advertise.getId() != null)
            setId(advertise.getId());
        setFile(advertise.getFile());

        setTopicEn(advertise.getTopicEn());
        setHeaderEn(advertise.getHeaderEn());
        setPrimaryTextEn(advertise.getPrimaryTextEn());
        setParagraphEn(advertise.getParagraphEn());

        setTopicRu(advertise.getTopicRu());
        setHeaderRu(advertise.getHeaderRu());
        setPrimaryTextRu(advertise.getPrimaryTextRu());
        setParagraphRu(advertise.getParagraphRu());

        setTopicUz(advertise.getTopicUz());
        setHeaderUz(advertise.getHeaderUz());
        setPrimaryTextUz(advertise.getPrimaryTextUz());
        setParagraphUz(advertise.getParagraphUz());
        setUploadPath(advertise.getUploadPath());
    }


}
