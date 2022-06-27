package com.example.demo.controller.user;

import com.example.demo.pojo.user.RespPage;
import com.example.demo.service.user.UserCollectionsService;
import com.example.demo.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
public class UserCollectionsController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserCollectionsService userCollectionsService;

    //查询该用户所有收藏
    @GetMapping("/selectAllCollected/{userId}")
    public Result<RespPage> selectAllCollected(@PathVariable("userId") int userId,
                                               @RequestParam(defaultValue = "1") Integer pageNum,
                                               @RequestParam(defaultValue = "20") Integer pageSize) {
        if (userId == 0) {
            return Result.error("msg", this.messageSource.getMessage("user.notFound", null, Locale.getDefault()));
        }
        return Result.success(userCollectionsService.selectAllCollected(userId, pageNum, pageSize));
    }

    //添加收藏
    @PutMapping("/addCollections/{userId}/{gId}")
    public Result<?> addCollections(@PathVariable("userId") int userId, @PathVariable("gId") int gId) {
        //未登录
        if (userId == 0) {
            return Result.error("msg", this.messageSource.getMessage("user.notFound", null, Locale.getDefault()));
        }
        int i = userCollectionsService.addCollections(userId, gId);
        //收藏限额200件
        if (i == -1) {
            return Result.error("msg", this.messageSource.getMessage("collections.excess", null, Locale.getDefault()));
        }
        if (i == 0) {
            return Result.error("msg", this.messageSource.getMessage("collections.addFail", null, Locale.getDefault()));
        }
        return Result.success();

    }

    //取消收藏(批量删除藏品)
    @DeleteMapping("/deleteCollections/{userId}/{gId}")
    public Result<?> deleteCollections(@PathVariable("userId") int userId, @PathVariable("gId") List<Integer> gId) {
        //数组转字符串
        String ids = StringUtils.strip(gId.toString(), "[]");

        if (userCollectionsService.deleteCollections(userId, ids) != 0) {
            return Result.success();
        } else
            return Result.error("msg", this.messageSource.getMessage("delete.fail", null, Locale.getDefault()));
    }

}
