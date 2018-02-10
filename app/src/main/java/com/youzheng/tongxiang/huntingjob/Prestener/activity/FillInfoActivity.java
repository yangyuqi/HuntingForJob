package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.Model.Event.EventModel;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.Login.UserInfoOnePageFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.Login.UserInfoTwoPageFragment;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.UserInfoFragmentPagerAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiuweiyu on 2018/2/8.
 */

public class FillInfoActivity extends BaseActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    @BindView(R.id.vp_layout)
    ViewPager vpLayout;
    @BindView(R.id.view_two)
    ImageView viewTwo;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.view_one)
    ImageView viewOne;

    private List<Fragment> list = new ArrayList<>();
    private UserInfoFragmentPagerAdapter adapter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fill_info_layout);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void OnEvent(EventModel model){
        if (model==EventModel.GO_UPPER_PAGE){

        }
    }

    private void initView() {
        textHeadTitle.setVisibility(View.GONE);
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        list.add(new UserInfoOnePageFragment());
        list.add(new UserInfoTwoPageFragment());
        adapter = new UserInfoFragmentPagerAdapter(fm,list);
        vpLayout.setAdapter(adapter);

        vpLayout.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    viewOne.setImageResource(R.mipmap.no01);
                    viewTwo.setImageResource(R.mipmap.no02);
                }else if (position==1){
                    viewTwo.setImageResource(R.mipmap.no01);
                    viewOne.setImageResource(R.mipmap.no02);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
