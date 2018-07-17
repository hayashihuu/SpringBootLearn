package com.syun.springboottest12.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.syun.springboottest12.dto.StuExtend;
import com.syun.springboottest12.dto.StudentEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @description: 对于Excel的导入导出处理
 * @program: springboottest12
 * @author: syun
 * @create: 2018-07-12 14:33
 */
public class ExcelHandle {



    @Test
    public void test01() throws IOException {

        List<StudentEntity> list = new ArrayList<>();
        list.add(new StudentEntity("1", "张一", 1, new Date(), new Date()));
        list.add(new StudentEntity("1", "张二", 1, new Date(), new Date()));
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生"),
                StudentEntity.class, list);
        FileOutputStream ow = new FileOutputStream("E:/others/123456.xlsx");
        workbook.write(ow);
    }

    @Test
    public void test02() {
        //设置从那时候
        ImportParams params = new ImportParams();
        params.setTitleRows(1);   //设置标题行数
        params.setHeadRows(1);    //设置表头行数
        List<StudentEntity> list = ExcelImportUtil.importExcel(
                new File("E:/others/easypoi.xls"),
                StudentEntity.class, params);
        list.forEach((p)-> System.out.println(p.toString()));
    }




    @Test
    public void test03() throws IOException {


        List<StudentEntity> list = new ArrayList<>();
        list.add(new StudentEntity("1", "张一", 1, new Date(), new Date(),"测试不加注解",new StuExtend("测试连接")));
        list.add(new StudentEntity("1", "张二", 1, new Date(), new Date(),"测试不加注解",new StuExtend("测试连接")));

        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("计算机一班学生","学生"),
                StudentEntity.class, list);
        FileOutputStream ow = new FileOutputStream("E:/others/两表连接.xls");
        workbook.write(ow);
    }


    @Test
    public void test04() {

        //设置从那时候
        ImportParams params = new ImportParams();
        params.setTitleRows(1);   //设置标题行数
        params.setHeadRows(1);    //设置表头行数
        List<StudentEntity> list = ExcelImportUtil.importExcel(
                new File("E:/others/两表连接.xls"),
                StudentEntity.class, params);
        List<StuExtend> stuExtendlist=ExcelImportUtil.importExcel(
                new File("E:/others/两表连接.xls"),
                StuExtend.class, params);
        list.forEach((p)-> System.out.println(p.toString()));
        stuExtendlist.forEach((p)-> System.out.println(p.toString()));
    }


    /**
     *
     * @param inputStream
     * @param C
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> ExcelToListBean(InputStream inputStream,Class C[]) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);   //设置标题行数
        params.setHeadRows(1);    //设置表头行数
        List<List<T>> lists = new ArrayList<>();
        for(Class c:C){
            try {
                lists.add(ExcelImportUtil.importExcel(inputStream, c, params));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return lists;
    }


    @Test
    public void testBean() throws FileNotFoundException {
        new File("E:/others/两表连接.xls");
        List<List<Object>> lists=ExcelToListBean(new FileInputStream("E:/others/两表连接.xls"), new Class[]{StudentEntity.class,StuExtend.class});
        lists.forEach((p)-> System.out.println(p.toString()));
    }




}
