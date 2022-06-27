package com.example.demo.service.user;

import cn.hutool.core.date.DateUtil;
import com.example.demo.mapper.user.GoodsOrderMapper;
import com.example.demo.pojo.Comment;
import com.example.demo.pojo.GoodsOrder;
import com.example.demo.pojo.user.RespPage;
import com.example.demo.util.CreateCodeUtils;
import com.example.demo.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
public class GoodsOrderService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CreateCodeUtils codeUtils;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private GoodsOrderMapper goodsOrderMapper;

    //status    （订单取消）-2 付款失败-1   未付款：0  (已支付)未发货：1   派送中：2  待取件：3   已签收：4
    //确认订单
    public long confirmGoodsOrder(int userId, int gId, int sCount) {
        String key =codeUtils.createCode("01");
        System.out.println(codeUtils.createCode("01"));
        //若不存在订单则新增
        if (!redisUtil.hasKey(key)){
            HashMap<String, Object> map = new HashMap<>();
            map.put("gId", gId);
            map.put("sCount", sCount);
            map.put("createDate", DateUtil.now());
            map.put("status", 0);
            GoodsOrder goodsOrder =new GoodsOrder();
            goodsOrder.setGId(gId);
            goodsOrder.setUserId(userId);
            goodsOrder.setSCount(sCount);
            goodsOrder.setCreateDate(DateUtil.now());
            goodsOrder.setStatus(0);
            goodsOrder.setGOId(Long.parseLong(key));
            goodsOrderMapper.addGoodsOrder(goodsOrder);
            //设置10分钟过期,失败返回
            if (!redisUtil.hmset(key, map, 600)) return 0;
            //库存减少
            goodsOrderMapper.reduce(gId, sCount);
        } else
            //已存在该商品订单且未支付成功！
            if ((int) redisUtil.hget(key, "status") == 0) return -1;
        return Long.parseLong(key);
    }

    //执行支付
    public String payGoodOrder( String subject,Long code, BigDecimal money) throws Exception {

        String i = alipayService.toPay(subject,code, money);
        //支付成功后修改状态
        if(i !=null){
            redisTemplate.persist(String.valueOf(code));
            redisUtil.hset(String.valueOf(code), "status", 1);
            goodsOrderMapper.updateStatus(code,1);
        }
        return i;
    }

    //取消订单(撤单)
    public int cancelGoodsOrder(Long code) {
        redisUtil.hdel(String.valueOf(code));
        goodsOrderMapper.updateStatus(code, -2);
        return 1;
    }

    //收货,评分
    public int commentGoodsOrder(Comment comment, Long code) {
        goodsOrderMapper.updateStatus(code, 7);
        goodsOrderMapper.addRate(comment.getComment_userId(),comment.getComment_gId(),comment.getComment_rate());
        return 1;
    }

    //我的订单
    public RespPage myGoodsOrder(int userId, Integer pageNum, Integer pageSize) {

        RespPage pageEntity = new RespPage();
        if (pageNum != null && pageSize != null) {
            pageNum = (pageNum - 1) * pageSize;
        }
        List<Map> goodsOrder = goodsOrderMapper.selectMyOrder(userId,pageNum, pageSize);
        pageEntity.setData(goodsOrder);
        // 获取当前用户总量
        Long total = goodsOrderMapper.getMyOrders(userId);
        pageEntity.setTotal(total);
        return pageEntity;
    }


    //库存回滚
    public int incrStore(Long code) {
        int i = goodsOrderMapper.increase(code);
        if (i != 0) return 1;
        return 0;
    }

    //查询商品库存
    public int selectStore(int gId) {
        return goodsOrderMapper.selectStore(gId);
    }

    //收货
    public int receivedGoods(Long code){
        return goodsOrderMapper.updateStatus(code,4);
    }
}
