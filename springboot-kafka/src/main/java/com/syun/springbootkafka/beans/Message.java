package com.syun.springbootkafka.beans;

import lombok.Data;

import java.util.Date;

/*
 * @description:
 * @program: springboot-kafka
 * @author: syun
 * @create: 2018-08-14 13:53
 */
@Data
public class Message {
    private Long id;    //id

    private String msg; //消息

    private Date sendTime;  //时间戳

    public Message(Long id, String msg, Date sendTime) {
        this.id = id;
        this.msg = msg;
        this.sendTime = sendTime;
    }

    public Message() {

    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
