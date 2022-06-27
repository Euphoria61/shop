package com.example.demo.controller.user;

import com.example.demo.service.user.ShoppingCartService;
import com.example.demo.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class ShoppingCartController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ShoppingCartService shoppingCartService;

    //查询该用户所有购物车商品
    @GetMapping("/selectCartGoods/{userId}")
    public Result<?> selectCartGoods(@PathVariable int userId) {
        //用户未登录
        if (userId == 0) {
            return Result.error("msg", this.messageSource.getMessage("user.notFound", null, Locale.getDefault()));
        }
        return Result.success(shoppingCartService.selectCartGoods(userId));
    }

    //添加购物车
    @PutMapping("/addCartGoods/{userId}/{gId}/{sCount}")
    public Result<?> addCartGoods(@PathVariable("userId") int userId,
                                  @PathVariable("gId") int gId,
                                  @PathVariable("sCount") int sCount) {
        //未登录
        if (userId == 0) {
            return Result.error("msg", this.messageSource.getMessage("user.notFound", null, Locale.getDefault()));
        }
        int i = shoppingCartService.addCartGoods(userId, gId, sCount);
        //购物车限额200件
        if (i == -1) {
            return Result.error("msg", this.messageSource.getMessage("cart.excess", null, Locale.getDefault()));
        }
        if (i == 0) {
            return Result.error("msg", this.messageSource.getMessage("cart.addFail", null, Locale.getDefault()));
        }
        return Result.success();

    }

    //删除购物车商品(批量删除商品)
    @DeleteMapping("/deleteCartGoods/{userId}/{gId}")
    public Result<?> deleteCartGoods(@PathVariable("userId") int userId, @PathVariable("gId") List<Integer> gId) {
        //数组转字符串
        String ids = StringUtils.strip(gId.toString(), "[]");

        if (shoppingCartService.deleteCartGoods(userId, ids) != 0) {
            return Result.success();
        } else
            return Result.error("msg", this.messageSource.getMessage("delete.fail", null, Locale.getDefault()));
    }

    //    修改购物车商品数量
    @DeleteMapping("/updateGoodsCount/{userId}/{gId}/{sCount}")
    public Result<?> updateGoodsCount(@PathVariable("userId") int userId, @PathVariable("gId") int gId,
                                      @PathVariable("sCount") int sCount) {

            return Result.success(shoppingCartService.updateGoodsCount(userId,gId,sCount));
    }
}
