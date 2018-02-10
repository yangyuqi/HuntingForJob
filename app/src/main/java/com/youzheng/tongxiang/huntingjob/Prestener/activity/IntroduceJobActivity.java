package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qiuweiyu on 2018/2/9.
 */

public class IntroduceJobActivity extends BaseActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    @BindView(R.id.rl_see_more)
    RelativeLayout rlSeeMore;
    @BindView(R.id.ls_details)
    WebView lsDetails;
    @BindView(R.id.iv_see_co)
    ImageView ivSeeCo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce_job_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        textHeadTitle.setText("职位信息");
        btnBack.setVisibility(View.VISIBLE);
    }

    @OnClick({R.id.btnBack,R.id.iv_see_co})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.iv_see_co :
                startActivity(new Intent(mContext,IntroduceCoActivity.class));
                break;
        }
    }
}
