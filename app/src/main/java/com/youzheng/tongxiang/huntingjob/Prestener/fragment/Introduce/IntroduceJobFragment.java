package com.youzheng.tongxiang.huntingjob.Prestener.fragment.Introduce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.youzheng.tongxiang.huntingjob.Prestener.fragment.BaseFragment;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.CommonAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.ViewHolder;
import com.youzheng.tongxiang.huntingjob.UI.Widget.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by qiuweiyu on 2018/2/9.
 */

public class IntroduceJobFragment extends BaseFragment {

    @BindView(R.id.ls)
    ListView ls;
    Unbinder unbinder;

    private CommonAdapter<String> adapter;
    private List<String> data_data = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.introduce_job_fragment_layout, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
