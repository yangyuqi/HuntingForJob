package com.youzheng.tongxiang.huntingjob.Prestener.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.youzheng.tongxiang.huntingjob.Model.IBasePresenter;
import com.youzheng.tongxiang.huntingjob.View.IBaseView;

import javax.inject.Inject;

/**
 * Created by qiuweiyu on 2018/2/7.
 */

public class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView {

    protected Context mContext;

    protected T mPresenter ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showNetError() {

    }

    protected void showToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
