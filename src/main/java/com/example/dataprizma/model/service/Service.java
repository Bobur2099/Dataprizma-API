package com.example.dataprizma.model.service;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Getter
@Setter

@Table(name = "service_section")
public class Service {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column
    private String primaryEn;

    @Column
    private String textEn;

    @Column
    private String headerEn;

    @Column
    private String textFirstEn;

    @Column
    private String textSecondEn;

    @Column
    private String textThirdEn;

    @Column
    private String sphereText1En;

    @Column
    private String sphereText2En;

    @Column
    private String sphereText3En;


    @Column
    private String primaryRu;

    @Column
    private String textRu;

    @Column
    private String headerRu;

    @Column
    private String textFirstRu;

    @Column
    private String textSecondRu;

    @Column
    private String textThirdRu;

    @Column
    private String sphereText1Ru;

    @Column
    private String sphereText2Ru;

    @Column
    private String sphereText3Ru;

    @Column
    private String primaryUz;

    @Column
    private String textUz;

    @Column
    private String headerUz;

    @Column
    private String textFirstUz;

    @Column
    private String textSecondUz;

    @Column
    private String textThirdUz;

    @Column
    private String sphereText1Uz;

    @Column
    private String sphereText2Uz;

    @Column
    private String sphereText3Uz;

    @Column(name = "img")
    private String file; //img

    @Column(name = "uploadPath")
    private String uploadPath;

    @Column(name = "extension")
    private String extension;
}
