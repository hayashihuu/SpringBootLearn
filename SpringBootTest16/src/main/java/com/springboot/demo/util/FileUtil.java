package com.springboot.demo.util;

import org.junit.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/*
 * @description:文件处理
 * @program: search-engine
 * @author: syun
 * @create: 2018-07-27 16:14
 */
public class FileUtil {
    /**
     * 在basePath下保存上传的文件夹
     * @param basePath
     * @param files
     */
    public static void saveMultiFile(String basePath, MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return;
        }
        if (basePath.endsWith("/")) {
            basePath = basePath.substring(0, basePath.length() - 1);
        }
        for (MultipartFile file : files) {
            String filePath = basePath + "/" + file.getOriginalFilename();
            makeDir(filePath);
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 确保目录存在，不存在则创建
     * @param filePath
     */
    private static void makeDir(String filePath) {
        if (filePath.lastIndexOf('/') > 0) {
            String dirPath = filePath.substring(0, filePath.lastIndexOf('/'));
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        }
    }


    /**
     * 写入临时文件
     * @param file
     * @return 存入文件的绝对路径
     * @throws IOException
     */
    public static String fileWrite(MultipartFile file) throws IOException {

        File tempfile = new File("temp.zip");
        String dest = null;
        try {
            InputStream is = file.getInputStream();
            FileOutputStream fos = new FileOutputStream(tempfile);
            byte[] date = new byte[1024];
            int len = 0;
            while(-1!=(len=is.read(date))){
                fos.write(date,0,date.length);
            }
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempfile.getAbsolutePath();
    }


    /**
     * 文件写入指定位置,若存在则覆盖
     * @param file
     * @param path
     * @return
     * @throws IOException
     */
    public static String fileWrite(MultipartFile file, String path) throws IOException {

        System.out.println(file.getOriginalFilename());
        System.out.println(file.getName());
        File temp = new File(path+"/"+file.getOriginalFilename());
        if(temp.exists()){
            temp.delete();
        }
        InputStream is = file.getInputStream();
        FileOutputStream fos = new FileOutputStream(temp);
        byte[] date = new byte[1024];
        int len = 0;
        while(-1!=(len=is.read(date))){
            fos.write(date,0,date.length);
        }
        fos.flush();
        fos.close();
        return temp.getAbsolutePath();
    }


    /**
     * 解压文件
     * @param zipFile
     * @param descDir
     * @return 解压的文件夹的绝对路径
     * @throws IOException
     */
    public static String unZipFiles(File zipFile, String descDir) throws IOException {

        System.out.println("zipFile.getParent()："+ zipFile.getParent());

        ZipFile zip = new ZipFile(zipFile,Charset.forName("GBK"));//解决中文文件夹乱码
        String name = zip.getName().substring(zip.getName().lastIndexOf('\\')+1, zip.getName().lastIndexOf('.'));
        System.out.println("name:" + name);
        String outPath;
        File pathFile = new File(descDir+name);

//        如果文件夹已存在则删除
        if(pathFile.exists()){
            delFolder(pathFile.getAbsolutePath());
        }
        pathFile.mkdirs();

        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            System.out.println(zipEntryName);
            InputStream in = zip.getInputStream(entry);
            outPath = (descDir + name +"/"+ zipEntryName).replaceAll("\\*", "/");
            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            // 输出文件路径信息
//          System.out.println(outPath);

            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        System.out.println("******************解压完毕********************");
        return pathFile.getAbsolutePath();
    }

    /**
     * 删除文件夹
     * @param folderPath
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     * @param path
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }


    public static void unzip(String zipFilePath, String destDir)
    {
        System.setProperty("sun.zip.encoding", System.getProperty("sun.jnu.encoding")); //防止文件名中有中文时出错
        //System.out.println(System.getProperty("sun.zip.encoding")); //ZIP编码方式
        //System.out.println(System.getProperty("sun.jnu.encoding")); //当前文件编码方式
        //System.out.println(System.getProperty("file.encoding")); //这个是当前文件内容编码方式

        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if (!dir.exists()) dir.mkdirs();

        FileInputStream fis;
        // buffer for read and write data to file
        byte[] buffer = new byte[1024];
        try
        {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while (ze != null)
            {
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);

                //System.out.println("Unzipping to " + newFile.getAbsolutePath());
                // create directories for sub directories in zip
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0)
                {
                    fos.write(buffer, 0, len);
                }
                fos.close();
                // close this ZipEntry
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            // close last ZipEntry
            zis.closeEntry();
            zis.close();
            fis.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * 解压文件
     * @param zipFile
     * @param descDir
     * @return 解压的文件夹的绝对路径
     * @throws IOException
     */
    public static String unZipFiles3(File zipFile, String descDir) throws IOException {

        ZipFile zip = new ZipFile(zipFile,Charset.forName("GBK"));//解决中文文件夹乱码
        String name = zip.getName().substring(zip.getName().lastIndexOf('\\')+1, zip.getName().lastIndexOf('.'));
        System.out.println("name:" + name);
        String outPath;
        File pathFile = new File(descDir+name);

//        如果文件夹已存在则删除
        if(pathFile.exists()){
            delFolder(pathFile.getAbsolutePath());
        }
        pathFile.mkdirs();

        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            System.out.println(zipEntryName);
            InputStream in = zip.getInputStream(entry);
            outPath = (descDir + name +"/"+ zipEntryName).replaceAll("\\*", "/");
            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            // 输出文件路径信息
//          System.out.println(outPath);

            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        System.out.println("******************解压完毕********************");
        return pathFile.getAbsolutePath();
    }
    @Test
    public void test() throws IOException {
        System.out.println(unZipFiles(new File("D:\\tmp\\demand_conf.zip"),new File("D:\\tmp\\demand_conf.zip").getParent()+"/"));
    }

//    public static void main(String[] args)
//    {
//        String zipFilePath = "D:\\D\\dll.zip";
//
//        String destDir = "D:\\D\\dll_zip";
//
//        UnZip.unzip(zipFilePath, destDir);
//    }

}
