package com.example.demo.service.admin;

import com.example.demo.mapper.admin.GoodsTypeMapper;
import com.example.demo.pojo.admin.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsTypeService {
    @Autowired
    private GoodsTypeMapper goodsMapper;

    //   添加商品类别
    public int addGoodsType(String gtName) {
        return goodsMapper.addGoodsType(gtName);
    }

    //查询所有商品类别
    public List<GoodsType> selectAllGoodsType() {
        return goodsMapper.selectAllGoodsType();
    }

    //查询指定商品类别
    public int selectGoodsType(String gtName) {
        return goodsMapper.selectGoodsType(gtName);
    }

    //删除商品类别
    public int deleteGoodTyByName(int gtId) {
        if (goodsMapper.selectGTBygtId(gtId) > 0)
            return 0;
        return goodsMapper.deleteGtype(gtId);
    }
}
