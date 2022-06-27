package com.example.demo.mapper.user;

import com.example.demo.pojo.GoodsOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface GoodsOrderMapper {
    List<Map> selectMyOrder(@Param("userId") int userId, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
    Long getMyOrders(int userId);
    //添加订单
    int addGoodsOrder(GoodsOrder goodsOrder);
    //修改状态
    int updateStatus(@Param("code")Long code,@Param("status")int status);
    //库存减少
    int reduce(@Param("gId") int gId,@Param("sCount")  int sCount);
    //库存回滚
    int increase(@Param("code") Long code);
    //查库存
    int selectStore(int gId);
    //评分
    int addRate(@Param("userId") String userId, @Param("gId") String gId, @Param("rate") String rate);
}
