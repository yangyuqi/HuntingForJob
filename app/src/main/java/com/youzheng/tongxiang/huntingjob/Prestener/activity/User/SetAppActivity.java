package com.youzheng.tongxiang.huntingjob.Prestener.activity.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.Event.EventModel;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.BaseActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.FindPwdActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.H5Activity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.LoginActivity;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.SharedPreferencesUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;
import com.youzheng.tongxiang.huntingjob.UI.dialog.DeleteDialog;
import com.youzheng.tongxiang.huntingjob.UI.dialog.DeleteDialogInterface;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qiuweiyu on 2018/2/11.
 */

public class SetAppActivity extends BaseActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    @BindView(R.id.switvh)
    Switch switvh;
    @BindView(R.id.rl_change_pwd)
    RelativeLayout rlChangePwd;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.rl_update_version)
    RelativeLayout rlUpdateVersion;
    @BindView(R.id.rl_share)
    RelativeLayout rlShare;
    @BindView(R.id.rl_about_us)
    RelativeLayout rlAboutUs;
    @BindView(R.id.rl_out_login)
    RelativeLayout rlOutLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_app_layout);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        textHeadTitle.setText("设置");
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.rl_change_pwd,R.id.rl_update_version,R.id.rl_share,R.id.rl_about_us,R.id.rl_out_login})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.rl_change_pwd:
                Intent intent = new Intent(mContext, FindPwdActivity.class);
                intent.putExtra("type","1");
                startActivity(intent);
                break;
            case R.id.rl_update_version:
                break;
            case R.id.rl_share:
                break;

            case R.id.rl_out_login:

                DeleteDialog deleteDialog = new DeleteDialog(mContext,"提示","是否退出登录","确定");
                deleteDialog.show();
                deleteDialog.OnDeleteBtn(new DeleteDialogInterface() {
                    @Override
                    public void isDelete(boolean isdelete) {
                        if (!accessToken.equals("")){
                            Map<String,Object> map = new HashMap<>();
                            map.put("access_token",accessToken);
                            OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.LOGIN_OUT, new OkHttpClientManager.StringCallback() {
                                @Override
                                public void onFailure(Request request, IOException e) {

                                }

                                @Override
                                public void onResponse(String response) {
                                    BaseEntity entity = gson.fromJson(response,BaseEntity.class);
                                    if (entity.getCode().equals(PublicUtils.SUCCESS)){
                                        finish();
                                        SharedPreferencesUtils.clear(mContext);
                                        SharedPreferencesUtils.setParam(mContext,SharedPreferencesUtils.fiet,1);
                                        startActivity(new Intent(mContext, LoginActivity.class));
                                        EventBus.getDefault().post(EventModel.GO_UPPER_PAGE);
                                    }
                                }
                            });
                        }
                    }
                });
                deleteDialog.show();
                break;
            case R.id.rl_about_us :
                Intent intent1 = new Intent(mContext, H5Activity.class);
                intent1.putExtra("title","关于我们");
                intent1.putExtra("content","优聘人才网（www.youpin.com \n" +
                        "\n" +
                        "），是优正管理咨询股份有限公司旗下，于2011年正式上线。作为实现企业、猎头和职业经理人三方互动的平台，猎聘网始终专注于打造以经理人个人用户体验为核心，全面颠覆传统网络招聘以企业为核心的广告发布。截至2016年6月，猎聘网拥有超过3000万的注册会员，已服务超过50万家优质企业。目前，有超过25万名猎头在猎聘网上寻找核心岗位的候选人。猎聘网的业务遍及中国北京、上海、广州、深圳、天津、大连、杭州、南京、武汉、厦门、成都、青岛、重庆、郑州等十余个城市。");
                startActivity(intent1);
                break;
        }
    }
}
