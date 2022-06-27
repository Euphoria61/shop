package com.example.demo.service.user;

import com.example.demo.mapper.user.UserMapper;
import com.example.demo.pojo.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    //邮件名查询User
    public User queryByEmail(String email) {
        return userMapper.queryByEmail(email);
    }

    //
    public int insertUser(String email, String pwd) {
        return userMapper.insertUser(email, pwd);
    }
}

