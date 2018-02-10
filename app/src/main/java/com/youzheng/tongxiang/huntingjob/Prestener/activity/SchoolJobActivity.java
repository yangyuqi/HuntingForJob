package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jude.rollviewpager.RollPagerView;
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

/**
 * Created by qiuweiyu on 2018/2/7.
 */

public class SchoolJobActivity extends BaseActivity {

    @BindView(R.id.roll_view)
    RollPagerView rollView;
    @BindView(R.id.ls)
    NoScrollListView ls;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    @BindView(R.id.top_header_ll_right_iv_caidan)
    ImageView topHeaderLlRightIvCaidan;
    @BindView(R.id.layout_header)
    RelativeLayout layoutHeader;
    private CommonAdapter<UserBean> adapter;
    private List<UserBean> data_data = new ArrayList<>();
    private List<Integer> data = new ArrayList<>();

    private List<String> com_data = new ArrayList<>();

    private class UserBean{
        public String name ;
        public List<String> data ;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.school_job_layout);
        ButterKnife.bind(this);
        layoutHeader.setBackgroundResource(R.drawable.main_color);
        textHeadTitle.setText("校园招聘会");
        btnBack.setVisibility(View.VISIBLE);
        topHeaderLlRightIvCaidan.setImageResource(R.mipmap.sousuo2);
        topHeaderLlRightIvCaidan.setVisibility(View.VISIBLE);
        initData();
    }


    private void initData() {
        data.clear();
        data.add(R.mipmap.banner);
        data.add(R.mipmap.banner);
        data.add(R.mipmap.banner);
        rollView.setPlayDelay(6000);
        rollView.setHintView(new MyIconHintView(mContext, R.mipmap.select_rv_bg, R.mipmap.two_pg));
        rollView.setAdapter(new BannerNormalAdapter(data));
        ls.setFocusable(false);
        data_data.clear();
        com_data.clear();
        com_data.add("软件开发工程师");
        com_data.add("软件测试工程师");
        com_data.add("软件实施工程师");
        com_data.add("软件开发工程师");
        UserBean userBean = new UserBean();
        userBean.name = "应届生专区";
        userBean.data = com_data ;
        data_data.add(userBean);
        UserBean userBean1 = new UserBean();
        userBean1.name = "实习生专区";
        userBean1.data = com_data ;
        data_data.add(userBean1);
        adapter = new CommonAdapter<UserBean>(mContext, data_data, R.layout.school_job_ls_item) {
            @Override
            public void convert(ViewHolder helper, UserBean item) {
                helper.setText(R.id.tv_name, item.name);
                CommonAdapter<String> com_adapter =  new CommonAdapter<String>(mContext,item.data,R.layout.home_page_ls_item) {
                    @Override
                    public void convert(ViewHolder helper, String item) {
                        helper.setText(R.id.tv_name,item);
                    }
                };
                ((NoScrollListView)helper.getView(R.id.ls)).setAdapter(com_adapter);
            }
        };
        ls.setAdapter(adapter);
    }

    @OnClick({R.id.btnBack,R.id.top_header_ll_right_iv_caidan})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnBack :
                break;
            case R.id.top_header_ll_right_iv_caidan :
                startActivity(new Intent(mContext,SearchJobActivity.class));
                break;
        }
    }
}
