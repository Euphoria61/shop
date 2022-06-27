package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {

    public static String getStringID() {
        String id = null;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        id = sdf.format(date);
        return id;
    }

}
