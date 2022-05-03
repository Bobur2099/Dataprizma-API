package com.example.dataprizma.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UnsupportedMediaType extends RuntimeException {
    public UnsupportedMediaType(String exception) {
        super(exception);
    }
}