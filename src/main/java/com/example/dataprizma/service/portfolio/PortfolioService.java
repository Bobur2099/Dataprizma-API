package com.example.dataprizma.service.portfolio;


import com.example.dataprizma.dto.SerCarouselDto;
import com.example.dataprizma.dto.portfolio.PortfolioDto;
import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.Pagination;
import com.example.dataprizma.model.SerCarousel;
import com.example.dataprizma.model.portfolio.Portfolio;
import com.example.dataprizma.repository.SerCarouselRepository;
import com.example.dataprizma.repository.portfolio.PortfolioRepository;
import com.example.dataprizma.service.UploadPathService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PortfolioService implements IPortfolioService{

    private final ServletContext context;

    private final PortfolioRepository portfolioRepository;

    private final UploadPathService uploadPathService;

    @Override
    public Map<String, Object> findPaginated(Pagination<Portfolio> pagination) {
//        Pageable pageable = PageRequest.of(pagination.getPage(), pagination.getLimit(), Sort.by("Id"));
//        Page<Portfolio> page = portfolioRepository.findAll(pageable);
        Page<Portfolio> page = portfolioRepository.findAll(PageRequest.of(pagination.getPage(), pagination.getLimit()));
//        List<Portfolio> portfolioList = (List<Portfolio>) portfolioRepository.findAll();
//        List<PortfolioDto> portfolioDtos = new ArrayList<>(portfolioList.size());
//        portfolioList.forEach(portfolio -> portfolioDtos.add(new PortfolioDto(portfolio)));
        Map<String, Object> map = new HashMap<>();
        map.put("list", page.getContent());
        map.put("total", page.getTotalElements());
//        System.out.println(page.getContent().get(0));
        return map;
    }


    public List<PortfolioDto> List() {
        List<Portfolio> portfolioList = (List<Portfolio>) portfolioRepository.findAll();
        List<PortfolioDto> portfolioDtos = new ArrayList<>(portfolioList.size());
        portfolioList.forEach(portfolio -> portfolioDtos.add(new PortfolioDto(portfolio)));
        return portfolioDtos;

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
                       String textEn, String textRu, String textUz,
                       String clientNameEn, String clientNameRu, String clientNameUz,
                       String location, String startDate, String endDate, String budget) {
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {
            String fileName = multipartFile.getOriginalFilename();
            String currentMill = LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + fileName;
            File storeFile = getFilePath(currentMill, "imgs/portfolio/port");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Portfolio portfolio = new Portfolio();
            portfolio.setImg(fileName);
            portfolio.setExtension(FilenameUtils.getExtension(fileName));
            portfolio.setUploadPath("imgs/portfolio/port/" + currentMill);
            portfolio.setTextEn(textEn);
            portfolio.setHeaderEn(headerEn);
            portfolio.setClientNameEn(clientNameEn);
            portfolio.setTextRu(textRu);
            portfolio.setHeaderRu(headerRu);
            portfolio.setClientNameRu(clientNameRu);
            portfolio.setTextUz(textUz);
            portfolio.setHeaderUz(headerUz);
            portfolio.setClientNameUz(clientNameUz);
            portfolio.setLocation(location);
            portfolio.setStartDate(startDate);
            portfolio.setEndDate(endDate);
            portfolio.setBudget(budget);
//            if (storeFile != null)
//                serCarousel.setAbsolutePath(storeFile.getAbsolutePath());
            portfolioRepository.save(portfolio);
        }
        return "Successfully saved....";
    }

    public Portfolio save(MultipartFile multipartFile, Long portId, String headerEn, String headerRu, String headerUz,
                          String textEn, String textRu, String textUz,
                          String clientNameEn, String clientNameRu, String clientNameUz,
                          String location, String startDate, String endDate, String budget){
        if (multipartFile != null && StringUtils.hasText(multipartFile.getOriginalFilename())) {

            String fileName = multipartFile.getOriginalFilename();
            RandomStringGenerator pwdGenerator = new RandomStringGenerator.Builder().withinRange(64, 90).withinRange(97, 122).build();
            String modifiedFileName = FilenameUtils.getBaseName(fileName) + "_" + pwdGenerator.generate(100) + System.currentTimeMillis() + "." + FilenameUtils.getExtension(fileName);
//            File storeFile = uploadPathService.getFilePath(modifiedFileName, "images/employeePhoto");
            File storeFile = uploadPathService.getFilePath(modifiedFileName, "imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/portfolio-photo");
            if (storeFile != null) {
                try {
                    FileUtils.writeByteArrayToFile(storeFile, multipartFile.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Portfolio portfolio =  portfolioRepository.findById(portId).orElseThrow();;
            String fileType = "portfolio";
            portfolio.setImg(fileName);
            portfolio.setExtension(FilenameUtils.getExtension(fileName));
            portfolio.setTextEn(textEn);
            portfolio.setHeaderEn(headerEn);
            portfolio.setClientNameEn(clientNameEn);
            portfolio.setTextRu(textRu);
            portfolio.setHeaderRu(headerRu);
            portfolio.setClientNameRu(clientNameRu);
            portfolio.setTextUz(textUz);
            portfolio.setHeaderUz(headerUz);
            portfolio.setClientNameUz(clientNameUz);
            portfolio.setUploadPath("imgs/portfolio/port" + modifiedFileName);
            portfolio.setLocation(location);
            portfolio.setStartDate(startDate);
            portfolio.setEndDate(endDate);
            portfolio.setBudget(budget);
            portfolio.setUploadPath("imgs/" + LocalDate.now().getYear() + "." + LocalDate.now().getMonthValue() + "." + LocalDate.now().getDayOfMonth() + "/portfolio-photo/" + modifiedFileName);
//            if (storeFile != null)
//                serCarousel.setAbsolutePath(storeFile.getAbsolutePath());
            if (portId != null)
                portfolioRepository.findById(portId);
            portfolioRepository.save(portfolio);
            return portfolio;
        } else return null;

    }




    public void save(Portfolio portfolio) {
        portfolioRepository.save(portfolio);

    }

    public void delete(Long id) {
        portfolioRepository.deleteById(id);
    }

    public Portfolio getById(Long id) {
        Portfolio portfolio = portfolioRepository.findById(id).orElse(null);
        if (portfolio == null) {
            return null;
        } else {
            return portfolio;
        }
    }
}
