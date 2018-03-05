package com.youzheng.tongxiang.huntingjob.Prestener.fragment.resume;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.codbking.widget.DatePickDialog;
import com.codbking.widget.OnSureLisener;
import com.codbking.widget.bean.DateType;
import com.squareup.okhttp.Request;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.Event.SelectJianLiBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.AreaInfoBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.AreaInfoChildBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.EducationBean;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.DescribeDetailsActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.fragment.BaseFragment;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.SharedPreferencesUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;

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

public class UserResumeOnePageFragment extends BaseFragment {


    @BindView(R.id.edt_name)
    EditText edtName;
    Unbinder unbinder;
    @BindView(R.id.tv_brith)
    TextView tvBrith;
    @BindView(R.id.cb)
    RadioButton cb;
    @BindView(R.id.cb_woman)
    RadioButton cbWoman;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.edt_zhiwei)
    EditText edtZhiwei;
    @BindView(R.id.edt_money)
    TextView edtMoney;
    @BindView(R.id.iv_money)
    ImageView ivMoney;
    @BindView(R.id.edt_address)
    TextView edtAddress;
    @BindView(R.id.iv_address)
    ImageView ivAddress;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textHeadNext)
    TextView textHeadNext;
    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    @BindView(R.id.sv)
    ScrollView sv;
    @BindView(R.id.tv_hangye)
    TextView tvHangye;
    @BindView(R.id.iv_hangye)
    ImageView ivHangye;
    @BindView(R.id.tv_riqi)
    ImageView tvRiqi;
    @BindView(R.id.iv_city_address)
    ImageView ivCityAddress;
    @BindView(R.id.iv_status)
    ImageView ivStatus;
    @BindView(R.id.rg)
    RadioGroup rg;
    private SelectJianLiBean jianLiBean;
    private OptionsPickerView pvCustomTime;

    private String citys, hope_citys, hangye_id;
    private int state_id, money_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static UserResumeOnePageFragment getInstance(SelectJianLiBean bean) {
        UserResumeOnePageFragment fragment = new UserResumeOnePageFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", bean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_resume_one_page_layout, null);
        unbinder = ButterKnife.bind(this, view);

        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((DescribeDetailsActivity) mContext).finish();
            }
        });
        textHeadTitle.setVisibility(View.GONE);
        textHeadNext.setVisibility(View.VISIBLE);
        textHeadNext.setText("确定");
        final int rid = (int) SharedPreferencesUtils.getParam(mContext, SharedPreferencesUtils.rid, 0);
        textHeadNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<>();
                map.put("rid", rid);
                map.put("telephone", edtPhone.getText().toString());
                map.put("email", edtEmail.getText().toString());
                int sex;
                if (cb.isChecked()) {
                    sex = 1;
                } else {
                    sex = 0;
                }
                map.put("gender", sex);
                if (tvBrith.getText().toString().equals("")) {
                    showToast("选择出生日期");
                    return;
                }
                if (edtPhone.getText().toString().equals("")) {
                    showToast("选择联系电话");
                    return;
                }
                if (tvCity.getText().toString().equals("")) {
                    showToast("选择所在城市");
                    return;
                }
                if (tvStatus.getText().toString().equals("")) {
                    showToast("选择状态");
                    return;
                }
                if (edtZhiwei.getText().toString().equals("")) {
                    showToast("选择期望职位");
                    return;
                }
                if (edtMoney.getText().toString().equals("")) {
                    showToast("选择期望月薪");
                    return;
                }
                if (edtAddress.getText().toString().equals("")) {
                    showToast("选择期望工作地点");
                    return;
                }

                map.put("birthdate", tvBrith.getText().toString());
                map.put("city", citys);
                map.put("hope_city", hope_citys);
                map.put("state", state_id);
                map.put("trade", hangye_id);
                map.put("position", edtZhiwei.getText().toString());
                map.put("wage", money_id);
                OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.UPDATE_BASE_INFO, new OkHttpClientManager.StringCallback() {
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
            }
        });

        if (getArguments() != null) {
            jianLiBean = (SelectJianLiBean) getArguments().getSerializable("data");
            edtName.setText(jianLiBean.getJi().getResume().getTruename());
            edtName.setEnabled(false);
            tvBrith.setText(jianLiBean.getJi().getResume().getBirthdate());
            if (jianLiBean.getJi().getResume().getGender() == 1) {
                cb.setChecked(true);
            } else {
                cbWoman.setChecked(true);
            }
            edtPhone.setText(jianLiBean.getJi().getResume().getTelephone());
            edtZhiwei.setText(jianLiBean.getJi().getResume().getPosition());
            edtEmail.setText(jianLiBean.getJi().getResume().getEmail());
            tvCity.setText(jianLiBean.getJi().getResume().getCitysName());
            edtMoney.setText(jianLiBean.getJi().getResume().getSalary());
            edtAddress.setText(jianLiBean.getJi().getResume().getHopeCityName());
            citys = jianLiBean.getJi().getResume().getCitys();
            hope_citys = jianLiBean.getJi().getResume().getHope_city();
            tvHangye.setText(jianLiBean.getJi().getResume().getTradeName());
            hangye_id = jianLiBean.getJi().getResume().getTrade_id();
            money_id = jianLiBean.getJi().getResume().getWage();
            if (jianLiBean.getJi().getResume().getStateName() != null) {
                tvStatus.setText(jianLiBean.getJi().getResume().getStateName());
                state_id = Integer.parseInt(jianLiBean.getJi().getResume().getState());
            }
        }


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_riqi, R.id.iv_city_address, R.id.iv_address, R.id.iv_status, R.id.iv_money})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.tv_riqi:
                initTime();
                break;
            case R.id.iv_city_address:
                initplace("1");
                break;
            case R.id.iv_address:
                initplace("0");
                break;
            case R.id.iv_money:
                Map<String, Object> money_map = new HashMap<>();
                money_map.put("ctype", "wage");
                OkHttpClientManager.postAsynJson(gson.toJson(money_map), UrlUtis.WORK_TIAOJIAN, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                        if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                            final EducationBean comclass = gson.fromJson(gson.toJson(entity.getData()), EducationBean.class);
                            if (comclass.getComclass().size() > 0) {
                                final List<String> date = new ArrayList<String>();
                                for (int i = 0; i < comclass.getComclass().size(); i++) {
                                    date.add(comclass.getComclass().get(i).getName());
                                }
                                pvCustomTime = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        for (int i = 0; i < comclass.getComclass().size(); i++) {
                                            if (date.get(options1).equals(comclass.getComclass().get(i).getName())) {
                                                edtMoney.setText(date.get(options1));
                                                money_id = comclass.getComclass().get(i).getId();
                                            }
                                        }
                                    }
                                }).setTitleText("选择月薪").build();
                                pvCustomTime.setPicker(date);
                                pvCustomTime.show();
                            }
                        }
                    }
                });
                break;

            case R.id.iv_status:
                Map<String, Object> map = new HashMap<>();
                map.put("ctype", "current");
                OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.WORK_TIAOJIAN, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                        if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                            final EducationBean comclass = gson.fromJson(gson.toJson(entity.getData()), EducationBean.class);
                            if (comclass.getComclass().size() > 0) {
                                final List<String> date = new ArrayList<String>();
                                for (int i = 0; i < comclass.getComclass().size(); i++) {
                                    date.add(comclass.getComclass().get(i).getName());
                                }
                                pvCustomTime = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
                                    @Override
                                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                                        for (int i = 0; i < comclass.getComclass().size(); i++) {
                                            if (date.get(options1).equals(comclass.getComclass().get(i).getName())) {
                                                tvStatus.setText(date.get(options1));
                                                state_id = comclass.getComclass().get(i).getId();
                                            }
                                        }
                                    }
                                }).setTitleText("当前状态").build();
                                pvCustomTime.setPicker(date);
                                pvCustomTime.show();
                            }
                        }
                    }
                });
                break;

        }
    }

    private void initTime() {
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
                tvBrith.setText(dateStr);
            }
        });
        dialog.show();
    }

    public void initplace(final String type) {
        String add = (String) SharedPreferencesUtils.getParam(mContext, SharedPreferencesUtils.AREAALL, "");
        if (!add.equals("")) {
            initAddress(add, type);
        } else {
            OkHttpClientManager.postAsynJson("", UrlUtis.ALL_ADDRESS, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                    if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                        initAddress(response, type);
                    }
                }
            });
        }
    }

    private List<AreaInfoChildBean> options1Items = new ArrayList<>();
    private List<List<AreaInfoChildBean>> options2Items = new ArrayList<>();
    private List<List<List<AreaInfoChildBean>>> options3Items = new ArrayList<>();
    private List<String> list_data = new ArrayList<>();
    private List<List<String>> list_data_two = new ArrayList<>();
    private List<List<List<String>>> list_data_third = new ArrayList<>();

    private void initAddress(String areaall, String type) {
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
            initDialog(type);
            SharedPreferencesUtils.setParam(mContext, SharedPreferencesUtils.AREAALL, areaall);
        }

    }

    private void initDialog(final String type) {
        OptionsPickerView pickerView = new OptionsPickerView.Builder(mContext, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                String tx = options1Items.get(options1).getAreaName() +
                        options2Items.get(options1).get(options2).getAreaName() +
                        options3Items.get(options1).get(options2).get(options3).getAreaName();
                if (type.equals("1")) {
                    citys = options1Items.get(options1).getId() + "," + options2Items.get(options1).get(options2).getId() + "," + options3Items.get(options1).get(options2).get(options3).getId();
                    if (tx != null && !tx.equals("")) {
                        tvCity.setText(tx);
                    }
                } else if (type.equals("0")) { //期望地址
                    hope_citys = options1Items.get(options1).getId() + "," + options2Items.get(options1).get(options2).getId() + "," + options3Items.get(options1).get(options2).get(options3).getId();
                    if (tx != null && !tx.equals("")) {
                        edtAddress.setText(tx);
                    }
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
