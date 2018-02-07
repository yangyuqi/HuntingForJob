package com.youzheng.tongxiang.huntingjob.Prestener.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    @BindView(R.id.iv_go)
    ImageView ivGo;
    @BindView(R.id.tv_more2)
    TextView tvMore2;
    @BindView(R.id.iv_go2)
    ImageView ivGo2;
    @BindView(R.id.tv_more3)
    TextView tvMore3;
    @BindView(R.id.iv_go3)
    ImageView ivGo3;
    @BindView(R.id.ls)
    NoScrollListView ls;

    private List<Integer> data = new ArrayList<>();
    private CommonAdapter<String> adapter ;
    private List<String> data_data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
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
        data_data.add("软件开发工程师");data_data.add("软件测试工程师");data_data.add("软件实施工程师");data_data.add("软件开发工程师");
        adapter = new CommonAdapter<String>(mContext,data_data,R.layout.home_page_ls_item) {
            @Override
            public void convert(ViewHolder helper, String item) {
                helper.setText(R.id.tv_name,item);
            }
        };
        ls.setAdapter(adapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @OnClick(R.id.iv_go2)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.iv_go2 :
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
