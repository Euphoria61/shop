package com.example.demo.controller.user;

import com.example.demo.pojo.admin.Goods;
import com.example.demo.pojo.user.RespPage;
import com.example.demo.service.user.RecommendService;
import com.example.demo.service.user.UserGoodsService;
import com.example.demo.util.RedisUtil;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController

public class UserGoodsController {

    @Autowired
    private UserGoodsService userGoodsService;

    @Autowired
    private RecommendService recommendService;


    //查询全部已上架商品
    @GetMapping("/goodsShelfed")
    public Result<RespPage> selectGoodsShelfed(@RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "20") Integer pageSize) {

        return Result.success(userGoodsService.selectGoodsShelfed(pageNum, pageSize));
    }

    //根据类型查询商品
    @RequestMapping("/goodsByType")
    public Result<?> goodsByType(HttpServletRequest request, Model model) {
        String goodsType = request.getParameter("goodsType");
        List<Goods> goods = userGoodsService.selectGoodsByType(goodsType);
        return Result.success(goods);
    }

    //关键词搜索商品

    @GetMapping("/goodsByWord/{word}")
    public Result<List<Goods>> goodsByWord(@PathVariable String word) {

        List<Goods> goods = userGoodsService.selectGoodsByWord(word);

        return Result.success(goods);
    }

    //推荐商品
    @GetMapping("/goodsByWord/{userId}/{gId}/{num}")
    public Result<?> getRecommendGoods(@PathVariable("userId")int userId,
                                         @PathVariable("gId")int gId,
                                         @PathVariable("num")int num){
        return Result.success(recommendService.getRecommendGoods(userId,gId,num));
    }





}
