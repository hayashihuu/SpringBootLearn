package com.syun.springboottest12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/*
 * @description: 实现文件的下载
 * @program: springboottest12
 * @author: syun
 * @create: 2018-07-12 09:44
 */
@Controller
public class FileDownLoadController {


    /**
     * 实现文件的下载
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/download")
    @ResponseBody
    public boolean fileDownLoad(HttpServletRequest request, HttpServletResponse response){
        String string = "123" + "\n" + "456";


        // 设置强制下载不打开
        response.setContentType("application/force-download");
        // 设置文件名
        response.addHeader(
                "Content-Disposition",
                "attachment;fileName=" + "test");
        OutputStream os = null;

        try {
            os = response.getOutputStream();
            os.write(string.getBytes());  //写入
            os.flush();
        } catch (IOException e) {
            System.out.println("获取路径失败");
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
//        OutputStream fos = new OutputStream();
    }



}
