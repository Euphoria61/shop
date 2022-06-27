package com.example.demo.service.admin;

import com.example.demo.mapper.admin.GoodsMapper;
import com.example.demo.pojo.admin.Goods;
import com.example.demo.pojo.user.RespPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    //所有商品
    public RespPage selectAllGoods(Integer pageNum, Integer pageSize) {

        RespPage pageEntity = new RespPage();
        if (pageNum != null && pageSize != null) {
            pageNum = (pageNum - 1) * pageSize;
        }
        List<Goods> goods = goodsMapper.selectAllGoods(pageNum, pageSize);
        pageEntity.setData(goods);

        Long total = goodsMapper.getCount();
        pageEntity.setTotal(total);
        return pageEntity;
    }

    //指定ID商品
    public Goods selectGoodsById(int gId) {
        return goodsMapper.selectGoodsById(gId);
    }

    //指定类型商品
    public List<Goods> selectGoodsByType(String goodsType) {
        return goodsMapper.selectGoodsByType(goodsType);
    }

    //关键词搜索商品
    public RespPage selectGoodsByWord(String word,Integer pageNum, Integer pageSize) {

        RespPage pageEntity = new RespPage();
        if (pageNum != null && pageSize != null) {
            pageNum = (pageNum - 1) * pageSize;
        }
        List<Goods> goods = goodsMapper.selectGoodsByWord(word,pageNum, pageSize);
        pageEntity.setData(goods);

        Long total = goodsMapper.getCountByword(word);
        pageEntity.setTotal(total);
        return pageEntity;
    }

    //添加商品
    public int addGoods(Goods goods) {
        return goodsMapper.addGoods(goods);
    }

    //    修改商品信息
    public int updateGoods(Goods goods) {
        return goodsMapper.updateGoods(goods);
    }

    //    商品下架
    public int offShelf(int gId) {
        return goodsMapper.offShelf(gId);
    }
    //商品上架
    public int onShelf(int gId) {
        return goodsMapper.onShelf(gId);
    }
}
