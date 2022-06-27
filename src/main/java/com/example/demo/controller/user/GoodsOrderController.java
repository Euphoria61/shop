package com.example.demo.controller.user;

import cn.hutool.core.date.DateUtil;
import com.example.demo.pojo.Comment;
import com.example.demo.pojo.user.RespPage;
import com.example.demo.service.admin.GoodsService;
import com.example.demo.service.user.GoodsOrderService;
import com.example.demo.service.user.MongoDbService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
public class GoodsOrderController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private GoodsOrderService goodsOrderService;

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MongoDbService mongoDbService;

    //确认订单
    @PutMapping("/confirmGoodsOrder/{userId}/{gId}/{sCount}")
    public Result<?> confirmGoodsOrder(@PathVariable("userId") int userId,
                                       @PathVariable("gId") int gId,
                                       @PathVariable("sCount") int sCount) {

        if(userId ==0)Result.error("msg", this.messageSource.getMessage("user.notFound", null, Locale.getDefault()));
        //验证是否超售
        if (goodsOrderService.selectStore(gId) < sCount)
            //超售
            return Result.error("msg", this.messageSource.getMessage("sCount.excess", null, Locale.getDefault()));

        long i = goodsOrderService.confirmGoodsOrder(userId, gId, sCount);
        if (i == -1)
            //已存在该商品订单且未支付成功！
            return Result.error("msg", this.messageSource.getMessage("order.exist", null, Locale.getDefault()));
        if (i == 0 || sCount <= 0)
            //订单创建失败
            return Result.error("msg", this.messageSource.getMessage("order.fail", null, Locale.getDefault()));
        return Result.success(i);
    }

    //  支付
    @PutMapping("/payGoods")
    public Result<?> payGoodsOrder(@RequestParam("code") Long code,
                                @RequestParam("subject") String subject,
                                @RequestParam("money") BigDecimal money) throws Exception {
        String form = goodsOrderService.payGoodOrder(subject,code, money);
        return Result.success(form);
    }



    //我的订单
    @GetMapping("/myGoodsOrder/{userId}")
    public Result<?> myGoodsOrder(@PathVariable("userId") int userId,
                                  @RequestParam(defaultValue = "1") Integer pageNum,
                                  @RequestParam(defaultValue = "20") Integer pageSize
                                  ){
        return Result.success(goodsOrderService.myGoodsOrder(userId,pageNum,pageSize));
    }


    //取消订单
    @PutMapping("/cancelGoodsOrder/{code}")
    public Result<?> cancelGoodsOrder(@PathVariable("code") Long code){
        return Result.success(goodsOrderService.cancelGoodsOrder(code));
    }

    //签收成功，评价
    @PutMapping("/receivedGoods/{code}")
    public Result<?> receivedGoods(@RequestBody Comment comment,
                                    @PathVariable("code") Long code){
        goodsOrderService.receivedGoods(code);
        //评价
        comment.setComment_createTime(DateUtil.now());
        mongoDbService.save(comment);
        goodsOrderService.commentGoodsOrder(comment,code);
        System.out.println("查询所有"+mongoDbService.findAll());
        return Result.success();
    }


}














