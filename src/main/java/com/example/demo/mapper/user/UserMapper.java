package com.example.demo.mapper.user;


import com.example.demo.pojo.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User queryByEmail(String email);

    int insertUser(@Param("email") String email, @Param("pwd") String pwd);
}
