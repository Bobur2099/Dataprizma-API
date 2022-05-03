package com.example.dataprizma.service;


import com.example.dataprizma.dto.AdvrtiseDto;
import com.example.dataprizma.dto.RevCarouselDto;
import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.RevCarousel;
import com.example.dataprizma.repository.AdvertiseRepository;
import com.example.dataprizma.repository.RevCarouselRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RevCarouselService {

    private final RevCarouselRepository revCarouselRepository;
    private final ServletContext context;
    private final UploadPathService uploadPathService;

    public List<RevCarouselDto> revCarouselList() {
        List<RevCarousel> revCarouselList = (List<RevCarousel>) revCarouselRepository.findAll();
        List<RevCarouselDto> revCarouselDtoList = new ArrayList<>(revCarouselList.size());
        revCarouselList.forEach(revCarousel -> revCarouselDtoList.add(new RevCarouselDto(revCarousel)));
        return revCarouselDtoList;

    }

    public File getFilePath(String modifiedFileName, String path) {

        boolean exists = new File(context.getRealPath("/" + path + "/")).exists();
        if (!exists) {
            new File(context.getRealPath("/" + path + "/")).mkdirs();
        }
        String modifiedFilePath = context.getRealPath("/" + path + "/" + File.separator + modifiedFileName);
        return new File(modifiedFilePath);
    }


    public String save(MultipartFile multipartFile, String textEn,String textRu, String textUz,
                       String authorEn, String authorRu, String authorUz,
                       String positionEn, String positionRu, String positionUz) {
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {
            String fileName = multipartFile.getOriginalFilename();
            String currentMill = LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + fileName;
            File storeFile = getFilePath(currentMill, "imgs/revCarousel");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            RevCarousel revCarousel = new RevCarousel();
            revCarousel.setFile(fileName);
            revCarousel.setExtension(FilenameUtils.getExtension(fileName));
            revCarousel.setUploadPath("imgs/revCarousel/" + currentMill);
            revCarousel.setTextEn(textEn);
            revCarousel.setAuthorEn(authorEn);
            revCarousel.setPositionEn(positionEn);

            revCarousel.setTextRu(textRu);
            revCarousel.setAuthorRu(authorRu);
            revCarousel.setPositionRu(positionRu);

            revCarousel.setTextUz(textUz);
            revCarousel.setAuthorUz(authorUz);
            revCarousel.setPositionUz(positionUz);
//            if (storeFile != null)
//                advertise.setAbsolutePath(storeFile.getAbsolutePath());
            revCarouselRepository.save(revCarousel);
        }
        return "Successfully saved....";
    }

    public RevCarousel save(MultipartFile multipartFile, Long revCarouselId, String textEn,String textRu, String textUz,
                            String authorEn, String authorRu, String authorUz,
                            String positionEn, String positionRu, String positionUz){
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {

            String fileName = multipartFile.getOriginalFilename();
            RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(64, 90).withinRange(97, 122).build();
            String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + pwdGenerator.generate(100) + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
//            File storeFile = uploadPathService.getFilePath(modifiedFileName, "images/employeePhoto");
            File storeFile = uploadPathService.getFilePath(modifiedFileName, "imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/revCarousel-photo");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            RevCarousel revCarousel = revCarouselRepository.findById(revCarouselId).orElseThrow();
            String fileType = "revCarousel";
            revCarousel.setFile(fileName);
            revCarousel.setExtension(FilenameUtils.getExtension(fileName));
            revCarousel.setTextEn(textEn);
            revCarousel.setAuthorEn(authorEn);
            revCarousel.setPositionEn(positionEn);

            revCarousel.setTextRu(textRu);
            revCarousel.setAuthorRu(authorRu);
            revCarousel.setPositionRu(positionRu);

            revCarousel.setTextUz(textUz);
            revCarousel.setAuthorUz(authorUz);
            revCarousel.setPositionUz(positionUz);
//             comfort.setm(modifiedFileName);
            revCarousel.setUploadPath("imgs/revCarousel/" + modifiedFileName);

            revCarousel.setUploadPath("imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/revCarousel-photo/" + modifiedFileName);
//            if (storeFile != null)
//                advertise.setAbsolutePath(storeFile.getAbsolutePath());
            if (revCarouselId != null)
                revCarouselRepository.findById(revCarouselId);
            revCarouselRepository.save(revCarousel);
            return revCarousel;
        } else return null;

    }




    public void saveCarousel(RevCarousel revCarousel) {
        revCarouselRepository.save(revCarousel);

    }

    public void deleteRevCarousel(Long id) {
        revCarouselRepository.deleteById(id);
    }

    public RevCarousel getRevCarouselByID(Long id) {
        RevCarousel revCarousel = revCarouselRepository.findById(id).orElse(null);
        if (revCarousel == null) {
            return null;
        } else {
            return revCarousel;
        }
    }

}
