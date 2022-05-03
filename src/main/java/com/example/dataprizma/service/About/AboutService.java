package com.example.dataprizma.service.About;

import com.example.dataprizma.dto.About.AboutDto;
import com.example.dataprizma.dto.AdvrtiseDto;
import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.about.About;
import com.example.dataprizma.repository.About.AboutRepository;
import com.example.dataprizma.repository.AdvertiseRepository;
import com.example.dataprizma.service.UploadPathService;
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
public class AboutService {

    private final AboutRepository aboutRepository;
    private final ServletContext context;
    private final UploadPathService uploadPathService;


    public List<AboutDto> aboutList() {
        List<About> aboutList = (List<About>) aboutRepository.findAll();
        List<AboutDto> aboutDtoList = new ArrayList<>(aboutList.size());
        aboutList.forEach(about -> aboutDtoList.add(new AboutDto(about)));
        return aboutDtoList;

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
            File storeFile = getFilePath(currentMill, "images/about/main-about");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            About about = new About();
            about.setFile(fileName);
            about.setExtension(FilenameUtils.getExtension(fileName));
            about.setUploadPath("images/about/main-about" + currentMill);
            about.setParagraphEn(textEn);
            about.setParagraphRu(textRu);
            about.setParagraphUz(textUz);
//            if (storeFile != null)
//                advertise.setAbsolutePath(storeFile.getAbsolutePath());
            aboutRepository.save(about);
        }
        return "Successfully saved....";
    }

    public About save(MultipartFile multipartFile, Long aboutId, String textEn, String textRu, String textUz){
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {

            String fileName = multipartFile.getOriginalFilename();
            RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(64, 90).withinRange(97, 122).build();
            String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + pwdGenerator.generate(100) + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
//            File storeFile = uploadPathService.getFilePath(modifiedFileName, "images/employeePhoto");
            File storeFile = uploadPathService.getFilePath(modifiedFileName, "imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/about-photo");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            About about = aboutRepository.findById(aboutId).orElseThrow();
            String fileType = "about";
            about.setFile(fileName);
            about.setExtension(FilenameUtils.getExtension(fileName));
            about.setParagraphEn(textEn);
            about.setParagraphRu(textRu);
            about.setParagraphUz(textUz);
//             comfort.setm(modifiedFileName);
            about.setUploadPath("imgs/about/main-about" + modifiedFileName);

            about.setUploadPath("imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/about-Photo/" + modifiedFileName);
//            if (storeFile != null)
//                advertise.setAbsolutePath(storeFile.getAbsolutePath());
            if (aboutId != null)
                aboutRepository.findById(aboutId);
            aboutRepository.save(about);
            return about;
        } else return null;

    }




    public void saveAdvertise(About about) {
        aboutRepository.save(about);

    }

    public void delete(Long id) {
        aboutRepository.deleteById(id);
    }

    public About get(Long id) {
        About about = aboutRepository.findById(id).orElse(null);
        if (about == null) {
            return null;
        } else {
            return about;
        }
    }

}
