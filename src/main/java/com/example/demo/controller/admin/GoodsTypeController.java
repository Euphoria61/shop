package com.example.demo.controller.admin;

import com.example.demo.pojo.admin.GoodsType;
import com.example.demo.service.admin.GoodsTypeService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/admin")

public class GoodsTypeController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private GoodsTypeService goodsService;

    //所有商品类别
    @GetMapping("/allGoodsType")
    public Result<?> allGoodsType() {
        List<GoodsType> GoodsTypeList = goodsService.selectAllGoodsType();
        return Result.success(GoodsTypeList);
    }

    //添加商品类别
    @PutMapping("/addGType/{gtName}")
    public Result<?> addGType(@PathVariable("gtName")String gtName) {
        if (goodsService.selectGoodsType(gtName) != 0) {
            Result.error("msg", this.messageSource.getMessage("goodsType.exist", null, Locale.getDefault()));
        } else {
            if (goodsService.addGoodsType(gtName) == 0) Result.error("msg", this.messageSource.getMessage("add.false", null, Locale.getDefault()));
        }
        return Result.success();
    }

    //删除商品类别
    @DeleteMapping("/deleteGType/{gtId}")
    public Result<?> deleteGType(@PathVariable("gtId") int gtId) {
        //查询是否有关联商品
        if (goodsService.deleteGoodTyByName(gtId) == 0) {
            return Result.error("msg", this.messageSource.getMessage("goodsType.delete.false", null, Locale.getDefault()));
        } else
        return Result.success();
    }
}
