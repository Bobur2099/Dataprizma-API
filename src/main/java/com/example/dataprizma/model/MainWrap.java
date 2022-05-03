package com.example.dataprizma.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "MainWrap")
@CrossOrigin(origins = "http://localhost:8080")
public class MainWrap {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String textEn;

    @Column
    private String textRu;

    @Column
    private String name;

    @Column
    private String textUz;

    @Column(name = "uploadPath")
    private String uploadPath;

    @Column(name = "absolutePath", length = 10000)
    private String absolutePath;

    @Column(name = "extension")
    private String extension;
}
