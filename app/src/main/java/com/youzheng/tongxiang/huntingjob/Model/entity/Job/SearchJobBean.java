package com.youzheng.tongxiang.huntingjob.Model.entity.Job;

import java.util.List;

/**
 * Created by qiuweiyu on 2018/2/20.
 */

public class SearchJobBean {
    private List<JobBeanDetails> recentList ;

    public List<JobBeanDetails> getRecentList() {
        return recentList;
    }

    public void setRecentList(List<JobBeanDetails> recentList) {
        this.recentList = recentList;
    }
}
