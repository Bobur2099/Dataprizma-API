package com.example.dataprizma.service;

import com.example.dataprizma.dto.ComfortDto;
import com.example.dataprizma.dto.MainWrapDto;
import com.example.dataprizma.model.Comfort;
import com.example.dataprizma.model.MainWrap;
import com.example.dataprizma.repository.MainWrapRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MainWrapService {

   private final MainWrapRepository mainWrapRepository;
    private final UploadPathService uploadPathService;
    private final Path root = Paths.get("uploads");
    private final ServletContext context;


    public List<MainWrapDto> mainWrapList() {
        List<MainWrap> mainWrapList = (List<MainWrap>) mainWrapRepository.findAll();
        List<MainWrapDto> mainWrapDtoList = new ArrayList<>(mainWrapList.size());
        mainWrapList.forEach(mainWrap -> mainWrapDtoList.add(new MainWrapDto(mainWrap)));
        return mainWrapDtoList;
    }

    public void saveMain(MainWrap mainWrap) {
        mainWrapRepository.save(mainWrap);

    }

    public void deleteMain(Long id) {
        mainWrapRepository.deleteById(id);
    }

    public MainWrap getMainById(Long id) {
        MainWrap mainWrap = mainWrapRepository.findById(id).orElse(null);
        if (mainWrap == null) {
            return null;

        } else {
            return mainWrap;
        }


    }



    public File getFilePath(String modifiedFileName, String path) {

        boolean exists = new File(context.getRealPath("/" + path + "/")).exists();
        if (!exists) {
            new File(context.getRealPath("/" + path + "/")).mkdirs();
        }
        String modifiedFilePath = context.getRealPath("/" + path + "/" + File.separator + modifiedFileName);
        return new File(modifiedFilePath);
    }


    public String save(MultipartFile multipartFile, String textEn, String textRu, String textUz) {
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {
            String fileName = multipartFile.getOriginalFilename();
            String currentMill = LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + fileName;
            File storeFile = getFilePath(currentMill, "imgs/mainWrap");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            MainWrap mainWrap = new MainWrap();
            mainWrap.setName(fileName);
            mainWrap.setExtension(FilenameUtils.getExtension(fileName));
            mainWrap.setUploadPath("imgs/mainWrap/" + currentMill);
            mainWrap.setTextEn(textEn);
            mainWrap.setTextRu(textRu);
            mainWrap.setTextUz(textUz);
            if (storeFile != null)
                mainWrap.setAbsolutePath(storeFile.getAbsolutePath());
            mainWrapRepository.save(mainWrap);
        }
        return "Successfully saved....";
    }

    public MainWrap save(MultipartFile multipartFile, Long mainWrapId,  String textEn, String textRu, String textUz){
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {

            String fileName = multipartFile.getOriginalFilename();
            RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(64, 90).withinRange(97, 122).build();
            String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + pwdGenerator.generate(100) + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
//            File storeFile = uploadPathService.getFilePath(modifiedFileName, "images/employeePhoto");
            File storeFile = uploadPathService.getFilePath(modifiedFileName, "imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/mainwrap-Photo");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            MainWrap mainWrap = mainWrapRepository.findById(mainWrapId).orElseThrow();
            String fileType = "mainWrap";
            mainWrap.setName(fileName);
            mainWrap.setExtension(FilenameUtils.getExtension(fileName));
            mainWrap.setTextEn(textEn);
            mainWrap.setTextRu(textRu);
            mainWrap.setTextUz(textUz);

            mainWrap.setUploadPath("imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/mainwrap-photo/" + modifiedFileName);
            //            if (storeFile != null)
//                mainWrap.setAbsolutePath(storeFile.getAbsolutePath());
            if (mainWrapId != null)
                mainWrapRepository.findById(mainWrapId);
            mainWrapRepository.save(mainWrap);
            return mainWrap;
        } else return null;
    }


}
