package com.example.demo.controller.user;

import com.example.demo.pojo.admin.Goods;
import com.example.demo.service.user.MongoDbService;
import com.example.demo.service.user.UserCollectionsService;
import com.example.demo.service.user.UserGoodsDetailsService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserGoodsDetailsController {
    @Autowired
    private UserGoodsDetailsService userGoodsDetailsService;

    @Autowired
    private UserCollectionsService userCollectionsService;


    @Autowired
    private MongoDbService mongoDbService;

    //查询全部已上架商品详情
    @GetMapping("/selectGoodsDetails/{gId}")
    public Result<Goods> selectGoodsDetails(@PathVariable int gId) {
        Goods goods = userGoodsDetailsService.selectGoodsDetails(gId);
        return Result.success(goods);
    }

    //查询该商品是否被该用户收藏  0未1已
    @GetMapping("/queryIsCollected/{userId}/{gId}")
    public Result<?> queryIsCollected(@PathVariable("userId") int userId, @PathVariable("gId") int gId) {
        return Result.success(userCollectionsService.queryIsCollected(userId, gId));
    }

    @GetMapping("/queryComment/{gId}")
    public Result<?> queryIsCollected(@PathVariable("gId") int gId) {
        return Result.success(mongoDbService.findByGoods(gId));
    }
}
