package com.example.demo.controller.admin;

import com.example.demo.service.admin.AdminReportService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminReportController {
    @Autowired
    private AdminReportService adminReportService;


    //销售额
//    商品分类
    @GetMapping("/salesReport/{selectDay}/{index}")
    public Result<?> salesReport(@PathVariable("selectDay") String selectDay,
                                 @PathVariable("index") int index
    ) {
        return Result.success(adminReportService.selectSales(selectDay,index));
    }

    //   商品
    @GetMapping("/selectSalesByGoods/{selectDay}/{gId}/{index}")
    public Result<?> selectSalesByGoods(@PathVariable("gId") int gId,
                                        @PathVariable("selectDay") String selectDay,
                                        @PathVariable("index") int index
    ) {
        return Result.success(adminReportService.selectSalesByGoods(gId, selectDay,index));
    }

    //销量
//    商品分类
    @GetMapping("/sCountReport/{selectDay}/{index}")
    public Result<?> sCountReport(@PathVariable("selectDay") String selectDay,
                                  @PathVariable("index") int index
    ) {
        return Result.success(adminReportService.selectSCount(selectDay,index));
    }

    //商品
    @GetMapping("/selectSCountByGoods/{selectDay}/{gId}/{index}")
    public Result<?> sCountReport(@PathVariable("gId") int gId,
                                  @PathVariable("selectDay") String selectDay,
                                  @PathVariable("index") int index
    ) {
        return Result.success(adminReportService.selectSCountByGoods(gId, selectDay,index));
    }

    //库存
    @GetMapping("/stockReport")
    public Result<?> stockReport() {
        return Result.success(adminReportService.selectStock());
    }
}
