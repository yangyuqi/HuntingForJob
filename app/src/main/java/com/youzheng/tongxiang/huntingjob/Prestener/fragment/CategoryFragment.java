package com.youzheng.tongxiang.huntingjob.Prestener.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.Prestener.activity.SearchJobActivity;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.CommonAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by qiuweiyu on 2018/2/7.
 */

public class CategoryFragment extends BaseFragment {

    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    Unbinder unbinder;
    @BindView(R.id.rg_one)
    RadioGroup rgOne;
    @BindView(R.id.rg_two)
    RadioGroup rgTwo;
    @BindView(R.id.dl_drawer)
    DrawerLayout llDrawer;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textHeadNext)
    TextView textHeadNext;
    @BindView(R.id.layout_header)
    RelativeLayout layoutHeader;
    @BindView(R.id.ls_three)
    ListView lsThree;

    private CommonAdapter<String> adapter ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public CategoryFragment() {
    }

    public static CategoryFragment newInstance(String da) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", da);
        //fragment保存参数，传入一个Bundle对象
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.category_layout, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        textHeadTitle.setText("分类");
        if (getArguments() != null) {
            layoutHeader.setVisibility(View.GONE);
        }
        llDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        for (int i = 0; i < 15; i++) {
            RadioButton tempButton = new RadioButton(mContext);
            tempButton.setBackgroundResource(R.drawable.category_bg_item);   // 设置RadioButton的背景图片
            tempButton.setButtonDrawable(R.drawable.drawable_category_item);           // 设置按钮的样式
            tempButton.setPadding(80, 0, 0, 0);                 // 设置文字距离按钮四周的距离
            tempButton.setText("按钮 " + i);
            tempButton.setTextColor(mContext.getResources().getColorStateList(R.color.category_text_color));
            rgOne.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            tempButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    llDrawer.openDrawer(GravityCompat.END);
                    for (int i = 0; i < 15; i++) {
                        RadioButton tempButton = new RadioButton(mContext);
                        tempButton.setBackgroundResource(R.drawable.category_bg_item2);
                        tempButton.setButtonDrawable(R.drawable.drawable_category_item2);
                        tempButton.setPadding(80, 0, 0, 0);
                        tempButton.setText("二级按钮 " + i);
                        tempButton.setTextColor(mContext.getResources().getColorStateList(R.color.category_text_color));
                        rgTwo.addView(tempButton, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        if (i == 0) {
                            tempButton.setChecked(true);
                        }
                    }
                }
            });
        }

        rgTwo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                List<String> data = new ArrayList<String>();
                for (int j = 0; j < 15; j++) {
                    data.add("三级按钮 " + i);
                }
                adapter = new CommonAdapter<String>(mContext,data,R.layout.three_category_item) {
                    @Override
                    public void convert(final ViewHolder helper, String item) {
                        helper.setText(R.id.tv_name,item);
                        helper.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                helper.getView(R.id.iv_show).setVisibility(View.VISIBLE);
                                startActivity(new Intent(mContext, SearchJobActivity.class));
                            }
                        });
                    }
                };

                lsThree.setAdapter(adapter);
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.textHeadNext})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.textHeadNext:

                break;
        }
    }

}
