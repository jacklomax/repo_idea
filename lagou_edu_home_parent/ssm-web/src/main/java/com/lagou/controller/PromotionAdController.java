package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.Result;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllAdByPage(PromotionAdVO promotionAdVO){
        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionAdByPage(promotionAdVO);

        return new ResponseResult(true,200,"广告分页查询成功",pageInfo);
    }

    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        if(file.isEmpty()){
            throw new RuntimeException();
        }
        String realPath = request.getServletContext().getRealPath("/");
        String substring = realPath.substring(0, realPath.indexOf("ssm-web"));
        String originalFilename = file.getOriginalFilename();
        String newFilename = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

        String uploadPath=substring+"upload\\";
        File filePath = new File(uploadPath, newFilename);
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
            System.out.println("创建目录"+filePath);
        }

        file.transferTo(filePath);
        Map<String, String> map=new HashMap<>();
        map.put("fileName",newFilename);
        map.put("filePath","http://localhost:8080/upload/"+newFilename);
        ResponseResult result = new ResponseResult(true, 200, "图片上传成功", map);
        return result;
    }


    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(int id,int status){
        promotionAdService.updatePromotionAdStatus(id,status);
        ResponseResult result = new ResponseResult(true, 200, "广告上下线成功",null);
        return result;
    }

}
