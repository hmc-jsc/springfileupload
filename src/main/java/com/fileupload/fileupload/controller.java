package com.fileupload.fileupload;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
public class controller {
    @RequestMapping("up")   //设置访问路径
    @ResponseBody   //json响应体
    public String up(HttpServletRequest request, @RequestParam("file")MultipartFile file){  //设置请求体参数变量
        Map<String,String > respMap = new HashMap<>();  //创建map变量
        if (file.isEmpty()){    //判断文佳是否为空
            System.out.println("文件为空");
            respMap.put("msg","文件为空");  //添加至map返回数据
            String str = JSONObject.toJSONString(respMap);  //将map类型转换成json类型
            System.out.println(str);
            return str; //返回消息
        }
        String fileName = file.getOriginalFilename(); //获取文件名
        fileName = new Date().toString() +"-"+fileName;
        fileName = fileName.replaceAll(" ","-");
        fileName = fileName.replaceAll(":","-");
        fileName = fileName.substring(11);
        String suffixName = fileName.substring(fileName.indexOf("."));      //获取文件后缀
        String filePath  = "E:\\html test\\2021.02.16\\static\\";   //定义文件保存位置
        File dast = new File( filePath + fileName);
        System.out.println(dast);// 设置文件保存操作
        if (!dast.getParentFile().exists()){    //判断文件的父级别保存目录是否存在，若不存在，创建此路径目录
            dast.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dast);  //文佳保存操作
            respMap.put("msg","上传成功");  //添加至map返回数据
        }catch (IOException e) {        //处理异常
            respMap.put("msg","上传失败");      //添加至map返回数据
        }
        String str = JSONObject.toJSONString(respMap);  //将map类型转换成json类型
        System.out.println(str);
        return str; //返回消息
    }
}
