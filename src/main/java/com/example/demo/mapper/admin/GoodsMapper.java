package com.example.demo.mapper.admin;

import com.example.demo.pojo.admin.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
@Repository
public interface GoodsMapper {

    //查询所有商品
    List<Goods> selectAllGoods(@Param("pageNum")Integer pageNum ,@Param("pageSize")Integer pageSize);

    Long getCount();
    Long getCountByword(String word);


    //指定ID商品
    Goods selectGoodsById(int gId);

    //指定类型商品
    List<Goods> selectGoodsByType(String goodsType);

    //关键词搜索
    List<Goods> selectGoodsByWord(String word,@Param("pageNum")Integer pageNum ,@Param("pageSize")Integer pageSize);

    //添加商品
    int addGoods(Goods goods);

    //修改商品信息
    int updateGoods(Goods goods);

    //商品下架
    int offShelf(int gId);

    //商品上架
    int onShelf(int gId);
}
