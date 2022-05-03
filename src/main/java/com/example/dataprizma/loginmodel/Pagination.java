package com.example.dataprizma.loginmodel;

import lombok.Data;

@Data
public class Pagination<T> {
    private Integer page;
    private Integer limit;
    private String order;
    private String type;
    private T search;
}
