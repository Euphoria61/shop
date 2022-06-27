package com.example.demo.mapper.admin;

import com.example.demo.pojo.admin.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper {
    Admin queryByName(String name);

}
