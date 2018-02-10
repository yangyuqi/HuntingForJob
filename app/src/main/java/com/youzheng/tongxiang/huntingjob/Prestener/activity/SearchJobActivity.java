package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.Prestener.fragment.Category.DetailsCategoryFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.CategoryFragment;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.CommonAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.ViewHolder;
import com.youzheng.tongxiang.huntingjob.UI.Widget.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by qiuweiyu on 2018/2/8.
 */

public class SearchJobActivity extends BaseActivity {
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.ls)
    NoScrollListView ls;
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

    private List<String> data_data = new ArrayList<>();
    private CommonAdapter<String> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_job_layout);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
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
    }

    @OnClick({R.id.iv_back, R.id.rl_zhineng,R.id.rl_gongzi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                flContent.setVisibility(View.GONE);
                break;
            case R.id.rl_zhineng:
                flContent.setVisibility(View.VISIBLE);
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.fl_content, CategoryFragment.newInstance("")).commit();
                break;
            case R.id.rl_gongzi:
                FragmentTransaction transaction1 = fm.beginTransaction();
                transaction1.replace(R.id.fl_content,new DetailsCategoryFragment()).commit();
                break;
        }
    }
}
