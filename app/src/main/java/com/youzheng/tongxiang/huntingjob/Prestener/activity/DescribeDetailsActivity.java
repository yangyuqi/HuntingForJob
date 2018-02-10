package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.Model.Event.EventModel;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.resume.UserResumeFivePageFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.resume.UserResumeFourPageFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.resume.UserResumeOnePageFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.resume.UserResumeSixPageFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.resume.UserResumeThreePageFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.resume.UserResumeTwoPageFragment;
import com.youzheng.tongxiang.huntingjob.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiuweiyu on 2018/2/9.
 */

public class DescribeDetailsActivity extends BaseActivity {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.rl_content)
    FrameLayout rlContent;
    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.describe_details_layout);
        ButterKnife.bind(this);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        textHeadTitle.setVisibility(View.GONE);
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(EventModel model) {
        FragmentTransaction transaction = fm.beginTransaction();
        switch (model) {
            case GO_BASE_INFO:
                transaction.replace(R.id.rl_content, new UserResumeOnePageFragment()).commit();
                break;
            case GO_WORK_INFO:
                transaction.replace(R.id.rl_content, new UserResumeTwoPageFragment()).commit();
                break;
            case GO_JY_INFO:
                transaction.replace(R.id.rl_content, new UserResumeFourPageFragment()).commit();
                break;
            case GO_STUDY_INFO:
                transaction.replace(R.id.rl_content, new UserResumeThreePageFragment()).commit();
                break;
            case GO_JINENG_INFO:
                transaction.replace(R.id.rl_content, new UserResumeFivePageFragment()).commit();
                break;
            case GO_PINGJIA_INFO:
                transaction.replace(R.id.rl_content, new UserResumeSixPageFragment()).commit();
                break;
        }
    }
}
