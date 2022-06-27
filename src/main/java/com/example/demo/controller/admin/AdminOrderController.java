package com.example.demo.controller.admin;

import com.example.demo.pojo.GoodsOrder;
import com.example.demo.service.admin.AdminOrderService;
import com.example.demo.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;


@RestController
@RequestMapping("/admin")
public class AdminOrderController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private AdminOrderService adminOrderService;
    //status    （订单取消）-2 付款失败-1   未付款：0  (已支付)未发货：1  撤销订单（退款）：2  已发货：3 派送中：4  待取件：5   已签收：6
    //订单
    @PostMapping("/goodsOrder/{pageNum}/{pageSize}")
    public Result<?> GoodsOrder(@RequestBody GoodsOrder goodsOrder,
                                @PathVariable("pageNum") Integer pageNum,
                                @PathVariable("pageSize") Integer pageSize
    ){
        return Result.success(adminOrderService.selectGoodsOrder(goodsOrder,pageNum,pageSize));
    }

    //发货(批量)
    @PutMapping("/deliverGoods/{code}")
    public Result<?> DeliverGoods(@PathVariable("code" ) List<Long> code
    ){
        String codes = StringUtils.strip(code.toString(), "[]");
        int i =adminOrderService.deliverGoods(codes);
        if(i == 0)return  Result.error("msg", this.messageSource.getMessage("deliverGoods.fail", null, Locale.getDefault()));
        return Result.success();
    }

    //撤销订单/退款(批量)
    @PutMapping("/refundGoods/{code}")
    public Result<?> RefundGoods(@PathVariable("code" ) List<Long> code
    ){
        String codes = StringUtils.strip(code.toString(), "[]");
        int i =adminOrderService.refundGoods(codes);
        if(i == 0)return  Result.error("msg", this.messageSource.getMessage("refundGoods.fail", null, Locale.getDefault()));
        return Result.success();
    }
}
