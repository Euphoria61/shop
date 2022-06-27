package com.example.demo.mapper.user;

import com.example.demo.pojo.admin.Goods;
import com.example.demo.pojo.user.Collections;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserCollectionsMapper {
    //        查询该商品是否被该用户收藏
    Collections queryIsCollected(@Param("userId") int userId, @Param("gId") int gId);

    //查询该用户所有收藏
    List<Goods> selectAllCollected(@Param("userId") int userId, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    Long getAllCollected(int userId);

    int addCollections(@Param("userId") int userId, @Param("gId") int gId);

    int deleteCollections(@Param("userId") int userId, @Param("ids") String ids);
}
