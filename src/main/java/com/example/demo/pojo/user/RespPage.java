package com.example.demo.pojo.user;

import lombok.Data;

import java.util.List;

@Data
public class RespPage {
    private List<?> data;
    private Long total;
}
