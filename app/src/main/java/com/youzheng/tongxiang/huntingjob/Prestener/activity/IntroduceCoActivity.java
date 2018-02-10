package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.donkingliang.labels.LabelsView;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.Introduce.IntroduceCoFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.Introduce.IntroduceJobFragment;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.UserInfoFragmentPagerAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiuweiyu on 2018/2/9.
 */

public class IntroduceCoActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.view_page)
    ViewPager viewPage;

    private List<Fragment> list = new ArrayList<>();
    private MyFragmentPagerAdapter adapter;
    private ArrayList<String> label = new ArrayList<>();
    private String[] titles = {"公司介绍","招聘职位"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduce_co_layout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tab.post(new Runnable() {
            @Override
            public void run() {
                PublicUtils.setTabLine(tab, 65, 65);
            }
        });
        list.clear();
        list.add(new IntroduceCoFragment());
        list.add(new IntroduceJobFragment());
        adapter = new MyFragmentPagerAdapter(fm, list);
        viewPage.setAdapter(adapter);
        tab.setupWithViewPager(viewPage);
    }

    public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        List<Fragment> list ;

        public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list ;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
