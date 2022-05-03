package com.example.dataprizma.service;


import com.example.dataprizma.dto.ComfortDto;
import com.example.dataprizma.dto.SerCarouselDto;
import com.example.dataprizma.model.Comfort;
import com.example.dataprizma.model.SerCarousel;
import com.example.dataprizma.repository.ComfortRepository;
import com.example.dataprizma.repository.SerCarouselRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
public class SerCarouselService {

    private final ServletContext context;

    private final SerCarouselRepository serCarouselRepository;

    private final UploadPathService uploadPathService;

//    private final Path root = Paths.get("uploads");


    public List<SerCarouselDto> carouselList() {
        List<SerCarousel> listCarousel = (List<SerCarousel>) serCarouselRepository.findAll();
        List<SerCarouselDto> serCarouselDtoList = new ArrayList<>(listCarousel.size());
        listCarousel.forEach(carousel -> serCarouselDtoList.add(new SerCarouselDto(carousel)));
        return serCarouselDtoList;

    }

    public File getFilePath(String modifiedFileName, String path) {

        boolean exists = new File(context.getRealPath("/" + path + "/")).exists();
        if (!exists) {
            new File(context.getRealPath("/" + path + "/")).mkdirs();
        }
        String modifiedFilePath = context.getRealPath("/" + path + "/" + File.separator + modifiedFileName);
        return new File(modifiedFilePath);
    }


    public String save(MultipartFile multipartFile, String headerEn, String headerRu, String headerUz,
                       String textEn, String textRu, String textUz) {
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {
            String fileName = multipartFile.getOriginalFilename();
            String currentMill = LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + fileName;
            File storeFile = getFilePath(currentMill, "imgs/serCarousel");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            SerCarousel serCarousel = new SerCarousel();
            serCarousel.setFile(fileName);
            serCarousel.setExtension(FilenameUtils.getExtension(fileName));
            serCarousel.setUploadPath("imgs/serCarousel/" + currentMill);
            serCarousel.setTextEn(textEn);
            serCarousel.setHeaderEn(headerEn);

            serCarousel.setTextRu(textRu);
            serCarousel.setHeaderRu(headerRu);

            serCarousel.setTextUz(textUz);
            serCarousel.setHeaderUz(headerUz);
//            if (storeFile != null)
//                serCarousel.setAbsolutePath(storeFile.getAbsolutePath());
            serCarouselRepository.save(serCarousel);
        }
        return "Successfully saved....";
    }

    public SerCarousel save(MultipartFile multipartFile, Long comfortId, String headerEn, String headerRu, String headerUz,
                            String textEn, String textRu, String textUz){
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {

            String fileName = multipartFile.getOriginalFilename();
            RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(64, 90).withinRange(97, 122).build();
            String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + pwdGenerator.generate(100) + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
//            File storeFile = uploadPathService.getFilePath(modifiedFileName, "images/employeePhoto");
            File storeFile = uploadPathService.getFilePath(modifiedFileName, "imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/serCarousel-photo");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            SerCarousel serCarousel = serCarouselRepository.findById(comfortId).orElseThrow();
            String fileType = "serCarousel";
            serCarousel.setFile(fileName);
            serCarousel.setExtension(FilenameUtils.getExtension(fileName));
            serCarousel.setTextEn(textEn);
            serCarousel.setHeaderEn(headerEn);

            serCarousel.setTextRu(textRu);
            serCarousel.setHeaderRu(headerRu);

            serCarousel.setTextUz(textUz);
            serCarousel.setHeaderUz(headerUz);
//             comfort.setm(modifiedFileName);
            serCarousel.setUploadPath("imgs/serCarousel/" + modifiedFileName);

            serCarousel.setUploadPath("imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth()+ "/serCarousel-photo/"  + modifiedFileName);
//            if (storeFile != null)
//                serCarousel.setAbsolutePath(storeFile.getAbsolutePath());
            if (comfortId != null)
                serCarouselRepository.findById(comfortId);
            serCarouselRepository.save(serCarousel);
            return serCarousel;
        } else return null;

    }




    public void saveCarousel(SerCarousel serCarousel) {
        serCarouselRepository.save(serCarousel);

    }

    public void deleteComfort(Long id) {
        serCarouselRepository.deleteById(id);
    }

    public SerCarousel getSerCarouselId(Long id) {
        SerCarousel serCarousel = serCarouselRepository.findById(id).orElse(null);
        if (serCarousel == null) {
            return null;
        } else {
            return serCarousel;
        }
    }
}
