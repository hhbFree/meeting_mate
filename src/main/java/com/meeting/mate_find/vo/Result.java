package com.meeting.mate_find.vo;

/**
 * @Description: return result
 * @Author: hbHao
 * @Date: 2023/3/10 14:40
 */
public class Result {

    private int code;

    private String msg;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
