package com.youzheng.tongxiang.huntingjob.Prestener.activity.User;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.Prestener.activity.BaseActivity;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.CommonAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiuweiyu on 2018/2/11.
 */

public class SpeakActivity extends BaseActivity {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.ls)
    ListView ls;

    private List<String> data = new ArrayList<>();
    private CommonAdapter<String> adapter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speak_activity_layout);
        ButterKnife.bind(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        textHeadTitle.setText("宣讲会");
        btnBack.setVisibility(View.VISIBLE);

        data.add("苏州大学");data.add("南京大学");data.add("苏州大学");data.add("南京大学");data.add("苏州大学");data.add("南京大学");data.add("苏州大学");data.add("南京大学");data.add("苏州大学");data.add("南京大学");
        adapter = new CommonAdapter<String>(mContext,data,R.layout.speak_ls_item) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.tv_school_name,item);
            }
        };
        ls.setAdapter(adapter);

    }


}
