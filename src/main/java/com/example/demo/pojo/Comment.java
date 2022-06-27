package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EntityListeners;





@Document(collection = "comment")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Comment {
    @Id
    private String comment_id;         //评价id
    @JsonProperty(value = "comment_gId")
    private String comment_gId;         //商品id
    @JsonProperty(value = "comment_userId")
    private String comment_userId;    //评论用户id
    @JsonProperty(value = "comment_content")
    private String comment_content;    //评论内容
    @JsonProperty(value = "comment_rate")
    private String comment_rate;    //评论打分
    private String comment_createTime;   //评论时间
    @JsonProperty(value = "comment_responses")
    private String comment_responses;   //评论回复
}
