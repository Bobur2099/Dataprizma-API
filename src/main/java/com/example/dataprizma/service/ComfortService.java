package com.example.dataprizma.service;

import com.example.dataprizma.dto.ComfortDto;
import com.example.dataprizma.model.Comfort;
import com.example.dataprizma.model.Company;
import com.example.dataprizma.repository.ComfortRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



@Service
@Slf4j
@RequiredArgsConstructor
public class ComfortService {

    private final ServletContext context;

    private final ComfortRepository comfortRepository;

    private final UploadPathService uploadPathService;

//    private final Path root = Paths.get("uploads");


    public List<ComfortDto> comfortList() {
        List<Comfort> listComfort = (List<Comfort>) comfortRepository.findAll();
        List<ComfortDto> comfortDtoList = new ArrayList<>(listComfort.size());
        listComfort.forEach(comfort -> comfortDtoList.add(new ComfortDto(comfort)));
        return comfortDtoList;

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
            File storeFile = getFilePath(currentMill, "imgs/comfort");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Comfort comfort = new Comfort();
            comfort.setIcon(fileName);
            comfort.setExtension(FilenameUtils.getExtension(fileName));
            comfort.setUploadPath("imgs/comfort/" + currentMill);
            comfort.setTextEn(textEn);
            comfort.setHeaderEn(headerEn);

            comfort.setTextRu(textRu);
            comfort.setHeaderRu(headerRu);

            comfort.setTextUz(textUz);
            comfort.setHeaderUz(headerUz);
            if (storeFile != null)
                comfort.setAbsolutePath(storeFile.getAbsolutePath());
            comfortRepository.save(comfort);
        }
        return "Successfully saved....";
    }

     public Comfort save(MultipartFile multipartFile, Long comfortId, String headerEn, String headerRu, String headerUz,
                         String textEn,  String textRu, String textUz){
         if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {

             String fileName = multipartFile.getOriginalFilename();
             RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(64, 90).withinRange(97, 122).build();
             String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + pwdGenerator.generate(100) + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
//            File storeFile = uploadPathService.getFilePath(modifiedFileName, "images/employeePhoto");
             File storeFile = uploadPathService.getFilePath(modifiedFileName, "imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/comfort-photo");
             if (storeFile != null) {
                 try {
                     FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
             Comfort comfort = comfortRepository.findById(comfortId).orElseThrow();
             String fileType = "Comfort";
             comfort.setIcon(fileName);
             comfort.setExtension(FilenameUtils.getExtension(fileName));
             comfort.setTextEn(textEn);
             comfort.setHeaderEn(headerEn);

             comfort.setTextRu(textRu);
             comfort.setHeaderRu(headerRu);

             comfort.setTextUz(textUz);
             comfort.setHeaderUz(headerUz);
//             comfort.setm(modifiedFileName);
            comfort.setUploadPath("imgs/comfort/" + modifiedFileName);

             comfort.setUploadPath("imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/comfort-photo/" + modifiedFileName);
             if (storeFile != null)
                 comfort.setAbsolutePath(storeFile.getAbsolutePath());
             if (comfortId != null)
                 comfortRepository.findById(comfortId);
             comfortRepository.save(comfort);
             return comfort;
         } else return null;

     }




    public void saveComfort(Comfort comfort) {
        comfortRepository.save(comfort);

    }

    public void deleteComfort(Long id) {
        comfortRepository.deleteById(id);
    }

    public Comfort getComfortById(Long id) {
        Comfort comfort = comfortRepository.findById(id).orElse(null);
        if (comfort == null) {
            return null;
        } else {
            return comfort;
        }
    }
}
