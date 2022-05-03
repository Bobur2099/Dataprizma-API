package com.example.dataprizma.service.service;

import com.example.dataprizma.dto.AdvrtiseDto;
import com.example.dataprizma.dto.Service.ServiceDto;
import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.repository.AdvertiseRepository;
import com.example.dataprizma.repository.Service.ServiceRepository;
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
public class ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServletContext context;
    private final UploadPathService uploadPathService;


    public List<ServiceDto> List() {
        List<com.example.dataprizma.model.service.Service> serviceList = (List<com.example.dataprizma.model.service.Service>) serviceRepository.findAll();
        List<ServiceDto> serviceDtos = new ArrayList<>(serviceList.size());
        serviceList.forEach(service -> serviceDtos.add(new ServiceDto(service)));
        return serviceDtos;

    }

    public File getFilePath(String modifiedFileName, String path) {

        boolean exists = new File(context.getRealPath("/" + path + "/")).exists();
        if (!exists) {
            new File(context.getRealPath("/" + path + "/")).mkdirs();
        }
        String modifiedFilePath = context.getRealPath("/" + path + "/" + File.separator + modifiedFileName);
        return new File(modifiedFilePath);
    }


    public String save(MultipartFile multipartFile,
                       String headerEn, String headerRu, String headerUz,
                       String primaryEn, String primaryRu, String primaryUz,
                       String textEn, String textRu, String textUz,
                       String text1En, String text1Ru, String text1Uz,
                       String text2En, String  text2Ru, String text2Uz,
                       String text3En, String text3Ru, String text3Uz,
                       String sphereText1En, String sphereText1Ru, String sphereText1Uz,
                       String sphereText2En, String sphereText2Ru, String sphereText2Uz,
                       String sphereText3En, String sphereText3Ru, String sphereText3Uz) {
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {
            String fileName = multipartFile.getOriginalFilename();
            String currentMill = LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + fileName;
            File storeFile = getFilePath(currentMill, "imgs/service/Service");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            com.example.dataprizma.model.service.Service service = new com.example.dataprizma.model.service.Service();
            service.setFile(fileName);
            service.setExtension(FilenameUtils.getExtension(fileName));
            service.setUploadPath("imgs/service/Service" + currentMill);


            service.setHeaderEn(headerEn);
            service.setPrimaryEn(primaryEn);
            service.setTextEn(textEn);
            service.setTextFirstEn(text1En);
            service.setTextSecondEn(text2En);
            service.setTextThirdEn(text3En);
            service.setSphereText1En(sphereText1En);
            service.setSphereText2En(sphereText2En);
            service.setSphereText3En(sphereText3En);

            service.setHeaderRu(headerRu);
            service.setPrimaryRu(primaryRu);
            service.setTextRu(textRu);
            service.setTextFirstRu(text1Ru);
            service.setTextSecondRu(text2Ru);
            service.setTextThirdRu(text3Ru);
            service.setSphereText1Ru(sphereText1Ru);
            service.setSphereText2Ru(sphereText2Ru);
            service.setSphereText3Ru(sphereText3Ru);

            service.setHeaderUz(headerUz);
            service.setPrimaryUz(primaryUz);
            service.setTextUz(textUz);
            service.setTextFirstUz(text1Uz);
            service.setTextSecondUz(text2Uz);
            service.setTextThirdUz(text3Uz);
            service.setSphereText1Uz(sphereText1Uz);
            service.setSphereText2Uz(sphereText2Uz);
            service.setSphereText3Uz(sphereText3Uz);
//            if (storeFile != null)
//                advertise.setAbsolutePath(storeFile.getAbsolutePath());
            serviceRepository.save(service);
        }
        return "Successfully saved....";
    }

    public com.example.dataprizma.model.service.Service save(MultipartFile multipartFile, Long serviceId,
                                                             String headerEn, String headerRu, String headerUz,
                                                             String primaryEn, String primaryRu, String primaryUz,
                                                             String textEn, String textRu, String textUz,
                                                             String text1En, String text1Ru, String text1Uz,
                                                             String text2En, String  text2Ru, String text2Uz,
                                                             String text3En, String text3Ru, String text3Uz,
                                                             String sphereText1En, String sphereText1Ru, String sphereText1Uz,
                                                             String sphereText2En, String sphereText2Ru, String sphereText2Uz,
                                                             String sphereText3En, String sphereText3Ru, String sphereText3Uz){
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {

            String fileName = multipartFile.getOriginalFilename();
            RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(64, 90).withinRange(97, 122).build();
            String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + pwdGenerator.generate(100) + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
//            File storeFile = uploadPathService.getFilePath(modifiedFileName, "images/employeePhoto");
            File storeFile = uploadPathService.getFilePath(modifiedFileName, "imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/service-photo");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            com.example.dataprizma.model.service.Service service = serviceRepository.findById(serviceId).orElseThrow();
            String fileType = "service";
            service.setFile(fileName);
            service.setExtension(FilenameUtils.getExtension(fileName));

            service.setHeaderEn(headerEn);
            service.setPrimaryEn(primaryEn);
            service.setTextEn(textEn);
            service.setTextFirstEn(text1En);
            service.setTextSecondEn(text2En);
            service.setTextThirdEn(text3En);
            service.setSphereText1En(sphereText1En);
            service.setSphereText2En(sphereText2En);
            service.setSphereText3En(sphereText3En);

            service.setHeaderRu(headerRu);
            service.setPrimaryRu(primaryRu);
            service.setTextRu(textRu);
            service.setTextFirstRu(text1Ru);
            service.setTextSecondRu(text2Ru);
            service.setTextThirdRu(text3Ru);
            service.setSphereText1Ru(sphereText1Ru);
            service.setSphereText2Ru(sphereText2Ru);
            service.setSphereText3Ru(sphereText3Ru);

            service.setHeaderUz(headerUz);
            service.setPrimaryUz(primaryUz);
            service.setTextUz(textUz);
            service.setTextFirstUz(text1Uz);
            service.setTextSecondUz(text2Uz);
            service.setTextThirdUz(text3Uz);
            service.setSphereText1Uz(sphereText1Uz);
            service.setSphereText2Uz(sphereText2Uz);
            service.setSphereText3Uz(sphereText3Uz);

            service.setUploadPath("imgs/service/Service/" + modifiedFileName);
            service.setUploadPath("imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth()+ "/service-photo/" + modifiedFileName);
//            if (storeFile != null)
//                advertise.setAbsolutePath(storeFile.getAbsolutePath());
            if (serviceId != null)
                serviceRepository.findById(serviceId);
            serviceRepository.save(service);
            return service;
        } else return null;

    }




    public void save(com.example.dataprizma.model.service.Service service) {
        serviceRepository.save(service);

    }

    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }

    public com.example.dataprizma.model.service.Service getById(Long id) {
        com.example.dataprizma.model.service.Service service = serviceRepository.findById(id).orElse(null);
        if (service == null) {
            return null;
        } else {
            return service;
        }
    }

}
