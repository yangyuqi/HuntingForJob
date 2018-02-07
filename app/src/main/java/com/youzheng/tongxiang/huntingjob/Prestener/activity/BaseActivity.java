package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.youzheng.tongxiang.huntingjob.Model.IBasePresenter;
import com.youzheng.tongxiang.huntingjob.View.IBaseView;

import butterknife.ButterKnife;

/**
 * Created by qiuweiyu on 2018/2/7.
 */

public  class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    protected Context mContext ;
    protected FragmentManager fm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        mContext = this ;
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
