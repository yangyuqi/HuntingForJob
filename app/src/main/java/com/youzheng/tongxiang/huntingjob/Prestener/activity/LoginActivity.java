package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Request;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.entity.user.UserBean;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.SharedPreferencesUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;
import com.youzheng.tongxiang.huntingjob.UI.dialog.RegisterSuccessDialog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

/**
 * Created by qiuweiyu on 2018/2/8.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_pwd)
    EditText tvPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_find_pwd)
    TextView tvFindPwd;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_register,R.id.tv_pwd,R.id.btn_login,R.id.tv_find_pwd})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.tv_register :
                startActivity(new Intent(mContext,RegisterActivity.class));
                break;
            case R.id.tv_find_pwd :
                startActivity(new Intent(mContext,FindPwdActivity.class));
                break;
            case R.id.btn_login :
                if (tvPhone.getText().toString().equals("")){
                    showToast("请输入正确手机号");
                    return;
                }

                if (tvPwd.getText().toString().equals("")){
                    showToast("密码不能为空");
                    return;
                }

                RxPermissions permissions = new RxPermissions(LoginActivity.this);
                permissions.request(Manifest.permission.READ_PHONE_STATE).subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                            if (aBoolean){
                                final Map<String,Object> map = new HashMap<>();
                                map.put("username",tvPhone.getText().toString());
                                map.put("password",PublicUtils.md5Password(tvPwd.getText().toString()));
                                map.put("userType","0");
                                map.put("deviceType","android");
                                map.put("deviceNo",PublicUtils.getPhotoImEi(mContext));
                                OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.LOGIN_URL, new OkHttpClientManager.StringCallback() {
                                    @Override
                                    public void onFailure(Request request, IOException e) {

                                    }

                                    @Override
                                    public void onResponse(String response) {
                                        BaseEntity entity = gson.fromJson(response,BaseEntity.class);
                                        if (entity.getCode().equals(PublicUtils.SUCCESS)){
                                            UserBean bean = gson.fromJson(gson.toJson(entity.getData()),UserBean.class);
                                            SharedPreferencesUtils.setParam(mContext,SharedPreferencesUtils.access_token,bean.getAccess_token());
                                            SharedPreferencesUtils.setParam(mContext,SharedPreferencesUtils.uid,bean.getUid());
                                            SharedPreferencesUtils.setParam(mContext,SharedPreferencesUtils.rid,bean.getRid());
                                            finish();
                                        }else {
                                            showToast(entity.getMsg());
                                        }
                                    }
                                });
                            }
                    }
                });

                break;
        }
    }
}
