package com.example.dataprizma.service;


import org.springframework.stereotype.Service;

import java.io.File;
@Service
public interface UploadPathService {

    File getFilePath(String modifiedFileName, String images);
}