package com.meeting.mate_find.im.code.group.vo;

import com.meeting.mate_find.im.vo.BaseBean;

import java.io.Serializable;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2023/3/16 15:53
 */
public class GroupSendMsgReqBean extends BaseBean implements Serializable {

    private Integer fromuserid;

    private Integer togroupid;

    private String msg;

    public Integer getFromuserid() {
        return fromuserid;
    }

    public void setFromuserid(Integer fromuserid) {
        this.fromuserid = fromuserid;
    }

    public Integer getTogroupid() {
        return togroupid;
    }

    public void setTogroupid(Integer togroupid) {
        this.togroupid = togroupid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Byte code() {
        return 17;
    }
}
