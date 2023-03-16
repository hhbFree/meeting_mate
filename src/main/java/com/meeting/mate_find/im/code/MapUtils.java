package com.meeting.mate_find.im.code;

import com.meeting.mate_find.im.code.group.vo.*;
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
    private static Byte codeAddRes = 6;
    private static Byte codeAddRec = 7;
    private static Byte codeCreateRes = 8;
    private static Byte codeCreateRec = 9;
    private static Byte codeListRes = 10;
    private static Byte codeListRec = 11;
    private static Byte codeMemberRes = 12;
    private static Byte codeMemberRec = 13;
    private static Byte codeQuitRes = 14;
    private static Byte codeQuitRec = 15;
    private static Byte codeGroupMsgRec = 16;
    private static Byte codeGroupSendMsgRes = 17;
    private static Byte codeGroupSendMsgRec = 18;
    //2. 自定义一个Map，专门管理指令和实体的关系
    private static Map<Byte, Class> map = new HashMap<>();

    //3. 初始化
    static {
        map.put(codeLoginReq, LoginReqBean.class);
        map.put(codeLoginRes, LoginResBean.class);
        map.put(codeMsgReq, MsgReqBean.class);
        map.put(codeMsgRes, MsgResBean.class);
        map.put(codeMsgRec, MsgRecBean.class);
        map.put(codeAddRes, GroupAddReqBean.class);
        map.put(codeAddRec, GroupAddResBean.class);
        map.put(codeCreateRes, GroupCreateReqBean.class);
        map.put(codeCreateRec, GroupCreateResBean.class);



        map.put(codeListRes, GroupListReqBean.class);
        map.put(codeListRec, GroupListResBean.class);
        map.put(codeMemberRes, GroupMemberReqBean.class);
        map.put(codeMemberRec, GroupMemberResBean.class);
        map.put(codeQuitRes, GroupQuitReqBean.class);
        map.put(codeQuitRec, GroupQuitResBean.class);
        map.put(codeGroupMsgRec, GroupRecMsgBean.class);
        map.put(codeGroupSendMsgRes, GroupSendMsgReqBean.class);
        map.put(codeGroupSendMsgRec, GroupSendMsgResBean.class);
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