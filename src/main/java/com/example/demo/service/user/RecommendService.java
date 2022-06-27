package com.example.demo.service.user;

import com.example.demo.mapper.user.UserGoodsMapper;
import com.example.demo.pojo.admin.Goods;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecommendService {
    @Autowired
    private UserGoodsMapper userGoodsMapper;

    @Autowired
    private DataModel dataModel;

    public List<Goods> getRecommendGoods(int userId, int gId, int num) {
        List<Goods> list = null;
        try {
            //采用基于皮尔逊相关性的相似度算法,计算用户之间的相似度
            //得到内容项（商品）的历史评分数据；
            ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);

            //构建推荐器，使用基于物品的协同过滤算法推荐
            //针对商品进行商品之间的相似度计算，找到目标商品的“最近邻居”；
            //通过用户对不同商品的评分来评测商品之间的相似性，基于商品之间的相似性做出推荐。
            GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
            long start = System.currentTimeMillis();

            //产生推荐。这里商品之间的相似度是通过比较两个商品上的用户行为选择矢量得到的。
            // 物品推荐相似度，计算两个商品同时出现的次数，次数越多任务的相似度越高。
            List<RecommendedItem> recommendedItemList = recommender.recommendedBecause(userId, gId, num);

            List<Long> itemIds = new ArrayList<Long>();
            for (RecommendedItem recommendedItem : recommendedItemList) {
                System.out.println(recommendedItem);
                itemIds.add(recommendedItem.getItemID());
            }
            System.out.println(itemIds);
            //根据商品id查询商品
            if (itemIds.size() > 0) {
                list = userGoodsMapper.selectGoodsByIds(itemIds);
            } else {
                list = new ArrayList<>();
            }
        } catch (TasteException e) {
            e.printStackTrace();
        }
        return list;
    }
}
