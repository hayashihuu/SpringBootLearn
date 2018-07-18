package com.syun.springboottest13.model;

import org.apache.solr.client.solrj.beans.Field;

import java.util.Date;

/*
 * @description:
 * @program: springboottest13
 * @author: syun
 * @create: 2018-07-18 09:47
 */
public class Article {
    // 文章id
    @Field
    private String id;


    // 文章分类id
    private String categoryId;

    // 作者id
    private String authorId;

    // 文章标题
    @Field
    private String name;

    // 文章内容
    @Field
    private String content;

    // 发布时间
    @Field
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", authorId='" + authorId + '\'' +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
