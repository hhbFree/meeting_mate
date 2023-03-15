package com.meeting.mate_find.im.vo;

import java.io.Serializable;

public class MsgRecBean extends BaseBean implements Serializable {
    private Integer fromuserid;//发送人ID
    private String msg;//消息

    public Byte code() {
        return 5;//业务指令
    }

    public Integer getFromuserid() {
        return fromuserid;
    }

    public void setFromuserid(Integer fromuserid) {
        this.fromuserid = fromuserid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}