package com.example.dataprizma.dto;


import com.example.dataprizma.model.Services;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Provider;

@Getter
@Setter
@NoArgsConstructor
public class ServicesDto {

    private Long Id;
    private String topicEn;
    private String headerEn;
    private String topicUz;
    private String headerUz;
    private String topicRu;
    private String headerRu;

    public ServicesDto(Services services){
        if(services.getId() != null)
            setId(services.getId());
        setTopicEn(services.getTopicEn());
        setHeaderEn(services.getHeaderEn());
        setTopicRu(services.getTopicRu());
        setHeaderRu(services.getHeaderRu());
        setTopicUz(services.getTopicUz());
        setHeaderUz(services.getHeaderUz());
    }
}
