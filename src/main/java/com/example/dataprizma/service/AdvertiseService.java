package com.example.dataprizma.service;

import com.example.dataprizma.dto.AdvrtiseDto;
import com.example.dataprizma.dto.ComfortDto;
import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.Comfort;
import com.example.dataprizma.repository.AdvertiseRepository;
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
@RequiredArgsConstructor
@Slf4j
public class AdvertiseService {

    private final AdvertiseRepository advertiseRepository;
    private final ServletContext context;
    private final UploadPathService uploadPathService;


    public List<AdvrtiseDto> advrtiseList() {
        List<Advertise> advertiseList = (List<Advertise>) advertiseRepository.findAll();
        List<AdvrtiseDto> advrtiseDtoList = new ArrayList<>(advertiseList.size());
        advertiseList.forEach(advertise -> advrtiseDtoList.add(new AdvrtiseDto(advertise)));
        return advrtiseDtoList;

    }

    public File getFilePath(String modifiedFileName, String path) {

        boolean exists = new File(context.getRealPath("/" + path + "/")).exists();
        if (!exists) {
            new File(context.getRealPath("/" + path + "/")).mkdirs();
        }
        String modifiedFilePath = context.getRealPath("/" + path + "/" + File.separator + modifiedFileName);
        return new File(modifiedFilePath);
    }


    public String save(MultipartFile multipartFile, String topicEn, String topicRu, String topicUz,
                       String headerEn, String headerRu, String headerUz,
                       String primaryEn, String primaryRu, String primaryUz,
                       String paragraphEn, String paragraphRu, String paragraphUz) {
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {
            String fileName = multipartFile.getOriginalFilename();
            String currentMill = LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + fileName;
            File storeFile = getFilePath(currentMill, "imgs/advertise");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Advertise advertise = new Advertise();
            advertise.setFile(fileName);
            advertise.setExtension(FilenameUtils.getExtension(fileName));
            advertise.setUploadPath("imgs/advertise/" + currentMill);
            advertise.setTopicEn(topicEn);
            advertise.setHeaderEn(headerEn);
            advertise.setPrimaryTextEn(primaryEn);
            advertise.setParagraphEn(paragraphEn);

            advertise.setTopicRu(topicRu);
            advertise.setHeaderRu(headerRu);
            advertise.setPrimaryTextRu(primaryRu);
            advertise.setParagraphRu(paragraphRu);

            advertise.setTopicUz(topicUz);
            advertise.setHeaderUz(headerUz);
            advertise.setPrimaryTextUz(primaryUz);
            advertise.setParagraphUz(paragraphUz);
//            if (storeFile != null)
//                advertise.setAbsolutePath(storeFile.getAbsolutePath());
            advertiseRepository.save(advertise);
        }
        return "Successfully saved....";
    }

    public Advertise save(MultipartFile multipartFile, Long comfortId, String topicEn, String topicRu, String topicUz,
                          String headerEn, String headerRu, String headerUz,
                          String primaryEn, String primaryRu, String primaryUz,
                          String paragraphEn, String paragraphRu, String paragraphUz){
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {

            String fileName = multipartFile.getOriginalFilename();
            RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(64, 90).withinRange(97, 122).build();
            String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + pwdGenerator.generate(100) + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
//            File storeFile = uploadPathService.getFilePath(modifiedFileName, "images/employeePhoto");
            File storeFile = uploadPathService.getFilePath(modifiedFileName, "imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/advertise-photo");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Advertise advertise = advertiseRepository.findById(comfortId).orElseThrow();
            String fileType = "advertise";
            advertise.setFile(fileName);
            advertise.setExtension(FilenameUtils.getExtension(fileName));
            advertise.setTopicEn(topicEn);
            advertise.setHeaderEn(headerEn);
            advertise.setPrimaryTextEn(primaryEn);
            advertise.setParagraphEn(paragraphEn);

            advertise.setTopicRu(topicRu);
            advertise.setHeaderRu(headerRu);
            advertise.setPrimaryTextRu(primaryRu);
            advertise.setParagraphRu(paragraphRu);

            advertise.setTopicUz(topicUz);
            advertise.setHeaderUz(headerUz);
            advertise.setPrimaryTextUz(primaryUz);
            advertise.setParagraphUz(paragraphUz);
//             comfort.setm(modifiedFileName);
            advertise.setUploadPath("imgs/advertise/" + modifiedFileName);

            advertise.setUploadPath("imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/advertise-photo/" + modifiedFileName);
//            if (storeFile != null)
//                advertise.setAbsolutePath(storeFile.getAbsolutePath());
            if (comfortId != null)
                advertiseRepository.findById(comfortId);
            advertiseRepository.save(advertise);
            return advertise;
        } else return null;

    }




    public void saveAdvertise(Advertise advertise) {
        advertiseRepository.save(advertise);

    }

    public void deleteAdvertise(Long id) {
        advertiseRepository.deleteById(id);
    }

    public Advertise getAdvertiseById(Long id) {
        Advertise advertise = advertiseRepository.findById(id).orElse(null);
        if (advertise == null) {
            return null;
        } else {
            return advertise;
        }
    }
}



