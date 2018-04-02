package com.youzheng.tongxiang.huntingjob;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.youzheng.tongxiang.huntingjob.Model.Event.EventModel;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.BaseActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.CategoryFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.HomePageFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.JianLiFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.MessageFragment;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.UserCenterFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {


    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.foot_bar_home)
    RadioButton footBarHome;
    @BindView(R.id.foot_bar_im)
    RadioButton footBarIm;
    @BindView(R.id.foot_bar_im2)
    RadioButton footBarIm2;
    @BindView(R.id.foot_bar_interest)
    RadioButton footBarInterest;
    @BindView(R.id.main_footbar_user)
    RadioButton mainFootbarUser;
    @BindView(R.id.group)
    RadioGroup group;
    private ArrayList<String> fragmentTags;
    private static final String CURR_INDEX = "currIndex";
    private static int currIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initData(savedInstanceState);
        showFragment();
        initEvent();
    }

    private void initEvent() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.foot_bar_home : currIndex = 0 ;break;
                    case R.id.foot_bar_im : currIndex = 1 ;break;
                    case R.id.foot_bar_im2 : currIndex = 2 ;break;
                    case R.id.foot_bar_interest : currIndex = 3 ;break;
                    case R.id.main_footbar_user : currIndex = 4 ;break;
                }
                showFragment();
            }
        });
    }

    private void initData(Bundle savedInstanceState) {
        fragmentTags = new ArrayList<>(Arrays.asList("HomePageFragment", "CategoryFragment", "MessageFragment", "JianLiFragment","UserCenterFragment"));
        if(savedInstanceState != null) {
            currIndex = savedInstanceState.getInt(CURR_INDEX);
            hideSavedFragment();
        }
    }

    private void showFragment() {
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        Fragment fragment = fm.findFragmentByTag(fragmentTags.get(currIndex));
        if(fragment == null) {
            fragment = instantFragment(currIndex);
        }
        for (int i = 0; i < fragmentTags.size(); i++) {
            Fragment f = fm.findFragmentByTag(fragmentTags.get(i));
            if(f != null && f.isAdded()) {
                fragmentTransaction.hide(f);
            }
        }
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(R.id.fragment_container, fragment, fragmentTags.get(currIndex));
        }
        fragmentTransaction.commitAllowingStateLoss();
        fm.executePendingTransactions();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURR_INDEX, currIndex);
    }

    private Fragment instantFragment(int currIndex) {
        switch (currIndex) {
            case 0 :
                return new HomePageFragment();
            case 1:
                return new CategoryFragment();
            case 2:
                return new MessageFragment();
            case 3:
                return new JianLiFragment();
            case 4:
                return new UserCenterFragment();

            default:
                return null;
        }
    }

    private void hideSavedFragment() {
        Fragment fragment = fm.findFragmentByTag(fragmentTags.get(currIndex));
        if(fragment != null) {
            fm.beginTransaction().hide(fragment).commit();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe
    public void onEvent(EventModel model){
        if (model==EventModel.GO_UPPER_PAGE){
            finish();
        }
    }

}
