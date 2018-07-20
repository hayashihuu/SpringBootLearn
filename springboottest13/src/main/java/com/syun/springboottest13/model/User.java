package com.syun.springboottest13.model;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

/*
 * @description:
 * @program: springboottest13
 * @author: syun
 * @create: 2018-07-18 16:42
 */
public class User {


    @Field
    private String name;

    @Field
    private String id;

    @Field
    private String province;

    @Field
    private String city;

    @Field
    private String company;

    @Field
    private String total;

    @Field
    private String flg;

    @Field
    private Date modifyTime;

    @Field
    private boolean is_deleted;

    public User() {
    }

    public User(String name, String id, String province, String city, String company, String total, String flg, Date modifyTime, boolean is_deleted) {
        this.name = name;
        this.id = id;
        this.province = province;
        this.city = city;
        this.company = company;
        this.total = total;
        this.flg = flg;
        this.modifyTime = modifyTime;
        this.is_deleted = is_deleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", company='" + company + '\'' +
                ", total=" + total +
                ", flg='" + flg + '\'' +
                ", modifyTime=" + modifyTime +
                ", is_deleted=" + is_deleted +
                '}';
    }
}
