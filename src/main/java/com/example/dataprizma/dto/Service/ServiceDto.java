package com.example.dataprizma.dto.Service;

import com.example.dataprizma.model.service.Service;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class ServiceDto {

    private Long Id;
    private String file; //img
    private String uploadPath;

    private String primaryEn;
    private String textEn;
    private String headerEn;
    private String textFirstEn;
    private String textSecondEn;
    private String textThirdEn;
    private String sphereText1En;
    private String sphereText2En;
    private String sphereText3En;

    private String primaryRu;
    private String textRu;
    private String headerRu;
    private String textFirstRu;
    private String textSecondRu;
    private String textThirdRu;
    private String sphereText1Ru;
    private String sphereText2Ru;
    private String sphereText3Ru;

    private String primaryUz;
    private String textUz;
    private String headerUz;
    private String textFirstUz;
    private String textSecondUz;
    private String textThirdUz;
    private String sphereText1Uz;
    private String sphereText2Uz;
    private String sphereText3Uz;

    public ServiceDto(Service service){
        if(service.getId() != null)
            setId(service.getId());

        setFile(service.getFile());
        setUploadPath(service.getUploadPath());

        setPrimaryEn(service.getPrimaryEn());
        setTextEn(service.getTextEn());
        setHeaderEn(service.getHeaderEn());
        setTextFirstEn(service.getTextFirstEn());
        setTextSecondEn(service.getTextSecondEn());
        setTextThirdEn(service.getTextThirdEn());
        setSphereText1En(service.getSphereText1En());
        setSphereText2En(service.getSphereText2En());
        setSphereText3En(service.getSphereText3En());

        setPrimaryRu(service.getPrimaryRu());
        setPrimaryUz(service.getPrimaryUz());

        setTextRu(service.getTextRu());
        setHeaderRu(service.getHeaderRu());
        setTextFirstRu(service.getTextFirstRu());
        setTextSecondRu(service.getTextSecondRu());
        setTextThirdRu(service.getTextThirdRu());
        setSphereText1Ru(service.getSphereText1Ru());
        setSphereText2Ru(service.getSphereText2Ru());
        setSphereText3Ru(service.getSphereText3Ru());

        setTextUz(service.getTextUz());
        setHeaderUz(service.getHeaderUz());
        setTextFirstUz(service.getTextFirstUz());
        setTextSecondUz(service.getTextSecondUz());
        setTextThirdUz(service.getTextThirdUz());
        setSphereText1Uz(service.getSphereText1Uz());
        setSphereText2Uz(service.getSphereText2Uz());
        setSphereText3Uz(service.getSphereText3Uz());
    }
}
