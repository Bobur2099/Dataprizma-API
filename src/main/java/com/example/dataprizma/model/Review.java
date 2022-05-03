package com.example.dataprizma.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String topicEn;
    private String headerEn;
    private String paragraphEn;
    private String topicRu;
    private String headerRu;
    private String paragraphRu;
    private String topicUz;
    private String headerUz;
    private String paragraphUz;
    private String file;
    private String uploadPath;
    private String extension;

}
