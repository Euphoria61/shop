package com.example.demo.service.user;

import com.example.demo.mapper.user.UserCollectionsMapper;
import com.example.demo.pojo.admin.Goods;
import com.example.demo.pojo.user.RespPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCollectionsService {
    @Autowired
    private UserCollectionsMapper userCollectionsMapper;

    //查询是否被收藏
    public int queryIsCollected(int userId, int gId) {
        if (userCollectionsMapper.queryIsCollected(userId, gId) != null) return 1;
        return 0;
    }

    //用户全部藏品
    public RespPage selectAllCollected(int userId, Integer pageNum, Integer pageSize) {

        RespPage pageEntity = new RespPage();
        if (pageNum != null && pageSize != null) {
            pageNum = (pageNum - 1) * pageSize;
        }
        List<Goods> goods = userCollectionsMapper.selectAllCollected(userId, pageNum, pageSize);
        pageEntity.setData(goods);

        Long total = userCollectionsMapper.getAllCollected(userId);
        pageEntity.setTotal(total);
        return pageEntity;

    }

    //添加收藏
    public int addCollections(int userId, int gId) {
        //收藏限额200件
        if (userCollectionsMapper.getAllCollected(userId) > 200) {
            return -1;
        }
        return userCollectionsMapper.addCollections(userId, gId);
    }

    //删除收藏
    public int deleteCollections(int userId, String ids) {
        return userCollectionsMapper.deleteCollections(userId, ids);
    }
}
