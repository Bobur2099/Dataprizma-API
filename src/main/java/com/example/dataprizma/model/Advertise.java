package com.example.dataprizma.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "advertise")
public class Advertise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "name")
    private String file;

    @Column
    private String topicEn;

    @Column
    private String headerEn;

    @Column
    private String primaryTextEn;

    @Column
    private String paragraphEn;

    @Column
    private String topicRu;

    @Column
    private String headerRu;

    @Column
    private String primaryTextRu;

    @Column
    private String paragraphRu;

    @Column
    private String topicUz;

    @Column
    private String headerUz;

    @Column
    private String primaryTextUz;

    @Column
    private String paragraphUz;

    @Column(name = "uploadPath")
    private String uploadPath;

//    @Column(name = "absolutePath")
//    private String absolutePath;

    @Column(name = "extension")
    private String extension;



}
