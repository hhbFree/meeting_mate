package com.meeting.mate_find.im.code;

import com.meeting.mate_find.im.vo.*;

import java.util.HashMap;
import java.util.Map;

public class MapUtils {
    //1. 自定义指令
    private static Byte codeLoginReq = 1;
    private static Byte codeLoginRes = 2;
    private static Byte codeMsgReq = 3;
    private static Byte codeMsgRes = 4;
    private static Byte codeMsgRec = 5;
    //2. 自定义一个Map，专门管理指令和实体的关系
    private static Map<Byte, Class> map = new HashMap<>();

    //3. 初始化
    static {
        map.put(codeLoginReq, LoginReqBean.class);
        map.put(codeLoginRes, LoginResBean.class);
        map.put(codeMsgReq, MsgReqBean.class);
        map.put(codeMsgRes, MsgResBean.class);
        map.put(codeMsgRec, MsgRecBean.class);
    }

    //4. 根据指令获取对应的实体
    public static Class getBean(Byte code) {
        try {
            return map.get(code);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());

        }

    }

}