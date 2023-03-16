package com.meeting.mate_find.im.code.group.vo;

import com.meeting.mate_find.im.vo.BaseBean;

import java.io.Serializable;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2023/3/16 16:08
 */
public class GroupCreateResBean extends BaseBean implements Serializable {

    private Integer code;//响应状态，0登录成功，1登录失败
    private String msg;//响应信息


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public Byte code() {
        return 9;
    }
}