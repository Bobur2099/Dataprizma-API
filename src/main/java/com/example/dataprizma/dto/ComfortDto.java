package com.example.dataprizma.dto;

import com.example.dataprizma.model.Comfort;
import com.example.dataprizma.service.ComfortService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Setter
@Getter
@NoArgsConstructor
public class ComfortDto {

    private Long id;
    private String icon;
    private String textEn;
    private String headerEn;
    private String textRu;
    private String headerRu;
    private String textUz;
    private String headerUz;
    private String uploadPath;


    public ComfortDto(Comfort comfort) {
        if (comfort.getId() != null)
            setId(comfort.getId());
            setIcon(comfort.getIcon());
        setHeaderEn(comfort.getHeaderEn());
        setTextEn(comfort.getTextEn());

        setHeaderRu(comfort.getHeaderRu());
        setTextRu(comfort.getTextRu());

        setHeaderUz(comfort.getHeaderUz());
        setTextUz(comfort.getTextUz());

        setUploadPath(comfort.getUploadPath());
    }

//    public Comfort convertComfortDto() {
//        Comfort comfort = new Comfort();
//        return convertComfortDto(comfort);
//    }
//
//    public Comfort convertComfortDto(Comfort comfort) {
//        if (id != null)
//            comfort.setId(id);
//        comfort.setIcon(icon);
//        comfort.setText(text);
//        comfort.setHeader(header);
//        return comfort;
//    }
}
