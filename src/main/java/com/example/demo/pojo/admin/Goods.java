package com.example.demo.pojo.admin;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data

public class Goods {
    private int gId;
    private String gName;
    private double gPriceOld;
    private double gPriceNew;
    private MultipartFile image;
    private int gStore;
    private String gPicture;
    private int gtId;
    private int isShelf;
    private String gtName;
    private String gDetails;
}
