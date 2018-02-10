package com.youzheng.tongxiang.huntingjob.Prestener.fragment.Category;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.donkingliang.labels.LabelsView;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.BaseFragment;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.CommonAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by qiuweiyu on 2018/2/10.
 */

public class DetailsCategoryFragment extends BaseFragment {

    @BindView(R.id.ls)
    ListView ls;
    Unbinder unbinder;

    private ArrayList<ArrayList<String>> all_data = new ArrayList<>();
    private ArrayList<String> data = new ArrayList<>();
    private ArrayList<String> data1 = new ArrayList<>();
    private CommonAdapter<ArrayList<String>> adapter ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.details_category_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        data.add("应届生");data.add("应届生");data.add("应届生");data.add("应届生");data.add("应届生");
        data1.add("MBA");data1.add("MBA");data1.add("MBA");data1.add("MBA");data1.add("MBA");data1.add("MBA");data1.add("MBA");
        all_data.add(data);all_data.add(data1);
        adapter = new CommonAdapter<ArrayList<String>>(mContext,all_data,R.layout.details_category_ls_item) {
            @Override
            public void convert(ViewHolder helper, ArrayList<String> item) {
                LabelsView labelsView = helper.getView(R.id.labelView);
                labelsView.setLabels(item);
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
