package com.youzheng.tongxiang.huntingjob.Prestener.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.entity.user.UserInfoSeeBean;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.LoginActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.User.BandPhoneActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.User.CollectActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.User.DeliverMessageActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.User.InviteActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.User.SecretActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.User.SetAppActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.User.SpeakActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.User.UserCenterActivity;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;
import com.youzheng.tongxiang.huntingjob.UI.Widget.CircleImageView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    @BindView(R.id.rl_speak)
    RelativeLayout rlSpeak;
    @BindView(R.id.rl_collect)
    RelativeLayout rlCollect;
    @BindView(R.id.rl_user_info)
    LinearLayout rlUserInfo;
    @BindView(R.id.rl_set)
    RelativeLayout rlSet;
    @BindView(R.id.rl_secret)
    RelativeLayout rlSecret;
    @BindView(R.id.rl_bang_phone)
    RelativeLayout rlBangPhone;
    @BindView(R.id.rl_deliver)
    LinearLayout rlDeliver;
    @BindView(R.id.rl_invite)
    LinearLayout rlInvite;
    @BindView(R.id.rl_help)
    RelativeLayout rlHelp;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_show1)
    LinearLayout llShow1;
    @BindView(R.id.ll_show2)
    LinearLayout llShow2;
    @BindView(R.id.tv_personal)
    TextView tvPersonal;
    @BindView(R.id.iv_icon)
    CircleImageView ivIcon;


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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        if (!accessToken.equals("")) {
            llShow1.setVisibility(View.VISIBLE);
            llShow2.setVisibility(View.GONE);
            Map<String, Object> map = new HashMap<>();
            map.put("accessToken", accessToken);
            OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.USER_INFO_SEE, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                    if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                        UserInfoSeeBean userInfoSeeBean = gson.fromJson(gson.toJson(entity.getData()), UserInfoSeeBean.class);
                        if (userInfoSeeBean.getUser().getPhoto()!=null) {
                            Glide.with(mContext).load(userInfoSeeBean.getUser().getPhoto()).into(ivIcon);
                        }
                        tvName.setText(userInfoSeeBean.getUser().getUsername());
                        tvPersonal.setText(userInfoSeeBean.getUser().getPersonal());
                    }
                }
            });
        } else {
            llShow1.setVisibility(View.GONE);
            llShow2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_user, R.id.rl_speak, R.id.rl_collect, R.id.rl_user_info, R.id.rl_set, R.id.rl_secret, R.id.rl_bang_phone, R.id.rl_deliver, R.id.rl_invite, R.id.rl_help, R.id.ll_show2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_show2:
                startActivity(new Intent(mContext, LoginActivity.class));
                break;
            case R.id.rl_speak:
                startActivity(new Intent(mContext, SpeakActivity.class));
                break;
            case R.id.rl_collect:
                startActivity(new Intent(mContext, CollectActivity.class));
                break;
            case R.id.rl_user_info:
                startActivity(new Intent(mContext, UserCenterActivity.class));
                break;
            case R.id.rl_set:
                startActivity(new Intent(mContext, SetAppActivity.class));
                break;
            case R.id.rl_secret:
                startActivity(new Intent(mContext, SecretActivity.class));
                break;
            case R.id.rl_bang_phone:
                startActivity(new Intent(mContext, BandPhoneActivity.class));
                break;
            case R.id.rl_deliver:
                startActivity(new Intent(mContext, DeliverMessageActivity.class));
                break;
            case R.id.rl_invite:
                startActivity(new Intent(mContext, InviteActivity.class));
                break;
            case R.id.rl_help:
                showToast("该功能暂未开放");
                break;
        }
    }
}
