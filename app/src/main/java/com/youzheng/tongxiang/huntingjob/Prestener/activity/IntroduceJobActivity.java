package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.JobBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.JobBeanDetails;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.SharedPreferencesUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    @BindView(R.id.tv_place)
    TextView tvPlace;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.tv_work_style)
    TextView tvWorkStyle;
    @BindView(R.id.tv_experice)
    TextView tvExperice;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.iv_co_icon)
    ImageView ivCoIcon;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_co_name)
    TextView tvCoName;
    @BindView(R.id.tv_co_type)
    TextView tvCoType;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_ctype)
    TextView tvCtype;
    @BindView(R.id.iv_mm)
    ImageView ivMm;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.iv_shoucan)
    ImageView ivShoucan;
    @BindView(R.id.ll_click_shoucan)
    LinearLayout llClickShoucan;
    @BindView(R.id.rl_deliver_msg)
    RelativeLayout rlDeliverMsg;

    private int id, collect_id, is_delivery ,com_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce_job_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        Map<String, Object> map = new HashMap<>();
        map.put("jid", id);
        map.put("uid", uid);
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.JOB_DETAILS, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                    try {
                        JobBean mm = gson.fromJson(gson.toJson(entity.getData()), JobBean.class);
                        JobBeanDetails details = mm.getJob();
                        tvPlace.setText(details.getCity());
                        if (details.getEducation() != null) {
                            tvClass.setText(details.getEducation());
                        }
                        if (details.getJobs_nature() != null) {
                            tvWorkStyle.setText(details.getJobs_nature());
                        }
                        tvExperice.setText(details.getExperience());
                        tvYear.setText(details.getScale());
                        tvTitle.setText(details.getTitle());
                        tvMoney.setText("" + details.getWage_min() / 1000 + "K" + "-" + details.getWage_max() / 1000 + "K");
                        Glide.with(mContext).load(details.getCom_logo()).placeholder(R.mipmap.gongsilogo).into(ivCoIcon);
                        tvCoName.setText(details.getName());
                        tvCoType.setText(details.getCompany_type());
                        tvNum.setText(details.getScale());
                        tvCtype.setText(details.getCtype());
                        collect_id = details.getIs_collect();
                        is_delivery = details.getIs_delivery();
                        if (details.getIs_collect() == 0) {
                            ivShoucan.setImageResource(R.mipmap.shoucang4);
                        } else if (details.getIs_collect() == 1) {
                            ivShoucan.setImageResource(R.mipmap.shoucang1);
                        }
                        com_id = details.getCom_id();
                    }catch (Exception e){

                    }
                }
            }
        });
    }

    private void initView() {
        textHeadTitle.setText("职位信息");
        btnBack.setVisibility(View.VISIBLE);
        id = getIntent().getIntExtra("id", 0);
    }

    @OnClick({R.id.btnBack, R.id.iv_see_co, R.id.ll_click_shoucan,R.id.rl_deliver_msg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.iv_see_co:
                Intent intent = new Intent(mContext,IntroduceCoActivity.class);
                intent.putExtra("id",com_id);
                startActivity(intent);
                break;
            case R.id.ll_click_shoucan:

                if (collect_id == 0) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("jid", id);
                    map.put("ctype", 1);
                    map.put("uid", uid);
                    OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.JOB_COLLECT, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                            if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                                ivShoucan.setImageResource(R.mipmap.shoucang1);
                                try {
                                    JSONObject object = new JSONObject(gson.toJson(entity.getData()));
                                    collect_id = object.getInt("is_collect");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                } else if (collect_id == 1) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("jid", id);
                    map.put("ctype", 1);
                    map.put("uid", uid);
                    OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.UNSCRIBE_JOB, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                            if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                                ivShoucan.setImageResource(R.mipmap.shoucang4);
                                try {
                                    JSONObject object = new JSONObject(gson.toJson(entity.getData()));
                                    collect_id = object.getInt("is_collect");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                }
                break;

            case R.id.rl_deliver_msg:
                if (is_delivery==1){
                    showToast("您已投递该职位");
                }else if (is_delivery==0){
                    Map<String,Object> map = new HashMap<>();
                    map.put("jid",id);
                    map.put("rid",rid);
                    map.put("uid",uid);
                    OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.TOUJIANLI_JOB, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                             BaseEntity entity = gson.fromJson(response,BaseEntity.class);
                             showToast(entity.getMsg());
                        }
                    });
                }
                break;
        }
    }
}
