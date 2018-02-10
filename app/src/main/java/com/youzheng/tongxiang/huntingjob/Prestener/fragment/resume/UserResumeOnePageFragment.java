package com.youzheng.tongxiang.huntingjob.Prestener.fragment.resume;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.youzheng.tongxiang.huntingjob.Prestener.activity.UserResumeActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.BaseFragment;
import com.youzheng.tongxiang.huntingjob.R;

/**
 * Created by qiuweiyu on 2018/2/8.
 */

public class UserResumeOnePageFragment extends BaseFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_resume_one_page_layout,null);
        return view;
    }
}
