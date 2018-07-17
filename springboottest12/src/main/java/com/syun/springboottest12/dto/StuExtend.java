package com.syun.springboottest12.dto;


import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/*
 * @description:
 * @program: springboottest12
 * @author: syun
 * @create: 2018-07-12 15:24
 */
@ExcelTarget("StuExtend")
public class StuExtend {

    @Excel(name="数据",isImportField = "true_absent")
    private String data;


    public StuExtend(){
        super();
    }



    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public StuExtend(String data) {

        this.data = data;
    }

    @Override
    public String toString() {
        return "StuExtend{" +
                "data='" + data + '\'' +
                '}';
    }
}
