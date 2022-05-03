package com.example.dataprizma.dto;

import com.example.dataprizma.model.Company;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
public class CompanyDto {

    private  Long Id;
    private String logo;
    private String phone;
    private String addressEn;
    private String email;
    private String addressRu;
    private String addressUz;
    private String uploadPath;
    private String x;
    private String y;

    public CompanyDto(Company company){
        if(company.getId() != null)
            setId(company.getId());
        setLogo(company.getLogo());
        setPhone(company.getPhone());
        setAddressEn(company.getAddressEn());
        setEmail(company.getEmail());
        setAddressRu(company.getAddressRu());
        setAddressUz(company.getAddressUz());
        setUploadPath(company.getUploadPath());
        setX(company.getX());
        setY(company.getY());

    }
}
