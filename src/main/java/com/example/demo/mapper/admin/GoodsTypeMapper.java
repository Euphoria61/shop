package com.example.demo.mapper.admin;

import com.example.demo.pojo.admin.GoodsType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GoodsTypeMapper {
    int addGoodsType(String gtName);

    //查询所有商品类别
    List<GoodsType> selectAllGoodsType();

    //查询指定商品类别
    int selectGoodsType(String gtName);

    int deleteGtype(int gtId);

    int selectGTBygtId(int gtId);
}
