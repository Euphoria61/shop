package com.example.demo.service.user;

import com.example.demo.mapper.user.UserGoodsMapper;
import com.example.demo.pojo.admin.Goods;
import com.example.demo.pojo.user.RespPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGoodsService {
    @Autowired
    private UserGoodsMapper userGoodsMapper;


    //全部已上架商品
    public RespPage selectGoodsShelfed(Integer pageNum, Integer pageSize) {
        RespPage pageEntity = new RespPage();
        if (pageNum != null && pageSize != null) {
            pageNum = (pageNum - 1) * pageSize;
        }
        List<Goods> goods = userGoodsMapper.selectGoodsShelfed(pageNum, pageSize);
        pageEntity.setData(goods);
        // 获取当前用户总量
        Long total = userGoodsMapper.getGoodsTotal();
        pageEntity.setTotal(total);
        return pageEntity;


    }

    //指定类型商品
    public List<Goods> selectGoodsByType(String goodsType) {
        return userGoodsMapper.selectGoodsByType(goodsType);
    }


    //指定类型商品
    public Goods selectGoodsById(int gId) {
        return userGoodsMapper.selectGoodsById(gId);
    }

    //关键词搜索商品
    public List<Goods> selectGoodsByWord(String word) {
        return userGoodsMapper.selectGoodsByWord(word);
    }

}
