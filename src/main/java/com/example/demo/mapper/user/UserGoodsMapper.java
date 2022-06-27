package com.example.demo.mapper.user;

import com.example.demo.pojo.admin.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserGoodsMapper {
    //    全部已上架商品
    List<Goods> selectGoodsShelfed(Integer pageNum, Integer pageSize);

    Long getGoodsTotal();

    //指定类型商品
    List<Goods> selectGoodsByType(String goodsType);

    Goods selectGoodsById(int gId);
    List<Goods> selectGoodsByIds(List<Long> Ids);

    //关键词搜索
    List<Goods> selectGoodsByWord(String word);


}
