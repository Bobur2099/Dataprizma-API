package com.example.dataprizma.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "RevCarousel")
public class RevCarousel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String file;
    private String textEn;
    private String authorEn;
    private String positionEn;
    private String textUz;
    private String authorUz;
    private String positionUz;
    private String textRu;
    private String authorRu;
    private String positionRu;
    private String uploadPath;
    private String extension;

}
