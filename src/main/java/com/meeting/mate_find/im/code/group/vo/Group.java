package com.meeting.mate_find.im.code.group.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2023/3/16 16:15
 */
public class Group implements Serializable {

    private Integer groupId;

    private String groupName;

    private List<GroupMember> members;

    public List<GroupMember> getMembers() {
        return members;
    }

    public void setMembers(List<GroupMember> members) {
        this.members = members;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
