package com.syun.springboottest12.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;

import java.util.Date;

/*
 * @description:
 * @program: springboottest12
 * @author: syun
 * @create: 2018-07-12 14:32
 */
public class StudentEntity {
    /**
     * id
     */
    private String        id;
    /**
     * 学生姓名
     */
    @Excel(name = "学生姓名", height = 20, width = 30, isImportField = "true_st")
    private String name;
    /**
     * 学生性别
     */
    @Excel(name = "学生性别", replace = {"男_1", "女_2"}, suffix = "生", isImportField = "true_st")
    private int sex;

    @Excel(name = "出生日期", databaseFormat = "yyyyMMddHHmmss", format = "yyyy-MM-dd", isImportField = "true_st", width = 20)
    private Date birthday;

    @Excel(name = "进校日期", databaseFormat = "yyyyMMddHHmmss", width = 20,format = "yyyy-MM-dd")
    private Date registrationDate;

    /**
     * 测试不加注解
     * 测试结果为：不导出此字段
     */
    private String judge;

    /**
     * 实体类连接
     */
    @ExcelEntity(id = "absent")
    private StuExtend stuExtend;

    /**
     * 某些版本使用必须主动创建无参构造器
     */
    public StudentEntity(){
        super();
    }


    public StudentEntity(String id, String name, int sex, Date birthday, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.registrationDate = registrationDate;
    }

    public StudentEntity(String id, String name, int sex, Date birthday, Date registrationDate, String judge, StuExtend stuExtend) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.registrationDate = registrationDate;
        this.judge = judge;
        this.stuExtend = stuExtend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public StudentEntity(String id, String name, int sex, Date birthday, Date registrationDate, StuExtend stuExtend) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.birthday = birthday;
        this.registrationDate = registrationDate;
        this.stuExtend = stuExtend;
    }

    public StuExtend getStuExtend() {
        return stuExtend;
    }

    public void setStuExtend(StuExtend stuExtend) {
        this.stuExtend = stuExtend;
    }

    public String getJudge() {
        return judge;
    }

    public void setJudge(String judge) {
        this.judge = judge;
    }

    @Override
    public String toString() {
        return "StudentEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", registrationDate=" + registrationDate +
                ", stuExtend=" + stuExtend +
                '}';
    }
}
