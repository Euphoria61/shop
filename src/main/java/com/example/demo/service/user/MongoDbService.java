package com.example.demo.service.user;

import com.example.demo.pojo.Comment;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MongoDbService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(Comment comment) {
       mongoTemplate.save(comment);
    }
    public List<Comment> findAll() {
        return mongoTemplate.findAll(Comment.class);
    }

    public List<Comment> findByGoods(int gId){
        Query query = new Query().addCriteria(Criteria.where("comment_gId").is(String.valueOf(gId)));
        System.out.println(query);
        System.out.println(gId);
        System.out.println("查询所有"+mongoTemplate.findAll(Comment.class));
        System.out.println("查询条件"+mongoTemplate.find(query,Comment.class));
        return mongoTemplate.find(query,Comment.class);
    }

}
