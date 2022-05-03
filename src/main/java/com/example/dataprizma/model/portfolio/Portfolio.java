package com.example.dataprizma.model.portfolio;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Getter
@Setter
@Entity
@Table(name = "portfolio")
public class Portfolio {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column
    private String img;
    @Column
    private String headerUz;
    @Column
    private String textUz;
    @Column
    private String headerEn;
    @Column
    private String textEn;
    @Column
    private String headerRu;
    @Column
    private String textRu;

    @Column
    private String extension;
    @Column
    private String clientNameEn;
    @Column
    private String clientNameRu;
    @Column
    private String clientNameUz;
    @Column
    private String location;
    @Column
    private String startDate;
    @Column
    private String endDate;
    @Column
    private String budget;
    @Column
    private String uploadPath;
    private Integer pageSize;
    private Integer pageNo;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Portfolios{");
        sb.append("id=").append(Id);
        sb.append(", header='").append(headerUz).append('\'');
        sb.append(", text=").append(textUz);
        sb.append(", header='").append(headerEn).append('\'');
        sb.append(", text=").append(textEn);
        sb.append(", header='").append(headerRu).append('\'');
        sb.append(", text=").append(textRu);
        sb.append(", uploadPath=").append(uploadPath);
        sb.append('}');
        return sb.toString();
    }

}
