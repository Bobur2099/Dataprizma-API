package com.example.dataprizma.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.io.File;

@Service
public class UploadPathServiceImpl implements UploadPathService {

    @Autowired
    private ServletContext context;

    @Override
    public File getFilePath(String modifiedFileName, String path) {

        boolean exists = new File(context.getRealPath("/" + path + "/")).exists();
        if (!exists) {
            new File(context.getRealPath("/" + path + "/")).mkdirs();
        }
        String modifiedFilePath = context.getRealPath("/" + path + "/" + File.separator + modifiedFileName);
        return new File(modifiedFilePath);
    }
}

