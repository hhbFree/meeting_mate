package com.meeting.mate_find.im.vo;

import java.io.Serializable;


public abstract class BaseBean implements Serializable {
 private Integer tag=1;//固定值，标识的是一个协议类型，不同协议对应不同的值
 public abstract Byte code();//业务指令抽象方法

    public Integer getTag() {
        return tag;
    }

    public void setTag(Integer tag) {
        this.tag = tag;
    }
}