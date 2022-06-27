package com.example.demo.mapper.user;

import com.example.demo.pojo.admin.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserGoodsDetailsMapper {
    Goods selectGoodsDetails(int gId);

}
