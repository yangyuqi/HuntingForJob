package com.youzheng.tongxiang.huntingjob.Prestener.fragment.resume;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.squareup.okhttp.Request;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.Event.SelectJianLiBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.ProjectBean;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.DescribeDetailsActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.BaseFragment;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by qiuweiyu on 2018/2/8.
 */

public class UserResumeFourPageFragment extends BaseFragment {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    @BindView(R.id.textHeadNext)
    TextView textHeadNext;
    @BindView(R.id.tv_work_start)
    TextView tvWorkStart;
    @BindView(R.id.iv_rl1)
    ImageView ivRl1;
    @BindView(R.id.tv_brith)
    TextView tvBrith;
    @BindView(R.id.iv_rl2)
    ImageView ivRl2;
    @BindView(R.id.edt_xm)
    EditText edtXm;
    @BindView(R.id.edt_desc)
    EditText edtDesc;
    Unbinder unbinder;

    private ProjectBean bean ;
     int id = -10 ;
    public static UserResumeFourPageFragment getInstance(SelectJianLiBean bean) {
        UserResumeFourPageFragment fragment = new UserResumeFourPageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", bean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_resume_four_page_layout, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        if (getArguments()!=null){
            SelectJianLiBean jianLiBean = (SelectJianLiBean) getArguments().getSerializable("data");
            id = jianLiBean.getProject_id();
            for (int j = 0 ;j<jianLiBean.getJi().getProjectList().size();j++){
                if (jianLiBean.getJi().getProjectList().get(j).getId()==id){
                    bean = jianLiBean.getJi().getProjectList().get(j);
                }
            }
            if (bean!=null){
                tvWorkStart.setText(bean.getStart_time());
                tvBrith.setText(bean.getEnd_time());
                edtXm.setText(bean.getName());
                edtDesc.setText(bean.getDescription());
            }
        }
        return view;
    }

    private void initView() {
        textHeadNext.setText("确定");
        textHeadTitle.setVisibility(View.GONE);
        btnBack.setVisibility(View.VISIBLE);
        textHeadNext.setVisibility(View.VISIBLE);

        ivRl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTime(0);
            }
        });
        ivRl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initTime(1);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((DescribeDetailsActivity)mContext).finish();
            }
        });

    }
    @OnClick({R.id.textHeadNext})
    public void onClick(View view){
        if (edtXm.getText().toString().equals("")) {
            showToast("请填写项目名称");
            return;
        }
        if (tvWorkStart.getText().toString().equals("")) {
            showToast("请填写项目开始时间");
            return;
        }
        if (tvBrith.getText().toString().equals("")) {
            showToast("请填写项目结束时间");
            return;
        }
        if (edtDesc.getText().toString().equals("")) {
            showToast("请填写项目描述");
            return;
        }
        Log.e("sssssssss",String.valueOf(id));
        if (id==-10||id==0) {
            Map<String, Object> map = new HashMap<>();
            map.put("rid", rid);
            map.put("name", edtXm.getText().toString());
            map.put("start_time", tvWorkStart.getText().toString());
            map.put("end_time", tvBrith.getText().toString());
            map.put("description", edtDesc.getText().toString());
            OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.ADD_PROJECT, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                    if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                        ((DescribeDetailsActivity) mContext).finish();
                    } else {
                        showToast(entity.getMsg());
                    }
                }
            });
        }else {
            Map<String,Object> map = new HashMap<>();
            map.put("rid",rid);
            map.put("name",edtXm.getText().toString());
            map.put("flag","1");
            map.put("rpid",id);
            map.put("start_time",tvWorkStart.getText().toString());
            map.put("end_time",tvBrith.getText().toString());
            map.put("description",edtDesc.getText().toString());
            OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.UPDATE_PROJECT, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    BaseEntity entity = gson.fromJson(response,BaseEntity.class);
                    if (entity.getCode().equals(PublicUtils.SUCCESS)){
                        ((DescribeDetailsActivity)mContext).finish();
                    }else {
                        showToast(entity.getMsg());
                    }
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void initTime(final int type) {
        DatePickDialog dialog = new DatePickDialog(mContext);
        //设置上下年分限制
        dialog.setYearLimt(30);
        //设置标题
        dialog.setTitle("选择时间");
        //设置类型
        dialog.setType(DateType.TYPE_YMD);
        //设置消息体的显示格式，日期格式
        dialog.setMessageFormat("yyyy-MM-dd");
        //设置选择回调
        dialog.setOnChangeLisener(null);
        //设置点击确定按钮回调
        dialog.setOnSureLisener(new OnSureLisener() {
            @Override
            public void onSure(Date date) {
                String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                if (type == 0) {
                    tvWorkStart.setText(dateStr);
                } else if (type == 1) {
                    tvBrith.setText(dateStr);
                }
            }
        });
        dialog.show();
    }
}
