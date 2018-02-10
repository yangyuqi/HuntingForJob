package com.youzheng.tongxiang.huntingjob.Prestener.fragment.Introduce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.BaseFragment;
import com.youzheng.tongxiang.huntingjob.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by qiuweiyu on 2018/2/9.
 */

public class IntroduceCoFragment extends BaseFragment {
    @BindView(R.id.ls_details)
    WebView lsDetails;
    @BindView(R.id.labelView)
    LabelsView labelView;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    Unbinder unbinder;

    private ArrayList<String> label = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.introduce_co_fragment_layout, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        label.add("五险一金");label.add("包吃住");label.add("五险一金");label.add("包吃住");label.add("五险一金");label.add("包吃住");
        labelView.setLabels(label);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
