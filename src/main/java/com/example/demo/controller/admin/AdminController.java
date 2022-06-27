package com.example.demo.controller.admin;

import com.example.demo.service.admin.AdminService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AdminService adminService;

    //管理员登录实现
    @RequestMapping("/login")
    public Result<?> login(@RequestBody Map<Object, String> map, HttpSession session) {
        if (!map.get("code").equalsIgnoreCase(session.getAttribute("code").toString())) {
            return Result.error("msg", this.messageSource.getMessage("validateCode.false", null, Locale.getDefault()));
        }
        //MD5加密
        String md5Pwd = DigestUtils.md5DigestAsHex(map.get("adminPwd").getBytes());
        System.out.println(md5Pwd);
        if (adminService.queryByName(map.get("adminName")) == null) {
            //管理员不存在
            return Result.error("msg", this.messageSource.getMessage("admin.notFound", null, Locale.getDefault()));

        } else {
            if (adminService.queryByName(map.get("adminName")).getAdminPwd().equals(md5Pwd)) {
                //密码正确
                return Result.success();
            } else
                //密码错误
            return Result.error("msg", this.messageSource.getMessage("password.false", null, Locale.getDefault()));
        }
    }

    //退出登录
    @PutMapping("/exit")
    public Result<?> exit(HttpSession session) {
        session.invalidate();
        return Result.success();
    }
}
