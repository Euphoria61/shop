package com.example.demo.mapper.admin;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface AdminReportMapper {

    List<Map> selectSalesByTypeD(@Param("selectDay") String selectDay);
    List<Map> selectSalesByTypeM(@Param("selectDay") String selectDay);
    List<Map> selectSalesByGoodsD(@Param("selectDay")String selectDay,@Param("gId") int gId);
    List<Map> selectSalesByGoodsM(@Param("selectDay")String selectDay,@Param("gId") int gId);
    List<Map> selectSCByTypeD(@Param("selectDay") String selectDay);
    List<Map> selectSCByTypeM(@Param("selectDay") String selectDay);
    List<Map> selectSCByGoodsD(@Param("selectDay")String selectDay,@Param("gId") int gId);
    List<Map> selectSCByGoodsM(@Param("selectDay")String selectDay,@Param("gId") int gId);
    List<Map> selectStock();
}
