package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsOrder {
    @JsonProperty(value = "gOId")
    private Long gOId;
    @JsonProperty(value = "userId")
    private int userId;
    @JsonProperty(value = "gId")
    private int gId;
    private int sCount;
    @JsonProperty(value = "status")
    private int status;
    @JsonProperty(value = "createDate")
    private String createDate;
}
