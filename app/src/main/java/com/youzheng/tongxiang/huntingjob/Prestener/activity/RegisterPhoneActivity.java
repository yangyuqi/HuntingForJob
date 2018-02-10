package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.dialog.RegisterSuccessDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qiuweiyu on 2018/2/8.
 */

public class RegisterPhoneActivity extends BaseActivity {

    @BindView(R.id.tv_phone)
    EditText tvPhone;
    @BindView(R.id.tv_pwd)
    EditText tvPwd;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.tv_code)
    EditText tvCode;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_find_pwd)
    TextView tvFindPwd;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    @BindView(R.id.layout_header)
    RelativeLayout layoutHeader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_phone_layout);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        textHeadTitle.setText("注册");
        btnBack.setVisibility(View.VISIBLE);
        layoutHeader.setBackgroundResource(R.drawable.main_color);
    }

    @OnClick({R.id.btnBack,R.id.btn_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnBack : finish();
                break;
            case R.id.btn_login :
                final RegisterSuccessDialog dialog = new RegisterSuccessDialog(mContext);
                dialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        startActivity(new Intent(mContext,FillInfoActivity.class));
                    }
                },1000);

                break;
        }
    }
}
