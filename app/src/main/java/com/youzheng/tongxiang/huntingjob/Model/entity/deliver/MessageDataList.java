package com.youzheng.tongxiang.huntingjob.Model.entity.deliver;

import java.util.List;

/**
 * Created by qiuweiyu on 2018/2/22.
 */

public class MessageDataList {
    private int count ;
    private int uid ;
    private int page ;
    private int rows ;
    private List<MessageDataBean> aList ;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public List<MessageDataBean> getaList() {
        return aList;
    }

    public void setaList(List<MessageDataBean> aList) {
        this.aList = aList;
    }
}
