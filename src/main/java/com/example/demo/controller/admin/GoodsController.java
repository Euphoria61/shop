package com.example.demo.controller.admin;

import com.example.demo.pojo.admin.Goods;
import com.example.demo.pojo.user.RespPage;
import com.example.demo.service.admin.GoodsService;
import com.example.demo.service.admin.GoodsTypeService;
import com.example.demo.util.MyUtil;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

@RestController
@RequestMapping("/admin")
public class GoodsController {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsTypeService goodsTypeService;

    //所有商品
    @GetMapping("/allGoods")
    public Result<RespPage> allGoods(@RequestParam(defaultValue = "1") Integer pageNum,
                                     @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        return Result.success(goodsService.selectAllGoods(pageNum, pageSize));
    }

    //    添加商品
    @PostMapping("/addGoods")
    public Result<?> addGoods(@RequestParam("image") MultipartFile image,HttpServletRequest request) throws IOException {
        System.out.println(image);
        Goods goods =new Goods();
        goods.setGDetails(request.getParameter("gDetails"));
        goods.setGName(request.getParameter("gName"));
        goods.setGtId(Integer.parseInt(request.getParameter("gtId")));
        goods.setGStore(Integer.parseInt(request.getParameter("gStore")));
        goods.setGPriceNew(Double.parseDouble(request.getParameter("gPriceNew")));
        goods.setGPriceOld(Double.parseDouble(request.getParameter("gPriceOld")));
        System.out.println(goods);
        goods.setImage(image);
        String newFileName = "";
        String fileName = goods.getImage().getOriginalFilename();

        //选择了文件
        if (fileName.length() > 0) {
            String realpath = request.getServletContext().getRealPath("files");
            System.out.println(realpath);
            //实现文件上传
            String fileType = fileName.substring(fileName.lastIndexOf('.'));
            //防止文件名重名
            newFileName = MyUtil.getStringID() + fileType;
            goods.setGPicture(newFileName);
            File targetFile = new File(realpath, newFileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //上传
            try {
                goods.getImage().transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(goods);
        int i = goodsService.addGoods(goods);
        if (i == 0) Result.error("msg", this.messageSource.getMessage("add.false", null, Locale.getDefault()));
        return Result.success();
    }

    //    修改商品信息
    @PostMapping("/updateGoods")
    public Result<?> updateGoods(@RequestParam("image") MultipartFile image, HttpServletRequest request) throws IOException {
        Goods goods =new Goods();
        goods.setGDetails(request.getParameter("gDetails"));
        goods.setGName(request.getParameter("gName"));
        goods.setGtId(Integer.parseInt(request.getParameter("gtId")));
        goods.setGStore(Integer.parseInt(request.getParameter("gStore")));
        goods.setGPriceNew(Double.parseDouble(request.getParameter("gPriceNew")));
        goods.setGPriceOld(Double.parseDouble(request.getParameter("gPriceOld")));
        System.out.println(goods);
        goods.setImage(image);

        String newFileName = "";
        String fileName = goods.getImage().getOriginalFilename();
        //选择了文件
        if (fileName.length() > 0) {
            String realpath = request.getServletContext().getRealPath("files");
            System.out.println(realpath);
            //实现文件上传
            String fileType = fileName.substring(fileName.lastIndexOf('.'));
            //防止文件名重名
            newFileName = MyUtil.getStringID() + fileType;
            goods.setGPicture(newFileName);
            File targetFile = new File(realpath, newFileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            //上传
            try {
                goods.getImage().transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int i = goodsService.updateGoods(goods);
        if (i == 0) Result.error("msg", this.messageSource.getMessage("add.false", null, Locale.getDefault()));
        return Result.success();
    }

    //   搜索
    @GetMapping("/search/{word}")
    public Result<?> getGoods(@PathVariable String word,
                              @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "20") Integer pageSize
                              ) {
        return Result.success(goodsService.selectGoodsByWord(word,pageNum,pageSize));
    }


    //    商品下架
    @PutMapping("/offShelf/{gId}")
    public Result<?> offShelf(@PathVariable int gId) {
        int i = goodsService.offShelf(gId);
        if (i == 0)
            Result.error("msg", this.messageSource.getMessage("offShelf.false", null, Locale.getDefault()));
        return Result.success();
    }

    //    商品上架
    @PutMapping("/onShelf/{gId}")
    public Result<?> onShelf(@PathVariable int gId) {
        int i = goodsService.onShelf(gId);
        if (i == 0)
            Result.error("msg", this.messageSource.getMessage("onShelf.false", null, Locale.getDefault()));
        return Result.success();
    }
}
