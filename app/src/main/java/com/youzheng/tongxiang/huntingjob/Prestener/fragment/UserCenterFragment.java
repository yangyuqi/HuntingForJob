package com.youzheng.tongxiang.huntingjob.Prestener.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.youzheng.tongxiang.huntingjob.Prestener.activity.LoginActivity;
import com.youzheng.tongxiang.huntingjob.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by qiuweiyu on 2018/2/7.
 */

public class UserCenterFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.rl_user)
    RelativeLayout rlUser;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup conuser_center_layouttainer, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_center_layout, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_user})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_user :
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
        }
    }
}
