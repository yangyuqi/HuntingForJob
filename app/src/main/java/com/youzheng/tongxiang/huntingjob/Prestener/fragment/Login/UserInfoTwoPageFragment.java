package com.youzheng.tongxiang.huntingjob.Prestener.fragment.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.Prestener.activity.UserResumeActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.BaseFragment;
import com.youzheng.tongxiang.huntingjob.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by qiuweiyu on 2018/2/8.
 */

public class UserInfoTwoPageFragment extends BaseFragment {
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.btn_login)
    Button btnLogin;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info_two_page_layout, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login :
                startActivity(new Intent(mContext, UserResumeActivity.class));
                break;
        }
    }
}
