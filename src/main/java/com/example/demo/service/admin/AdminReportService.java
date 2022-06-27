package com.example.demo.service.admin;

import cn.hutool.core.lang.hash.Hash;
import com.example.demo.mapper.admin.AdminReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class AdminReportService {
    @Autowired
    private AdminReportMapper adminReportMapper;

    //销售额
    public List<Map> selectSales(String selectDay,int index) {
        if(index ==0)return adminReportMapper.selectSalesByTypeD(selectDay);
        return adminReportMapper.selectSalesByTypeM(selectDay);
    }
    public List<Map> selectSalesByGoods(int gId, String selectDay,int index) {
        if(index ==0)return adminReportMapper.selectSalesByGoodsD(selectDay,gId);
        return adminReportMapper.selectSalesByGoodsM(selectDay,gId);
    }


    //销量
    public List<Map> selectSCount( String selectDay,int index) {
        if(index ==0)return adminReportMapper.selectSCByTypeD(selectDay);
        return adminReportMapper.selectSCByTypeM(selectDay);
    }
    public List<Map> selectSCountByGoods(int gId, String selectDay,int index) {
        if(index ==0)return adminReportMapper.selectSCByGoodsD(selectDay,gId);
        return adminReportMapper.selectSCByGoodsM(selectDay,gId);
    }

    //库存
    public List<Map> selectStock() {
       return adminReportMapper.selectStock();
    }
}
