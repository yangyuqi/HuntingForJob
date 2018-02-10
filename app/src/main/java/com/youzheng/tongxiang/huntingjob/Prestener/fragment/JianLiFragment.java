package com.youzheng.tongxiang.huntingjob.Prestener.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.Model.Event.EventModel;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.DescribeDetailsActivity;
import com.youzheng.tongxiang.huntingjob.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by qiuweiyu on 2018/2/7.
 */

public class JianLiFragment extends BaseFragment {

    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    Unbinder unbinder;
    @BindView(R.id.rl_desc)
    RelativeLayout rlDesc;
    @BindView(R.id.rl_jinyan)
    RelativeLayout rlJinyan;
    @BindView(R.id.rl_study)
    RelativeLayout rlStudy;
    @BindView(R.id.rl_jineng)
    RelativeLayout rlJineng;
    @BindView(R.id.rl_pingjia)
    RelativeLayout rlPingjia;

    private String which;
    @BindView(R.id.rl_work)
    RelativeLayout rlWork;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jianli_layout, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        textHeadTitle.setText("简历");
    }

    @OnClick({R.id.rl_desc, R.id.rl_work, R.id.rl_jinyan,R.id.rl_study,R.id.rl_jineng,R.id.rl_pingjia})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.rl_desc:
                which = "1";
                startActivity(new Intent(mContext, DescribeDetailsActivity.class));
                break;
            case R.id.rl_work:
                which = "2";
                startActivity(new Intent(mContext, DescribeDetailsActivity.class));
                break;
            case R.id.rl_jinyan:
                which = "3";
                startActivity(new Intent(mContext, DescribeDetailsActivity.class));
                break;
            case R.id.rl_study:
                which = "4";
                startActivity(new Intent(mContext, DescribeDetailsActivity.class));
                break;
            case R.id.rl_jineng:
                which = "5";
                startActivity(new Intent(mContext,DescribeDetailsActivity.class));
                break;
            case R.id.rl_pingjia:
                which="6";
                startActivity(new Intent(mContext,DescribeDetailsActivity.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (which != null) {
            switch (which) {
                case "1":
                    EventBus.getDefault().post(EventModel.GO_BASE_INFO);
                    break;
                case "2":
                    EventBus.getDefault().post(EventModel.GO_WORK_INFO);
                    break;
                case "3":
                    EventBus.getDefault().post(EventModel.GO_JY_INFO);
                    break;
                case "4":
                    EventBus.getDefault().post(EventModel.GO_STUDY_INFO);
                    break;
                case "5":
                    EventBus.getDefault().post(EventModel.GO_JINENG_INFO);
                    break;
                case "6":
                    EventBus.getDefault().post(EventModel.GO_PINGJIA_INFO);
                    break;

            }
        }
    }
}
