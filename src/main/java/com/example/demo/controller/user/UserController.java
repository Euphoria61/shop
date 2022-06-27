package com.example.demo.controller.user;


import com.example.demo.pojo.user.User;
import com.example.demo.service.user.UserService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserService userService;

    //用户登录实现
    @PostMapping("/login")
    public Result<?> login(@RequestBody Map<Object, String> map, HttpSession session
    ) {
        if (!map.get("code").equalsIgnoreCase(session.getAttribute("code").toString())) {
            return Result.error("msg", this.messageSource.getMessage("validateCode.false", null, Locale.getDefault()));
        }
//        MD5加密
        String md5Pwd = DigestUtils.md5DigestAsHex(map.get("userPwd").getBytes());
        if (userService.queryByEmail(map.get("userEmail")) == null) {
            //邮件名不存在
            return Result.error("msg", this.messageSource.getMessage("userEmail.notfound", null, Locale.getDefault()));

        } else {
            if (userService.queryByEmail(map.get("userEmail")).getUserPwd().equals(md5Pwd)) {
                //密码正确
                User user = new User();
                user.setUserEmail(map.get("userEmail"));
                user.setUserPwd(map.get("userPwd"));
                user.setUserId(userService.queryByEmail(map.get("userEmail")).getUserId());
                return Result.success(user);
            } else
                //密码错误
                return Result.error("msg", this.messageSource.getMessage("password.false", null, Locale.getDefault()));

        }
    }


    //注册实现
    @PostMapping("/register")
    public Result<?> register(@RequestBody Map<Object, String> map, HttpSession session) {
        if (!map.get("code").equalsIgnoreCase(session.getAttribute("code").toString())) {
            return Result.error("msg", this.messageSource.getMessage("validateCode.false", null, Locale.getDefault()));
        }
        String md5Pwd = DigestUtils.md5DigestAsHex(map.get("userPwd").getBytes());

        //验证是否存在该用户
        if (userService.queryByEmail(map.get("userEmail")) == null) {
            System.out.println("插入");

            int n = userService.insertUser(map.get("userEmail"), md5Pwd);
            if (n > 0)
                return Result.success();
            else
                return Result.error("msg", this.messageSource.getMessage("register.false", null, Locale.getDefault()));

        } else
            return Result.error("msg", this.messageSource.getMessage("user.exist", null, Locale.getDefault()));
    }

    //退出登录
    @RequestMapping("/exit")
    public Result<?> exit(HttpSession session) {
        session.invalidate();
        return Result.success();
    }
}

