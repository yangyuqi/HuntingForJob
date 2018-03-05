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
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.BaseActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.FindPwdActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.LoginActivity;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.SharedPreferencesUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;
import com.youzheng.tongxiang.huntingjob.UI.dialog.DeleteDialog;
import com.youzheng.tongxiang.huntingjob.UI.dialog.DeleteDialogInterface;

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
                startActivity(new Intent(mContext, FindPwdActivity.class));
                break;
            case R.id.rl_update_version:
                break;
            case R.id.rl_share:
                break;
            case R.id.rl_about_us:
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
                                        startActivity(new Intent(mContext, LoginActivity.class));
                                    }
                                }
                            });
                        }
                    }
                });
                deleteDialog.show();
                break;
        }
    }
}
