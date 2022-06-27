package com.example.demo.mapper.admin;

import com.example.demo.pojo.GoodsOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AdminOrderMapper {
   List<Map> selectOrder(@Param("goodsOrder") GoodsOrder goodsOrder, @Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
   Long getOrderCount(@Param("goodsOrder") GoodsOrder goodsOrder);
   int updateOrderStatus(@Param("codes") String codes,@Param("status")int status);
}
