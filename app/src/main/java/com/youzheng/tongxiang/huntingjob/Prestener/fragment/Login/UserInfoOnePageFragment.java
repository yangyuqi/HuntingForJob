package com.youzheng.tongxiang.huntingjob.Prestener.fragment.Login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.Model.Event.EventModel;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.BaseFragment;
import com.youzheng.tongxiang.huntingjob.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by qiuweiyu on 2018/2/8.
 */

public class UserInfoOnePageFragment extends BaseFragment {

    @BindView(R.id.cb)
    CheckBox cb;
    @BindView(R.id.cb_woman)
    CheckBox cbWoman;
    @BindView(R.id.tv_brith)
    TextView tvBrith;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.btn_login)
    Button btnLogin;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info_one_page_layout, null);
        unbinder = ButterKnife.bind(this, view);

        initView();
        return view;
    }

    private void initView() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(EventModel.GO_UPPER_PAGE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
