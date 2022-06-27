package com.example.demo.service.user;

import com.example.demo.mapper.user.ShoppingCartMapper;
import com.example.demo.pojo.admin.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    //查询用户购物车
    public List<Goods> selectCartGoods(int userId) {
        return shoppingCartMapper.selectCartGoods(userId);

    }

    //添加购物车
    public int addCartGoods(int userId, int gId, int sCount) {
        //购物车限额200件
        if (shoppingCartMapper.getAllCartGoods(userId) > 200) {
            return -1;
        }
        return shoppingCartMapper.addCartGoods(userId, gId, sCount);
    }

    //删除购物车商品（可批量）
    public int deleteCartGoods(int userId, String ids) {
        return shoppingCartMapper.deleteCartGoods(userId, ids);
    }

    //修改购物车商品数量
    public int updateGoodsCount(int gId, int sCount, int userId) {
        return shoppingCartMapper.updateGoodsCount(gId, sCount, userId);
    }
}
