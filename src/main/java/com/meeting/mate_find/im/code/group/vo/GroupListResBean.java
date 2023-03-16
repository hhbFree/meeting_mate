package com.meeting.mate_find.im.code.group.vo;

import com.meeting.mate_find.im.vo.BaseBean;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: hbHao
 * @Date: 2023/3/16 16:10
 */
public class GroupListResBean extends BaseBean implements Serializable {

    private List<GroupInfo> lists;

    public List<GroupInfo> getLists() {
        return lists;
    }

    public void setLists(List<GroupInfo> lists) {
        this.lists = lists;
    }

    @Override
    public Byte code() {
        return 11;
    }
}
