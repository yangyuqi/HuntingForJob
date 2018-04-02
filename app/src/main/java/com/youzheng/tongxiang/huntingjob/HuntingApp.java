package com.youzheng.tongxiang.huntingjob;

import android.app.Application;
import android.util.Log;

import com.tbruyelle.rxpermissions.RxPermissions;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.LoginActivity;
import com.youzheng.tongxiang.huntingjob.UI.Utils.SharedPreferencesUtils;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by qiuweiyu on 2018/2/7.
 */

public class HuntingApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化sdk
        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);

        //建议添加tag标签，发送消息的之后就可以指定tag标签来发送了

    }
}
