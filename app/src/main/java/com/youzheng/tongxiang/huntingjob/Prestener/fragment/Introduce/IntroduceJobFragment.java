package com.youzheng.tongxiang.huntingjob.Prestener.fragment.Introduce;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.CoBeanDetails;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.IntroduceCoJobBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.JobBeanDetails;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.IntroduceCoActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.IntroduceJobActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.BaseFragment;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.CommonAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.ViewHolder;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;
import com.youzheng.tongxiang.huntingjob.UI.Widget.NoScrollListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by qiuweiyu on 2018/2/9.
 */

public class IntroduceJobFragment extends BaseFragment {

    @BindView(R.id.ls)
    ListView ls;
    Unbinder unbinder;

    private int page =1 ,rows =20;

    private CommonAdapter<JobBeanDetails> adapter;
    private List<JobBeanDetails> data_data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.introduce_job_fragment_layout, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

    }

    private void initView() {

        adapter = new CommonAdapter<JobBeanDetails>(mContext, data_data, R.layout.home_page_ls_item) {
            @Override
            public void convert(ViewHolder helper, final JobBeanDetails item) {
                helper.setText(R.id.tv_name, item.getTitle());
                helper.setText(R.id.tv_level_l, item.getEducation() + "/" + item.getExperience() + "/" + item.getCity());
                if (item.getWage_face()==0) {
                    helper.setText(R.id.tv_money, "" + item.getWage_min() / 1000 + "K" + "-" + item.getWage_max() / 1000 + "K");
                }else {
                    helper.setText(R.id.tv_money,"面议");
                }                helper.setText(R.id.tv_name_co, item.getName());
                helper.setText(R.id.tv_below, item.getTrade());
                Glide.with(mContext).load(item.getCom_logo()).placeholder(R.mipmap.zhaoshangyinhang).into((ImageView) helper.getView(R.id.iv_logo));

                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, IntroduceJobActivity.class);
                        intent.putExtra("id", item.getId());
                        startActivity(intent);
                    }
                });
            }
        };
        ls.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(CoBeanDetails details){
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",rows);
        map.put("uid",details.getUid());
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.GET_CO_JOB, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseEntity entity = gson.fromJson(response,BaseEntity.class);
                if (entity.getCode().equals(PublicUtils.SUCCESS)){
                    IntroduceCoJobBean jobBean = gson.fromJson(gson.toJson(entity.getData()),IntroduceCoJobBean.class);
                    adapter.setData(jobBean.getAllJobList());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}
