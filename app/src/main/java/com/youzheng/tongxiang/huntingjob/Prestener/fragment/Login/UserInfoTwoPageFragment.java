package com.youzheng.tongxiang.huntingjob.Prestener.fragment.Login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.squareup.okhttp.Request;
import com.youzheng.tongxiang.huntingjob.MainActivity;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.HangYeList;
import com.youzheng.tongxiang.huntingjob.Model.entity.Job.HanyeBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.AreaInfoBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.AreaInfoChildBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.EducationBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.UserBaseBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.user.UserBean;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.FillInfoActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.BaseFragment;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.SharedPreferencesUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;
import com.youzheng.tongxiang.huntingjob.UI.dialog.RegisterSuccessDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by qiuweiyu on 2018/2/8.
 */

public class UserInfoTwoPageFragment extends BaseFragment {

    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.btn_login)
    Button btnLogin;
    Unbinder unbinder;
    @BindView(R.id.iv_select_time)
    ImageView ivSelectTime;
    @BindView(R.id.tv_time_time)
    TextView tvTimeTime;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.iv_address)
    ImageView ivAddress;

    public String hope_city;
    @BindView(R.id.tv_hangye)
    TextView tvHangye;
    @BindView(R.id.iv_hangye)
    ImageView ivHangye;

    private UserBaseBean baseBean;
    private OptionsPickerView pvCustomTime;
    private String hanye ;

    private ArrayList<String> one_list = new ArrayList<>();
    private ArrayList<ArrayList<String>> two_list = new ArrayList<>();
    private ArrayList<HanyeBean> hang_list_one = new ArrayList<>();
    private ArrayList<ArrayList<HanyeBean>> hang_list_two = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_info_two_page_layout, null);
        unbinder = ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    @OnClick({R.id.btn_login, R.id.iv_select_time, R.id.iv_address,R.id.iv_hangye})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                if (tvTimeTime.getText().toString().equals("")) {
                    showToast("填写开始工作时间");
                    return;
                }
                if (edtPhone.getText().toString().equals("")) {
                    showToast("填写手机号");
                    return;
                }
                if (edtEmail.getText().toString().equals("")) {
                    showToast("填写电子邮箱");
                    return;
                }
                if (tvCity.getText().toString().equals("")) {
                    showToast("填写所在城市");
                    return;
                }
                if (tvHangye.getText().toString().equals("")){
                    showToast("填写行业");
                    return;
                }

                if (baseBean != null) {
                    baseBean.setResidence(hope_city);
                    baseBean.setWork_time(tvTimeTime.getText().toString());
                    baseBean.setTelephone(edtPhone.getText().toString());
                    baseBean.setEmail(edtEmail.getText().toString());
                    baseBean.setTrade(hanye);
                }
                OkHttpClientManager.postAsynJson(gson.toJson(baseBean), UrlUtis.BASE_RESUME, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                        if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                            try {
                                JSONObject object = new JSONObject(gson.toJson(entity.getData()));
                                int rid = object.getInt("rid");
                                SharedPreferencesUtils.setParam(mContext,SharedPreferencesUtils.rid,rid);
                                mContext.startActivity(new Intent(mContext, MainActivity.class));
                                ((FillInfoActivity)mContext).finish();
//                                dologin();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
//                startActivity(new Intent(mContext, UserResumeActivity.class));
                break;
            case R.id.iv_select_time:
                initTime();
                break;
            case R.id.iv_address:
                String add = (String) SharedPreferencesUtils.getParam(mContext, SharedPreferencesUtils.AREAALL, "");
                if (!add.equals("")) {
                    initAddress(add);
                } else {
                    OkHttpClientManager.postAsynJson("", UrlUtis.ALL_ADDRESS, new OkHttpClientManager.StringCallback() {
                        @Override
                        public void onFailure(Request request, IOException e) {

                        }

                        @Override
                        public void onResponse(String response) {
                            BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                            if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                                initAddress(response);
                            }
                        }
                    });
                }
                break;

            case R.id.iv_hangye:
                Map<String,Object> map = new HashMap<>();
                OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.ALL_TRADE, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseEntity entity = gson.fromJson(response,BaseEntity.class);
                        if (entity.getCode().equals(PublicUtils.SUCCESS)){
                            HangYeList list = gson.fromJson(gson.toJson(entity.getData()),HangYeList.class);
                            hang_list_one.addAll(list.getTradeList());
                            for (int q = 0 ; q<hang_list_one.size();q++){
                                one_list.add(hang_list_one.get(q).getTradeName());
                                ArrayList<String> data = new ArrayList<String>();
                                ArrayList<HanyeBean> d = new ArrayList<HanyeBean>();
                                for (int j = 0 ;j<hang_list_one.get(q).getChilds().size();j++){
                                    data.add(hang_list_one.get(q).getChilds().get(j).getTradeName());
                                    d.add(hang_list_one.get(q).getChilds().get(j));
                                }
                                two_list.add(data);
                                hang_list_two.add(d);
                            }

                            pvCustomTime = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
                                @Override
                                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                    String tx = hang_list_one.get(options1).getTradeName()+
                                            hang_list_two.get(options1).get(options2).getTradeName();
                                    tvHangye.setText(tx);
                                    hanye = hang_list_one.get(options1).getId()+","+hang_list_two.get(options1).get(options2).getId();
                                }
                            }).setTitleText("选择行业").build();
                            pvCustomTime.setPicker(one_list,two_list);
                            pvCustomTime.show();
                        }
                    }
                });

                break;
        }
    }

    private void dologin() {
        Map<String, Object> map = new HashMap<>();
        String access_token = (String) SharedPreferencesUtils.getParam(mContext, SharedPreferencesUtils.access_token, "");
        map.put("access_token", access_token);
        OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.TOKEN_LOGIN, new OkHttpClientManager.StringCallback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(String response) {
                BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                    UserBean bean = gson.fromJson(gson.toJson(entity.getData()),UserBean.class);
                    SharedPreferencesUtils.setParam(mContext,SharedPreferencesUtils.access_token,bean.getAccess_token());
                    SharedPreferencesUtils.setParam(mContext,SharedPreferencesUtils.uid,bean.getUid());

                    mContext.startActivity(new Intent(mContext, MainActivity.class));
                }
            }
        });
    }


    @Subscribe
    public void onEvent(UserBaseBean s) {
        if (s != null) {
            baseBean = s;
        }
    }

    private List<AreaInfoChildBean> options1Items = new ArrayList<>();
    private List<List<AreaInfoChildBean>> options2Items = new ArrayList<>();
    private List<List<List<AreaInfoChildBean>>> options3Items = new ArrayList<>();
    private List<String> list_data = new ArrayList<>();
    private List<List<String>> list_data_two = new ArrayList<>();
    private List<List<List<String>>> list_data_third = new ArrayList<>();

    private void initTime() {
        DatePickDialog dialog = new DatePickDialog(mContext);
        //设置上下年分限制
        dialog.setYearLimt(80);
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
                tvTimeTime.setText(dateStr);
            }
        });
        dialog.show();
    }

    private void initAddress(String areaall) {
        AreaInfoBean bean = gson.fromJson(areaall, AreaInfoBean.class);
        options1Items.addAll(bean.getData().get("areaList"));

        for (int q = 0; q < options1Items.size(); q++) {
            list_data.add(options1Items.get(q).getAreaName());
        }


        for (int i = 0; i < options1Items.size(); i++) {
            List<AreaInfoChildBean> CityList = new ArrayList<>();//该省的城市列表（第二级）
            List<List<AreaInfoChildBean>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            List<String> c_city = new ArrayList<>();
            List<List<String>> p_city = new ArrayList<>();


            for (int c = 0; c < options1Items.get(i).getChilds().size(); c++) {
                AreaInfoChildBean childBean = options1Items.get(i).getChilds().get(c);
                CityList.add(childBean);//添加城市

                c_city.add(options1Items.get(i).getChilds().get(c).getAreaName());


                List<AreaInfoChildBean> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                List<String> c_list = new ArrayList<>();

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (options1Items.get(i).getChilds().get(c).getAreaName() == null
                        || options1Items.get(i).getChilds().get(c).getChilds().size() == 0) {
                    City_AreaList.add(new AreaInfoChildBean("", "", null));
                    c_list.add("");
                } else {
                    for (int d = 0; d < options1Items.get(i).getChilds().get(c).getChilds().size(); d++) {//该城市对应地区所有数据
                        AreaInfoChildBean AreaName = options1Items.get(i).getChilds().get(c).getChilds().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                        c_list.add(AreaName.getAreaName());
                    }
                    Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                    p_city.add(c_list);
                }
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);

            list_data_two.add(c_city);
            list_data_third.add(p_city);
        }

        if (options2Items.size() > 0 && options3Items.size() > 0 && options1Items.size() > 0) {
            initDialog();
            SharedPreferencesUtils.setParam(mContext, SharedPreferencesUtils.AREAALL, areaall);
        }

    }

    private void initDialog() {
        OptionsPickerView pickerView = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1Items.get(options1).getAreaName() +
                        options2Items.get(options1).get(options2).getAreaName() +
                        options3Items.get(options1).get(options2).get(options3).getAreaName();

                hope_city = options1Items.get(options1).getId() + "," + options2Items.get(options1).get(options2).getId() + "," + options3Items.get(options1).get(options2).get(options3).getId();
                if (tx != null && !tx.equals("")) {
                    tvCity.setText(tx);
                }

                Toast.makeText(mContext, tx, Toast.LENGTH_SHORT).show();
            }
        }).setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();

        pickerView.setPicker(list_data, list_data_two, list_data_third);//三级选择器
        pickerView.show();
    }
}
