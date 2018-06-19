package com.syun.springboottest07.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Controller
public class FileuploadController {

    /**
     * 文件的上传与写入
     * @param imageNameIndex
     * @param url
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public String submit(String imageNameIndex, String url, MultipartFile file)
            throws Exception {

        //这里就可以获取里面的上传过来的数据了
        System.out.println("test success");
        System.out.println(file.toString());
        System.out.println("name: " + file.getName());
        System.out.println("size " + file.getSize());
        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());

        InputStream is = file.getInputStream();
        File file1 = new File("E:/others/"+file.getOriginalFilename());
        if(!file1.exists()){
            file1.createNewFile();
        }
        FileOutputStream fos=new FileOutputStream(file1);
        byte[] date = new byte[1024];
        int len=0;
        while(-1!=(len=is.read(date))){
            fos.write(date,0,len);
        }
        fos.flush();
        fos.close();
        is.close();
        //做一些存库操作，以及返回的数据
        return "success";
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
