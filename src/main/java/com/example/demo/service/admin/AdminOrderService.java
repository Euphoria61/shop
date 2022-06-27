package com.example.demo.service.admin;

import com.example.demo.mapper.admin.AdminOrderMapper;
import com.example.demo.pojo.GoodsOrder;
import com.example.demo.pojo.user.RespPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AdminOrderService {
    @Autowired
    private AdminOrderMapper adminOrderMapper;

    //查看订单
    public RespPage selectGoodsOrder(GoodsOrder goodsOrder,Integer pageNum, Integer pageSize) {
        System.out.println(goodsOrder);

        RespPage pageEntity = new RespPage();
        if (pageNum != null && pageSize != null) {
            pageNum = (pageNum - 1) * pageSize;
        }
        List<Map> goodsOrders = adminOrderMapper.selectOrder(goodsOrder, pageNum, pageSize);
        pageEntity.setData(goodsOrders);
        Long total = adminOrderMapper.getOrderCount(goodsOrder);
        pageEntity.setTotal(total);
        return pageEntity;

    }
    //发货
    public int deliverGoods(String code){
        //发货业务
        return adminOrderMapper.updateOrderStatus(code,3);
    }

    //撤销订单（退款）
    public int refundGoods(String code){
        //撤销业务
        return adminOrderMapper.updateOrderStatus(code,2);
    }






























}
