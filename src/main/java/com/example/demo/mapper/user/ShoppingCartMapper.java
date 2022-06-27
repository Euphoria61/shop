package com.example.demo.mapper.user;

import com.example.demo.pojo.admin.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ShoppingCartMapper {
    //查询所有购物车商品
    List<Goods> selectCartGoods(int userId);

    //添加到购物车
    int addCartGoods(@Param("userId") int userId, @Param("gId") int gId, @Param("sCount") int sCount);

    //删除购物车商品
    int deleteCartGoods(@Param("userId") int userId, @Param("ids") String gId);

    //修改数量
    int updateGoodsCount(@Param("gId") int gId, @Param("sCount") int sCount, @Param("userId") int userId);

    //购物车数量
    Long getAllCartGoods(@Param("userId") int userId);
}
