package com.example.dataprizma.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "serCarousel")
public class SerCarousel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "file_name")
    private String file;
    @Column
    private String headerEn;
    @Column
    private String textEn;
    @Column
    private String headerRu;
    @Column
    private String textRu;
    @Column
    private String headerUz;
    @Column
    private String textUz;
    @Column(name = "uploadPath")
    private String uploadPath;

//    @Column(name = "absolutePath")
//    private String absolutePath;

    @Column(name = "extension")
    private String extension;
}
