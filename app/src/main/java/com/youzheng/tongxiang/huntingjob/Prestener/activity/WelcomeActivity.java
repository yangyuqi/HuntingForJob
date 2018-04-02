package com.youzheng.tongxiang.huntingjob.Prestener.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.squareup.okhttp.Request;
import com.youzheng.tongxiang.huntingjob.MainActivity;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.SharedPreferencesUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by qiuweiyu on 2018/2/22.
 */

public class WelcomeActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                int first = (int) SharedPreferencesUtils.getParam(mContext,SharedPreferencesUtils.fiet,0);
                if (first==0){
                    startActivity(new Intent(mContext,AdActivity.class));
                    finish();
                }else {
                    if (accessToken.equals("")) {
                        startActivity(new Intent(mContext,LoginActivity.class));
                        finish();
                    }else {
//                        startActivity(new Intent(mContext,MainActivity.class));
                        Map<String,Object> map = new HashMap<>();
                        map.put("access_token",accessToken);
                        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.TOKEN_LOGIN, new OkHttpClientManager.StringCallback() {
                            @Override
                            public void onFailure(Request request, IOException e) {

                            }

                            @Override
                            public void onResponse(String response) {
                                try {
                                    BaseEntity entity = gson.fromJson(response,BaseEntity.class);
                                    if (entity.getCode()==null){
                                        startActivity(new Intent(mContext,LoginActivity.class));
                                        finish();
                                    }else if (entity.getCode().equals(PublicUtils.SUCCESS)){
                                        startActivity(new Intent(mContext,MainActivity.class));
                                        finish();
                                    }else if (!entity.getCode().equals(PublicUtils.SUCCESS)){
                                        startActivity(new Intent(mContext,LoginActivity.class));
                                        finish();
                                    }
                                }catch (Exception e){
                                    startActivity(new Intent(mContext,LoginActivity.class));
                                    finish();
                                }
                            }
                        });
                    }
                }
            }
        },1500);
    }
}
