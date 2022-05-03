package com.example.dataprizma.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "comfort")
public class Comfort {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "icon")
    private String icon;
    @Column
    private String textEn;
    @Column
    private String headerEn;
    @Column
    private String textRu;
    @Column
    private String headerRu;
    @Column
    private String textUz;
    @Column
    private String headerUz;
    private String uploadPath;
    private String absolutePath;
    private String extension;

}
