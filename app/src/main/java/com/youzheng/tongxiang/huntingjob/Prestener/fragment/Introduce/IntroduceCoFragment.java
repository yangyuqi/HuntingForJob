package com.youzheng.tongxiang.huntingjob.Prestener.fragment.Introduce;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.CoBeanDetails;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.BaseFragment;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
        EventBus.getDefault().register(this);
        initView();
        return view;
    }

    private void initView() {
        labelView.setLabels(label);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onEvent(CoBeanDetails details){
        if (details.getAddress()!=null){
            ArrayList<String> arrayList = new ArrayList<>();
            tvAddress.setText(details.getAddress());
            if (details.getJobtag().indexOf(",")>0){
                String[] strings = details.getJobtag().split(",");
                for (int i = 0 ;i<strings.length;i++){
                    arrayList.add(strings[i]);
                }
                labelView.setLabels(arrayList);
            }else {
                arrayList.add(details.getJobtag());
                labelView.setLabels(arrayList);
            }
            lsDetails.loadDataWithBaseURL(null, PublicUtils.getNewContent(details.getSummary()),"text/html","utf-8",null);
        }
    }
}
