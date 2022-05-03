package com.example.dataprizma.model.about;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "about_section")
public class About {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "name")
    private String file;

    @Column(name = "paragraphUz")
    private String paragraphUz;

    @Column(name = "paragraphEn")
    private String paragraphEn;

    @Column(name = "paragraphRu")
    private String paragraphRu;

    @Column(name = "uploadPath")
    private String uploadPath;

    @Column(name = "extension")
    private String extension;

}
