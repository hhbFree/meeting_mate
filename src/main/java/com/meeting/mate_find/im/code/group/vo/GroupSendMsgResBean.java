package com.meeting.mate_find.im.code.group.vo;

import com.meeting.mate_find.im.vo.BaseBean;

import java.io.Serializable;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2023/3/16 16:13
 */
public class GroupSendMsgResBean extends BaseBean implements Serializable {

    private String msg;//响应信息

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Byte code() {
        return 18;
    }
}
