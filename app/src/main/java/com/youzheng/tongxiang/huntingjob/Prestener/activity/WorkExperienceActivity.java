package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.Model.Event.SelectJianLiBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.EducationBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.EducitionBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.ProjectBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.WorkExperenceBean;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.resume.UserResumeOnePageFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.resume.UserResumeTwoPageFragment;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.CommonAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.ViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiuweiyu on 2018/2/13.
 */

public class WorkExperienceActivity extends BaseActivity {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    @BindView(R.id.ls)
    ListView ls;
    @BindView(R.id.textHeadNext)
    TextView textHeadNext;

    private SelectJianLiBean bean ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_experience_layout);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        textHeadNext.setText("添加");
        textHeadNext.setVisibility(View.VISIBLE);
        textHeadTitle.setVisibility(View.GONE);
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        textHeadNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext,DescribeDetailsActivity.class));
                finish();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bean!=null){
            EventBus.getDefault().post(bean);
        }
    }

    @Subscribe
    public void onEvent(SelectJianLiBean liBean) {
        if (liBean!=null){
            bean = liBean ;
            if (liBean.getType().equals("2")) {

                CommonAdapter<WorkExperenceBean> adapter = new CommonAdapter<WorkExperenceBean>(mContext,liBean.getJi().getExperienceList(),R.layout.work_experence_ls_item) {
                    @Override
                    public void convert(ViewHolder helper, final WorkExperenceBean item) {
                        helper.setText(R.id.tv_co,item.getCompany());
                        helper.setText(R.id.tv_experice,item.getStart_time()+"至"+item.getEnd_time()+"/"+item.getPosition());
                        helper.getView(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bean.setExpe_id(item.getId());
                                startActivity(new Intent(mContext,DescribeDetailsActivity.class));
                                finish();
                            }
                        });
                    }
                };
                ls.setAdapter(adapter);
            }else if (liBean.getType().equals("3")){
                CommonAdapter<ProjectBean> adapter = new CommonAdapter<ProjectBean>(mContext,liBean.getJi().getProjectList(),R.layout.work_experence_ls_item) {
                    @Override
                    public void convert(ViewHolder helper, final ProjectBean item) {
                        helper.setText(R.id.tv_co,item.getName());
                        helper.setText(R.id.tv_experice,item.getStart_time()+"至"+item.getEnd_time());
                        helper.getView(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bean.setProject_id(item.getId());
                                startActivity(new Intent(mContext,DescribeDetailsActivity.class));
                                finish();
                            }
                        });
                    }
                };
                ls.setAdapter(adapter);
            }else if (liBean.getType().equals("4")){
                CommonAdapter<EducitionBean> adapter = new CommonAdapter<EducitionBean>(mContext,liBean.getJi().getEducationList(),R.layout.work_experence_ls_item) {
                    @Override
                    public void convert(ViewHolder helper,final EducitionBean item) {
                        helper.setText(R.id.tv_co,item.getSchool());
                        helper.setText(R.id.tv_experice,item.getStarttime()+"至"+item.getEndtime());
                        helper.getView(R.id.tv_edit).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bean.setEducition_id(item.getId());
                                startActivity(new Intent(mContext,DescribeDetailsActivity.class));
                                finish();
                            }
                        });
                    }
                };
                ls.setAdapter(adapter);
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
