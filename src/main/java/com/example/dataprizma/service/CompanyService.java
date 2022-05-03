package com.example.dataprizma.service;

import com.example.dataprizma.dto.AdvrtiseDto;
import com.example.dataprizma.dto.CompanyDto;
import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.Company;
import com.example.dataprizma.repository.AdvertiseRepository;
import com.example.dataprizma.repository.CompanyRepository;
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
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ServletContext context;
    private final UploadPathService uploadPathService;


    public List<CompanyDto> companyList() {
        List<Company> companyList = (List<Company>) companyRepository.findAll();
        List<CompanyDto> companyDtoList = new ArrayList<>(companyList.size());
        companyList.forEach(company -> companyDtoList.add(new CompanyDto(company)));
        return companyDtoList;

    }

    public File getFilePath(String modifiedFileName, String path) {

        boolean exists = new File(context.getRealPath("/" + path + "/")).exists();
        if (!exists) {
            new File(context.getRealPath("/" + path + "/")).mkdirs();
        }
        String modifiedFilePath = context.getRealPath("/" + path + "/" + File.separator + modifiedFileName);
        return new File(modifiedFilePath);
    }


    public String save(MultipartFile multipartFile, String phone, String addressEn, String addressRu, String addressUz,
                       String email, String x, String y) {
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {
            String fileName = multipartFile.getOriginalFilename();
            String currentMill = LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + fileName;
            File storeFile = getFilePath(currentMill, "imgs/company");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Company company = new Company();
            company.setLogo(fileName);
            company.setExtension(FilenameUtils.getExtension(fileName));
            company.setUploadPath("imgs/advertise/" + currentMill);
            company.setEmail(email);
            company.setAddressEn(addressEn);

            company.setAddressRu(addressRu);

            company.setAddressUz(addressUz);
            company.setPhone(phone);
            company.setX(x);
            company.setY(y);
//            if (storeFile != null)
//                advertise.setAbsolutePath(storeFile.getAbsolutePath());
            companyRepository.save(company);
        }
        return "Successfully saved....";
    }

    public Company save(MultipartFile multipartFile, Long companyId, String phone,
                        String addressEn, String addressRu, String addressUz,
                        String email, String x, String y){
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {

            String fileName = multipartFile.getOriginalFilename();
            RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(64, 90).withinRange(97, 122).build();
            String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + pwdGenerator.generate(100) + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
//            File storeFile = uploadPathService.getFilePath(modifiedFileName, "images/employeePhoto");
            File storeFile = uploadPathService.getFilePath(modifiedFileName, "imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/company-photo");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Company company = companyRepository.findById(companyId).orElseThrow();
            String fileType = "company";
            company.setLogo(fileName);
            company.setExtension(FilenameUtils.getExtension(fileName));
            company.setEmail(email);

            company.setAddressEn(addressEn);

            company.setAddressRu(addressRu);

            company.setAddressUz(addressUz);
            company.setPhone(phone);
            company.setUploadPath("imgs/company/" + modifiedFileName);

            company.setUploadPath("imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/company-photo/" + modifiedFileName);
//            if (storeFile != null)
//                advertise.setAbsolutePath(storeFile.getAbsolutePath());
            company.setY(y);
            company.setX(x);
            if (companyId != null)
                companyRepository.findById(companyId);
            companyRepository.save(company);
            return company;
        } else return null;

    }




    public void saveCompany(Company company) {
        companyRepository.save(company);

    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }

    public Company getById(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company == null) {
            return null;
        } else {
            return company;
        }
    }

}
