package com.example.dataprizma.dto.portfolio;


import com.example.dataprizma.model.portfolio.Portfolio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class PortfolioDto {

    private Long Id;
    private String img;
    private String headerEn;
    private String textEn;
    private String headerUz;
    private String textUz;

    private String headerRu;
    private String textRu;
    private String uploadPath;
    private Integer pageSize;
    private Integer pageNo;
    private String clientNameUz;
    private String clientNameRu;
    private String clientNameEn;
    private String location;
    private String startDate;
    private String endDate;
    private String budget;



    public PortfolioDto(Portfolio portfolio){
        if(portfolio.getId() != null)
            setId(portfolio.getId());
        setImg(portfolio.getImg());

        setHeaderEn(portfolio.getHeaderEn());
        setTextEn(portfolio.getTextEn());

        setHeaderRu(portfolio.getHeaderRu());
        setHeaderUz(portfolio.getHeaderUz());

        setTextRu(portfolio.getTextRu());
        setTextUz(portfolio.getTextUz());
        setUploadPath(portfolio.getUploadPath());
        setPageSize(portfolio.getPageSize());
        setPageNo(portfolio.getPageNo());

        setClientNameUz(portfolio.getClientNameUz());
        setClientNameRu(portfolio.getClientNameRu());
        setClientNameEn(portfolio.getClientNameEn());

        setLocation(portfolio.getLocation());
        setStartDate(portfolio.getStartDate());
        setEndDate(portfolio.getEndDate());
        setBudget(portfolio.getBudget());
    }
}
