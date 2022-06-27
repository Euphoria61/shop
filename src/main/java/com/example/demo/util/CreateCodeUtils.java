package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class CreateCodeUtils {
    @Autowired
    private RedisUtil redisUtil;

    //生成code的长度
    private static final int codeLength = 12;

    /**
     * type标明业务类型
     */
    public String createCode(String type) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
        //日期code
        String dateCode = sd.format(new Date());
        //redis里的key
        String key = dateCode + type;
        //redis的code
        String code = "";
        //查询是否存在这个key
        Boolean flag = redisUtil.hasKey(key);
        //生成的code值
        String resultCode = "";
        if (flag) {

            code = String.valueOf(redisUtil.incr(key, 1));

            int length = codeLength - 8 - (code.length());
            //拼接code前边的0
            String ling = "";
            for (int i = 0; i < length; i++) {
                ling += "0";
            }

            resultCode = dateCode + ling + code;

        } else {
            resultCode = dateCode + "0001";
        }
        return resultCode;
    }
}
