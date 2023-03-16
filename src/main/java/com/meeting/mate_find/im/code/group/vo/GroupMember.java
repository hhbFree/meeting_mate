package com.meeting.mate_find.im.code.group.vo;

import io.netty.channel.Channel;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2023/3/16 16:16
 */
public class GroupMember {

    private Integer userid;

    private Integer groupId;

    private String groupName;

    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
