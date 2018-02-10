package com.youzheng.tongxiang.huntingjob.Prestener.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.IntroduceJobActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.SchoolJobActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.SearchHistoryActivity;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.BannerNormalAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.CommonAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.ViewHolder;
import com.youzheng.tongxiang.huntingjob.UI.MyIconHintView;
import com.youzheng.tongxiang.huntingjob.UI.Widget.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

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

    private List<Integer> data = new ArrayList<>();
    private CommonAdapter<String> adapter;
    private List<String> data_data = new ArrayList<>();

    private List<String> new_data = new ArrayList<>();
    private CommonAdapter<String> adapter2 ;

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
        data.clear();
        data.add(R.mipmap.banner);
        data.add(R.mipmap.banner);
        data.add(R.mipmap.banner);
        rollView.setPlayDelay(6000);
        rollView.setHintView(new MyIconHintView(mContext, R.mipmap.select_rv_bg, R.mipmap.two_pg));
        rollView.setAdapter(new BannerNormalAdapter(data));
        ls.setFocusable(false);
        data_data.clear();
        data_data.add("软件开发工程师");
        data_data.add("软件测试工程师");
        data_data.add("软件实施工程师");
        data_data.add("软件开发工程师");
        adapter = new CommonAdapter<String>(mContext, data_data, R.layout.home_page_ls_item) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.tv_name, item);
            }
        };
        ls.setAdapter(adapter);

        new_data.clear();
        new_data.add("鱿鱼金融");new_data.add("优正科技");new_data.add("鱿鱼金融");new_data.add("优正科技");
        adapter2 = new CommonAdapter<String>(mContext,new_data,R.layout.home_page_delay_ls_item) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.tv_co,item);
            }
        };
        lsReplay.setAdapter(adapter2);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick({R.id.iv_go2,R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_go2:
                startActivity(new Intent(mContext, SchoolJobActivity.class));
                break;
            case R.id.tv_search :
                startActivity(new Intent(mContext, SearchHistoryActivity.class));
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void initEvent() {
        ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                startActivity(new Intent(mContext, IntroduceJobActivity.class));
            }
        });
    }
}
