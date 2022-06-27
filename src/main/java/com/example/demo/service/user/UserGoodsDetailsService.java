package com.example.demo.service.user;

import com.example.demo.mapper.user.UserGoodsDetailsMapper;
import com.example.demo.pojo.admin.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserGoodsDetailsService {
    @Autowired
    private UserGoodsDetailsMapper userGoodsDetailsMapper;

    public Goods selectGoodsDetails(int gId) {
        return userGoodsDetailsMapper.selectGoodsDetails(gId);
    }
}
