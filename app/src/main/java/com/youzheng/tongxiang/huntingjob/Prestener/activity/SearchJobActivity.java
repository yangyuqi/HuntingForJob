package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.liaoinstan.springview.widget.SpringView;
import com.squareup.okhttp.Request;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.BeanCate;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.JobBeanDetails;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.SearchJobBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.EducationBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.EducationDetailsBean;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.Category.DetailsCategoryFragment;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.CommonAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.ViewHolder;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qiuweiyu on 2018/2/8.
 */

public class SearchJobActivity extends BaseActivity {
    @BindView(R.id.tv_search)
    EditText tvSearch;
    @BindView(R.id.ls)
    ListView ls;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rl_zhineng)
    RelativeLayout rlZhineng;
    @BindView(R.id.tv_go_search)
    TextView tvGoSearch;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.rl_gongzi)
    RelativeLayout rlGongzi;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.rl_work_self)
    RelativeLayout rlWorkSelf;
    @BindView(R.id.tv_self)
    TextView tvSelf;
    @BindView(R.id.tv_jingyan)
    TextView tvJingyan;
    @BindView(R.id.tv_xueli)
    TextView tvXueli;
    @BindView(R.id.rl_xueli)
    RelativeLayout rlXueli;
    @BindView(R.id.sv)
    SpringView sv;

    private List<JobBeanDetails> data_data = new ArrayList<>();
    private CommonAdapter<JobBeanDetails> adapter;

    private int page = 1, rows = 20, position1 = -1, position2 = -2, position3 = -3, position4 = -4;

    Map<String, Object> map = new HashMap<>();
    private String keyWords ,type ,more;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_job_layout);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        keyWords = getIntent().getStringExtra("keyWord");
        type = getIntent().getStringExtra("type");
        more = getIntent().getStringExtra("more");
        initData();
    }

    private void initData() {
        ls.setFocusable(false);
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
                Glide.with(mContext).load(item.getCom_logo()).placeholder(R.mipmap.youyuzhanweicopy2).into((ImageView) helper.getView(R.id.iv_logo));

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

        map.put("cityId",0);

        if (more!=null){
            map.put("accessToken",accessToken);
        }

        if (keyWords!=null){
            tvSearch.setText(keyWords);
            if (!keyWords.equals("兼职")) {
                map.put("param", keyWords);
            }
            if (keyWords.equals("兼职")){
                tvSelf.setText("兼职");
                Map<String, Object> map2 = new HashMap<>();
                map2.put("ctype", "jobs_nature");
                OkHttpClientManager.postAsynJson(gson.toJson(map2), UrlUtis.WORK_TIAOJIAN, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                        if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                            EducationBean comclass = gson.fromJson(gson.toJson(entity.getData()), EducationBean.class);
                            for (int i = 0 ;i <comclass.getComclass().size();i++){
                                if (comclass.getComclass().get(i).getName().equals("兼职")){
                                    map.put("page", page);
                                    map.put("rows", rows);
                                    map.put("jobs_nature",comclass.getComclass().get(i).getId());
                                    cataSearch(map);
                                }
                            }
                        }
                    }
                });
            }

        }

        if (type!=null){
            if (type.equals("应届生")){
                tvJingyan.setText("应届毕业生");
                Map<String, Object> map1 = new HashMap<>();
                map1.put("ctype", "experience");
                OkHttpClientManager.postAsynJson(gson.toJson(map1), UrlUtis.WORK_TIAOJIAN, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                        if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                            EducationBean comclass = gson.fromJson(gson.toJson(entity.getData()), EducationBean.class);
                            for (int i = 0 ;i <comclass.getComclass().size();i++){
                                if (comclass.getComclass().get(i).getName().equals("应届毕业生")){
                                    map.put("page", page);
                                    map.put("rows", rows);
                                    map.put("experience",comclass.getComclass().get(i).getId());
                                    cataSearch(map);
                                }
                            }
                        }
                    }
                });
            }

        }

        if (type==null) {
            if (keyWords!=null) {
                if (!keyWords.equals("兼职")) {
                    map.put("page", page);
                    map.put("rows", rows);
                    cataSearch(map);
                }
            }else {
                map.put("page", page);
                map.put("rows", rows);
                cataSearch(map);
            }
        }

        sv.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadmore() {
                page++;
                map.put("page",page);
                cataSearch(map);
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.rl_zhineng, R.id.rl_gongzi, R.id.tv_go_search, R.id.rl_work_self, R.id.rl_xueli})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                break;
            case R.id.rl_zhineng:
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fl_content, DetailsCategoryFragment.getInstance(new BeanCate(position1, "experience"))).commit();
                break;
            case R.id.rl_gongzi:
                FragmentTransaction transaction1 = fm.beginTransaction();
                transaction1.replace(R.id.fl_content, DetailsCategoryFragment.getInstance(new BeanCate(position3, "wage"))).commit();
                break;
            case R.id.tv_go_search:
                map.put("param", tvSearch.getText().toString());
                cataSearch(map);
                PublicUtils.closeKeybord(tvSearch, mContext);
                break;
            case R.id.rl_work_self:
                FragmentTransaction transaction2 = fm.beginTransaction();
                transaction2.replace(R.id.fl_content, DetailsCategoryFragment.getInstance(new BeanCate(position2, "jobs_nature"))).commit();
                break;
            case R.id.rl_xueli:
                FragmentTransaction transaction3 = fm.beginTransaction();
                transaction3.replace(R.id.fl_content, DetailsCategoryFragment.getInstance(new BeanCate(position4, "education"))).commit();
                break;
        }
    }

    public void cataSearch(Object o) {
        OkHttpClientManager.postAsynJson(gson.toJson(o), UrlUtis.SEARCH_JOB, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {
                sv.onFinishFreshAndLoad();
            }

            @Override
            public void onResponse(String response) {
                sv.onFinishFreshAndLoad();
                BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                    SearchJobBean jobBean = gson.fromJson(gson.toJson(entity.getData()), SearchJobBean.class);
                    if (page>1){
                        if (jobBean.getRecentList().size()>0) {
                            data_data.addAll(jobBean.getRecentList());
                            adapter.setData(data_data);
                        }
                    }else {
                        if (jobBean.getRecentList().size()>0) {
                            adapter.setData(jobBean.getRecentList());
                        }else {
                            adapter.setData(new ArrayList<JobBeanDetails>());
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEvent(EducationDetailsBean bean) {
        if (bean != null) {
            if (bean.getCtype().equals("wage")) {
                position3 = bean.getPosition();
                if (position3 != -3) {
                    map.put("wageLevel", bean.getId());
                    tvMoney.setText(bean.getName());
                } else {
                    if (map.containsKey("wageLevel")) {
                        map.remove("wageLevel");
                    }
                    tvMoney.setText("薪资范围");
                }
                cataSearch(map);
            } else if (bean.getCtype().equals("jobs_nature")) {
                position2 = bean.getPosition();
                if (position2 != -2) {
                    map.put("jobs_nature", bean.getId());
                    tvSelf.setText(bean.getName());
                } else {
                    if (map.containsKey("jobs_nature")) {
                        map.remove("jobs_nature");
                    }
                    tvSelf.setText("工作性质");
                }
                cataSearch(map);
            } else if (bean.getCtype().equals("experience")) {
                position1 = bean.getPosition();
                if (position1 != -1) {
                    map.put("experience", bean.getId());
                    tvJingyan.setText(bean.getName());
                } else {
                    if (map.containsKey("experience")) {
                        map.remove("experience");
                    }
                    tvJingyan.setText("工作经验");
                }
                cataSearch(map);
            } else if (bean.getCtype().equals("education")) {
                position4 = bean.getPosition();
                if (position4 != -4) {
                    map.put("education", bean.getId());
                    tvXueli.setText(bean.getName());
                } else {
                    if (map.containsKey("education")) {
                        map.remove("education");
                    }
                    tvXueli.setText("学历");
                }
                cataSearch(map);
            }
        }
    }
}
