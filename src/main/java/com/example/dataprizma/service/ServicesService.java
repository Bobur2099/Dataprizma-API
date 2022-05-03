package com.example.dataprizma.service;


import com.example.dataprizma.dto.AdvrtiseDto;
import com.example.dataprizma.dto.ServicesDto;
import com.example.dataprizma.model.Advertise;
import com.example.dataprizma.model.Services;
import com.example.dataprizma.repository.AdvertiseRepository;
import com.example.dataprizma.repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesService {

    private final ServicesRepository servicesRepository;



    public List<ServicesDto> servicesList() {
        List<Services> servicesList = (List<Services>) servicesRepository.findAll();
        List<ServicesDto> servicesDtosList = new ArrayList<>(servicesList.size());
        servicesList.forEach(services -> servicesDtosList.add(new ServicesDto(services)));
        return servicesDtosList;

    }



    public void saveServices(Services services) {
        servicesRepository.save(services);

    }

    public Services updateServices(Long id, @RequestBody ServicesDto servicesDto){
        Services services = servicesRepository.findById(id).orElseThrow();
        services.setHeaderEn(servicesDto.getHeaderEn());
        services.setTopicEn(servicesDto.getTopicEn());

        services.setHeaderRu(servicesDto.getHeaderRu());
        services.setTopicRu(servicesDto.getTopicRu());

        services.setHeaderUz(servicesDto.getHeaderUz());
        services.setTopicUz(servicesDto.getTopicUz());
        servicesRepository.save(services);
        return services;
    }

    public void deleteServices(Long id) {
        servicesRepository.deleteById(id);
    }

    public Services getServicesById(Long id) {
        Services services = servicesRepository.findById(id).orElse(null);
        if (services == null) {
            return null;
        } else {
            return services;
        }
    }
}
