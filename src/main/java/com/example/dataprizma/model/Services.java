package com.example.dataprizma.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "services")
public class Services {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String topicEn;
    private String headerEn;

    private String topicUz;
    private String headerUz;

    private String topicRu;
    private String headerRu;
}
