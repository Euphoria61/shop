package com.example.demo.service.user;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
@Service
public class AlipayService {
    public String toPay(String subject,Long code, BigDecimal money) throws Exception {
        // 最后一个参数是支付完成之后跳转到的界面, 一般为项目的首页
        AlipayTradePagePayResponse pay = Factory.Payment.Page().pay(subject, String.valueOf(code),
                String.valueOf(money), "http://localhost:9876/myOrders");
        String payForm = null;
        if (ResponseChecker.success(pay)) {
            payForm = pay.getBody();
        }
        return payForm;
    }


}
