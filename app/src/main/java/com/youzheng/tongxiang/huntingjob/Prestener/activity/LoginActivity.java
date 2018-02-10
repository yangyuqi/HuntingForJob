package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        }
    }
}
