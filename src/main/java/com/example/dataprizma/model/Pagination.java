package com.example.dataprizma.model;

import lombok.Data;


    @Data
    public class Pagination <T>{

        Integer page;
        Integer limit;
        String search;


    }

