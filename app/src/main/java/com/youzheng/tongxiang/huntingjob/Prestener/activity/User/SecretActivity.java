package com.youzheng.tongxiang.huntingjob.Prestener.activity.User;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.okhttp.Request;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.BaseActivity;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by qiuweiyu on 2018/2/11.
 */

public class SecretActivity extends BaseActivity {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    @BindView(R.id.switvh)
    Switch switvh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secret_layout);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        Map<String,Object> map = new HashMap<>();
        map.put("accessToken",accessToken);
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.SCREAT_LANCHER, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseEntity entity = gson.fromJson(response,BaseEntity.class);
                if (entity.getCode().equals(PublicUtils.SUCCESS)){
                    try {
                        JSONObject object = new JSONObject(gson.toJson(entity.getData()));
                        try {
                            if (object.getInt("hide")==1){
                                switvh.setChecked(false);
                            }else if (object.getInt("hide")==0){
                                switvh.setChecked(true);
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initView() {
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        textHeadTitle.setText("隐私设置");

        switvh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,Object> map = new HashMap<>();
                if (switvh.isChecked()){
                    map.put("filter","0");
                }else {
                    map.put("filter","1");
                }
                map.put("rid",rid);
                OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.OPEN_SECRET, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
//                        initData();
                    }
                });
            }
        });
    }
}
