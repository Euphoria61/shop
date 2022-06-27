package com.example.demo.service.admin;

import com.example.demo.mapper.admin.AdminMapper;
import com.example.demo.pojo.admin.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Admin queryByName(String name) {
        return adminMapper.queryByName(name);
    }
}
