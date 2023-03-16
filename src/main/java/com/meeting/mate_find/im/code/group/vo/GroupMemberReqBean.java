package com.meeting.mate_find.im.code.group.vo;

import com.meeting.mate_find.im.vo.BaseBean;

import java.io.Serializable;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2023/3/16 15:53
 */
public class GroupMemberReqBean extends BaseBean implements Serializable {

    private Integer userId;

    private Integer groupId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    @Override
    public Byte code() {
        return 12;
    }
}
