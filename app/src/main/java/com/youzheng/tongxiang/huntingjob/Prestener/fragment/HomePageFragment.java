package com.youzheng.tongxiang.huntingjob.Prestener.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.RollPagerView;
import com.squareup.okhttp.Request;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.GoodsCoBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.GoodsCoBeanDetails;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.HomeBeanList;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.ReplayFastBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.ReplayFastBeanDetails;
import com.youzheng.tongxiang.huntingjob.Model.entity.deliver.BannerList;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.CoListActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.HotNewActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.IntroduceCoActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.IntroduceJobActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.JoListActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.SchoolJobActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.SearchJobActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.User.CollectActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.User.DeliverMessageActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.UserResumeActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.WorkDiscussActivity;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.BannerNormalAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.CommonAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.ViewHolder;
import com.youzheng.tongxiang.huntingjob.UI.MyIconHintView;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;
import com.youzheng.tongxiang.huntingjob.UI.Widget.NoScrollListView;
import com.youzheng.tongxiang.huntingjob.UI.dialog.DeleteDialog;
import com.youzheng.tongxiang.huntingjob.UI.dialog.DeleteDialogInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by qiuweiyu on 2018/2/7.
 */

public class HomePageFragment extends BaseFragment {


    @BindView(R.id.roll_view)
    RollPagerView rollView;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    Unbinder unbinder;
    @BindView(R.id.tv_more)
    TextView tvMore;
    @BindView(R.id.tv_more2)
    TextView tvMore2;
    @BindView(R.id.iv_go2)
    TextView ivGo2;
    @BindView(R.id.ls)
    NoScrollListView ls;
    @BindView(R.id.tv_home_name)
    TextView tvHomeName;
    @BindView(R.id.tv_home_name_co)
    TextView tvHomeNameCo;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_name_co)
    TextView tvNameCo;
    @BindView(R.id.tv_delay_name)
    TextView tvDelayName;
    @BindView(R.id.tv_more_delay)
    TextView tvMoreDelay;
    @BindView(R.id.ls_replay)
    NoScrollListView lsReplay;
    @BindView(R.id.tv_hot_name)
    TextView tvHotName;
    @BindView(R.id.tv_more4)
    TextView tvMore4;
    @BindView(R.id.iv_icon1)
    ImageView ivIcon1;
    @BindView(R.id.iv_icon2)
    ImageView ivIcon2;
    @BindView(R.id.iv_icon13)
    ImageView ivIcon13;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_mm)
    TextView tvMm;
    @BindView(R.id.tv_below)
    TextView tvBelow;
    @BindView(R.id.tv_tv_money)
    TextView tvTvMoney;
    @BindView(R.id.tv_mmm)
    TextView tvMmm;
    @BindView(R.id.tv_below_below)
    TextView tvBelowBelow;
    @BindView(R.id.rl_click)
    RelativeLayout rlClick;
    @BindView(R.id.rl_rl_click)
    RelativeLayout rlRlClick;
    @BindView(R.id.tv_shoucang)
    TextView tvShoucang;
    @BindView(R.id.tv_deliever)
    TextView tvDeliever;
    @BindView(R.id.tv_hot_news)
    TextView tvHotNews;
    @BindView(R.id.tv_consule)
    TextView tvConsule;
    @BindView(R.id.tv_more5)
    TextView tvMore5;
    @BindView(R.id.ls_new)
    NoScrollListView lsNew;

    private CommonAdapter<GoodsCoBeanDetails> adapter;
    private List<GoodsCoBeanDetails> data_data = new ArrayList<>();

    private List<ReplayFastBeanDetails> new_data = new ArrayList<>();
    private CommonAdapter<ReplayFastBeanDetails> adapter2;
    private CommonAdapter<GoodsCoBeanDetails> adapter3 ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initEvent();
        return view;
    }


    private void initView() {

        rollView.setPlayDelay(6000);
        rollView.setHintView(new MyIconHintView(mContext, R.mipmap.select_rv_bg, R.mipmap.two_pg));
        Map<String, Object> map = new HashMap<>();
        map.put("showType", "app");
        map.put("placement", "home");
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.QUERY_BANNER, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                    BannerList list = gson.fromJson(gson.toJson(entity.getData()), BannerList.class);
                    if (list.getBanner().size() > 0) {
                        rollView.setAdapter(new BannerNormalAdapter(list.getBanner()));
                    }
                }
            }
        });

        ls.setFocusable(false);

        adapter = new CommonAdapter<GoodsCoBeanDetails>(mContext, data_data, R.layout.home_page_ls_item) {
            @Override
            public void convert(ViewHolder helper, final GoodsCoBeanDetails item) {
                helper.setText(R.id.tv_name, item.getTitle());
                helper.setText(R.id.tv_level_l, item.getEducation() + "/" + item.getCitysName());
                helper.setText(R.id.tv_name_co, item.getName());
                helper.setText(R.id.tv_below, item.getTradeName());
                ImageView imageView = helper.getView(R.id.iv_logo);
                Glide.with(mContext).load(item.getCom_logo()).placeholder(R.mipmap.youyuzhanweicopy2).into(imageView);
                if (item.getWage_face()==0) {
                    helper.setText(R.id.tv_money, "" + item.getWage_min() / 1000 + "K" + "-" + item.getWage_max() / 1000 + "K");
                }else {
                    helper.setText(R.id.tv_money,"面议");
                }
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


        adapter2 = new CommonAdapter<ReplayFastBeanDetails>(mContext, new_data, R.layout.home_page_delay_ls_item) {
            @Override
            public void convert(ViewHolder helper, final ReplayFastBeanDetails item) {
                helper.setText(R.id.tv_co, item.getName());
                helper.setText(R.id.tv_intro, item.getSummary());
                Glide.with(mContext).load(item.getLogo()).placeholder(R.mipmap.youyuzhanweicopy2).into(((ImageView) helper.getView(R.id.iv_icon)));
                helper.setText(R.id.tv_product, item.getTradeName());
                helper.setText(R.id.tv_num, item.getScaleName());
                helper.setText(R.id.tv_pace, item.getCitysName());

                helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, IntroduceCoActivity.class);
                        intent.putExtra("id", item.getId());
                        mContext.startActivity(intent);
                    }
                });
            }
        };
        lsReplay.setFocusable(false);
        lsReplay.setAdapter(adapter2);

        adapter3 = new CommonAdapter<GoodsCoBeanDetails>(mContext, data_data, R.layout.home_page_ls_item) {
            @Override
            public void convert(ViewHolder helper, final GoodsCoBeanDetails item) {
                helper.setText(R.id.tv_name, item.getTitle());
                helper.setText(R.id.tv_level_l, item.getEducation() + "/" + item.getCitysName());
                helper.setText(R.id.tv_name_co, item.getName());
                helper.setText(R.id.tv_below, item.getTradeName());
                ImageView imageView = helper.getView(R.id.iv_logo);
                Glide.with(mContext).load(item.getCom_logo()).placeholder(R.mipmap.youyuzhanweicopy2).into(imageView);
                if (item.getWage_face()==0) {
                    helper.setText(R.id.tv_money, "" + item.getWage_min() / 1000 + "K" + "-" + item.getWage_max() / 1000 + "K");
                }else {
                    helper.setText(R.id.tv_money,"面议");
                }
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
        lsNew.setAdapter(adapter3);
        lsNew.setFocusable(false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

        Map<String, Object> map1 = new HashMap<>();
        map1.put("flag", "replay_fast");
        map1.put("page", 1);
        map1.put("rows", 5);
        map1.put("more","more");
        OkHttpClientManager.postAsynJson(gson.toJson(map1), UrlUtis.HOME_INFO, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                    ReplayFastBean fastBean = gson.fromJson(gson.toJson(entity.getData()), ReplayFastBean.class);
                    if (fastBean.getJobList().size() > 0) {
                        adapter2.setData(fastBean.getJobList());
                        adapter2.notifyDataSetChanged();
                    }
                }
            }
        });

        Map<String, Object> map2 = new HashMap<>();
        map2.put("flag", "hot");
        map2.put("page", 1);
        map2.put("rows", 5);
        map2.put("more","more");
        OkHttpClientManager.postAsynJson(gson.toJson(map2), UrlUtis.HOME_INFO, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                    GoodsCoBean coBean = gson.fromJson(gson.toJson(entity.getData()), GoodsCoBean.class);
                    if (coBean.getJobList().size() > 0) {
                        adapter.setData(coBean.getJobList());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        Map<String, Object> map3 = new HashMap<>();
        map3.put("accessToken", accessToken);
        map3.put("page",1);
        map3.put("rows",3);
        OkHttpClientManager.postAsynJson(gson.toJson(map3), UrlUtis.HOME_INTRODUCE, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                    final HomeBeanList list = gson.fromJson(gson.toJson(entity.getData()), HomeBeanList.class);
                    if (list.getJobList().size() >= 2) {
                        tvHomeName.setText(list.getJobList().get(0).getTitle());
                        if (list.getJobList().get(0).getWage_face()==0) {
                            tvMoney.setText( "" + list.getJobList().get(0).getWage_min() / 1000 + "K" + "-" + list.getJobList().get(0).getWage_max() / 1000 + "K");
                        }else {
                            tvMoney.setText("面议");
                        }
                        tvMm.setText(list.getJobList().get(0).getEducation() + "/" + list.getJobList().get(0).getCity());
                        tvHomeNameCo.setText(list.getJobList().get(0).getName());
                        tvBelow.setText(list.getJobList().get(0).getJobType());
                        rlClick.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, IntroduceJobActivity.class);
                                intent.putExtra("id", list.getJobList().get(0).getId());
                                startActivity(intent);
                            }
                        });
                        if (list.getJobList().get(1).getWage_face()==0) {
                            tvTvMoney.setText( "" + list.getJobList().get(1).getWage_min() / 1000 + "K" + "-" + list.getJobList().get(1).getWage_max() / 1000 + "K");
                        }else {
                            tvTvMoney.setText("面议");
                        }
                        tvName.setText(list.getJobList().get(1).getTitle());
                        tvMmm.setText(list.getJobList().get(1).getEducation() + "/" + list.getJobList().get(1).getCity());
                        tvNameCo.setText(list.getJobList().get(1).getName());
                        tvBelowBelow.setText(list.getJobList().get(1).getJobType());
                        rlRlClick.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, IntroduceJobActivity.class);
                                intent.putExtra("id", list.getJobList().get(1).getId());
                                startActivity(intent);
                            }
                        });
                    }

                    if (list.getComList().size() >= 3) {
                        Glide.with(mContext).load(list.getComList().get(0).getCom_logo()).placeholder(R.mipmap.youyuzhanweicopy2).into(ivIcon1);
                        Glide.with(mContext).load(list.getComList().get(1).getCom_logo()).placeholder(R.mipmap.youyuzhanweicopy2).into(ivIcon2);
                        Glide.with(mContext).load(list.getComList().get(2).getCom_logo()).placeholder(R.mipmap.youyuzhanweicopy2).into(ivIcon13);
                        ivIcon1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, IntroduceCoActivity.class);
                                intent.putExtra("id",list.getComList().get(0).getCom_id());
                                startActivity(intent);
                            }
                        });
                        ivIcon2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, IntroduceCoActivity.class);
                                intent.putExtra("id", list.getComList().get(1).getCom_id());
                                startActivity(intent);
                            }
                        });
                        ivIcon13.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, IntroduceCoActivity.class);
                                intent.putExtra("id", list.getComList().get(2).getCom_id());
                                startActivity(intent);
                            }
                        });
                    }

                }
            }
        });


        Map<String, Object> map5 = new HashMap<>();
        map5.put("flag", "recent");
        map5.put("page", 1);
        map5.put("rows", 5);
        map5.put("more","more");
        OkHttpClientManager.postAsynJson(gson.toJson(map5), UrlUtis.HOME_INFO, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                    GoodsCoBean coBean = gson.fromJson(gson.toJson(entity.getData()), GoodsCoBean.class);
                    if (coBean.getJobList().size() > 0) {
                        adapter3.setData(coBean.getJobList());
                        adapter3.notifyDataSetChanged();
                    }
                }
            }
        });

    }

    @OnClick({R.id.iv_go2, R.id.tv_search, R.id.tv_shoucang, R.id.tv_deliever, R.id.tv_hot_news, R.id.tv_consule, R.id.tv_more, R.id.tv_more2,R.id.tv_more4,R.id.tv_more5,R.id.tv_more_delay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_go2:
                startActivity(new Intent(mContext, SchoolJobActivity.class));
                break;
            case R.id.tv_search:
                startActivity(new Intent(mContext, SearchJobActivity.class));
                break;
            case R.id.tv_shoucang:
                startActivity(new Intent(mContext, CollectActivity.class));
                break;
            case R.id.tv_deliever:
                startActivity(new Intent(mContext, DeliverMessageActivity.class));
                break;
            case R.id.tv_hot_news:
                startActivity(new Intent(mContext, HotNewActivity.class));
                break;
            case R.id.tv_consule:
                startActivity(new Intent(mContext, WorkDiscussActivity.class));
                break;
            case R.id.tv_more:
                Intent intent_more = new Intent(mContext,SearchJobActivity.class);
                intent_more.putExtra("more","more");
                startActivity(intent_more);
                break;
            case R.id.tv_more2:
                Intent co_intent = new Intent(mContext, CoListActivity.class);
                co_intent.putExtra("type","co_introduce");
                co_intent.putExtra("title","公司推荐");
                startActivity(co_intent);
                break;
            case R.id.tv_more4 :
                Intent intent = new Intent(mContext, JoListActivity.class);
                intent.putExtra("type","hot");
                intent.putExtra("title","热门职位");
                startActivity(intent);
                break;
            case R.id.tv_more5 :
                Intent intent1 = new Intent(mContext, JoListActivity.class);
                intent1.putExtra("type","recent");
                intent1.putExtra("title","最新职位");
                startActivity(intent1);
                break;
            case R.id.tv_more_delay :
                Intent co_intent1 = new Intent(mContext, CoListActivity.class);
                co_intent1.putExtra("type","replay_fast");
                co_intent1.putExtra("title","回复最快");
                startActivity(co_intent1);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void initEvent() {

    }
}
