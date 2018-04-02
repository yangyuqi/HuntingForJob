package com.youzheng.tongxiang.huntingjob.Prestener.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.donkingliang.labels.LabelsView;
import com.squareup.okhttp.Request;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.youzheng.tongxiang.huntingjob.MainActivity;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.Event.SelectJianLiBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.BaseJianli;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.EducitionBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.ProjectBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.SkillListBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.jianli.WorkExperenceBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.user.PhotoBean;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.DescribeDetailsActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.LoginActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.User.UserCenterActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.WorkExperienceActivity;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.CommonAdapter;
import com.youzheng.tongxiang.huntingjob.UI.Adapter.ViewHolder;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;
import com.youzheng.tongxiang.huntingjob.UI.Widget.CircleImageView;
import com.youzheng.tongxiang.huntingjob.UI.Widget.NoScrollListView;
import com.youzheng.tongxiang.huntingjob.UI.dialog.BottomPhotoDialog;
import com.youzheng.tongxiang.huntingjob.UI.dialog.DeleteDialog;
import com.youzheng.tongxiang.huntingjob.UI.dialog.DeleteDialogInterface;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.utils.ImageCaptureManager;
import rx.functions.Action1;

import static android.app.Activity.RESULT_OK;

/**
 * Created by qiuweiyu on 2018/2/7.
 */

public class JianLiFragment extends BaseFragment {

    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    Unbinder unbinder;
    @BindView(R.id.rl_desc)
    RelativeLayout rlDesc;
    @BindView(R.id.rl_jinyan)
    RelativeLayout rlJinyan;
    @BindView(R.id.rl_study)
    RelativeLayout rlStudy;
    @BindView(R.id.rl_jineng)
    RelativeLayout rlJineng;
    @BindView(R.id.rl_pingjia)
    RelativeLayout rlPingjia;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_sex)
    TextView tvSex;
    @BindView(R.id.ll_name_2)
    LinearLayout llName2;
    @BindView(R.id.tv_class)
    TextView tvClass;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_email)
    TextView tvEmail;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_brith)
    TextView tvBrith;
    @BindView(R.id.tv_work_for)
    TextView tvWorkFor;
    @BindView(R.id.tv_work_dus)
    TextView tvWorkDus;
    @BindView(R.id.tv_work_place)
    TextView tvWorkPlace;
    @BindView(R.id.tv_work_money)
    TextView tvWorkMoney;
    @BindView(R.id.tv_experience)
    TextView tvExperience;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.ls_experence)
    NoScrollListView lsExperence;
    @BindView(R.id.tv_project)
    TextView tvProject;
    @BindView(R.id.ls_project)
    NoScrollListView lsProject;
    @BindView(R.id.tv_jiaoyue)
    TextView tvJiaoyue;
    @BindView(R.id.ls_jiaoyue)
    NoScrollListView lsJiaoyue;
    @BindView(R.id.tv_jineng)
    TextView tvJineng;
    @BindView(R.id.labelView)
    LabelsView labelView;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    @BindView(R.id.tv_desc_desc)
    TextView tvDescDesc;
    @BindView(R.id.iv_co_icon)
    CircleImageView ivCoIcon;
    @BindView(R.id.ll_id)
    LinearLayout llId;
    @BindView(R.id.ll_ll)
    LinearLayout llLl;
    ImageCaptureManager captureManager ;

    private CommonAdapter<WorkExperenceBean> ex_adapter;
    private List<WorkExperenceBean> ex_data = new ArrayList<>();

    private CommonAdapter<ProjectBean> pr_adapter;
    private List<ProjectBean> pr_data = new ArrayList<>();

    private CommonAdapter<EducitionBean> ed_adapter;
    private List<EducitionBean> ed_data = new ArrayList<>();

    private CommonAdapter<SkillListBean> sk_adapter;
    private List<SkillListBean> sk_data = new ArrayList<>();

    private String which;
    @BindView(R.id.rl_work)
    RelativeLayout rlWork;

    BaseJianli jianli;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.jianli_layout, null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!accessToken.equals("")) {
            initData();
        } else {
        }
    }

    private void initData() {
        if (!accessToken.equals("")) {
            Map<String, Object> map = new HashMap<>();
            map.put("accessToken", accessToken);
            OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.MY_RESUME, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                    if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                        BaseJianli baseJianli = gson.fromJson(gson.toJson(entity.getData()), BaseJianli.class);
                        jianli = baseJianli;
                        tvName.setText(baseJianli.getResume().getTruename());
                        if (baseJianli.getResume().getGender() == 1) {
                            tvSex.setText("男");
                        } else {
                            tvSex.setText("女");
                        }
                        if (baseJianli.getResume().getPhoto()!=null) {
                            Glide.with(mContext).load(baseJianli.getResume().getPhoto()).into(ivCoIcon);
                        }
                        tvClass.setText(baseJianli.getResume().getEducation() +"  |  "+ baseJianli.getResume().getCitysName()+"  |  " + baseJianli.getResume().getWork_year()+"工作经验");
                        tvPhone.setText(baseJianli.getResume().getTelephone());
                        tvEmail.setText(baseJianli.getResume().getEmail());
                        tvBrith.setText(baseJianli.getResume().getBirthdate());
                        tvStatus.setText(baseJianli.getResume().getStateName());
                        tvWorkFor.setText(baseJianli.getResume().getPosition());
                        tvWorkDus.setText("期望行业 :" + baseJianli.getResume().getTradeName());
                        tvWorkPlace.setText("期望地点 :" + baseJianli.getResume().getHopeCityName());
                        tvWorkMoney.setText("期望月薪 :" + baseJianli.getResume().getSalary());

                        if (baseJianli.getExperienceList().size() > 0) {
                            tvExperience.setVisibility(View.GONE);
                            lsExperence.setVisibility(View.VISIBLE);
                            ex_adapter.setData(baseJianli.getExperienceList());
                            ex_adapter.notifyDataSetChanged();
                        } else {
                            tvExperience.setVisibility(View.VISIBLE);
                            lsExperence.setVisibility(View.GONE);
                        }

                        if (baseJianli.getProjectList().size() > 0) {
                            tvProject.setVisibility(View.GONE);
                            lsProject.setVisibility(View.VISIBLE);
                            pr_adapter.setData(baseJianli.getProjectList());
                            pr_adapter.notifyDataSetChanged();
                        } else {
                            lsProject.setVisibility(View.GONE);
                            tvProject.setVisibility(View.VISIBLE);
                        }

                        if (baseJianli.getEducationList().size() > 0) {
                            tvJiaoyue.setVisibility(View.GONE);
                            lsJiaoyue.setVisibility(View.VISIBLE);
                            ed_adapter.setData(baseJianli.getEducationList());
                            ed_adapter.notifyDataSetChanged();
                        } else {
                            lsJiaoyue.setVisibility(View.GONE);
                            tvJiaoyue.setVisibility(View.VISIBLE);
                        }

                        if (baseJianli.getSkillList().size() > 0) {
                            tvJineng.setVisibility(View.GONE);
                            labelView.setVisibility(View.VISIBLE);
                            ArrayList<String> data = new ArrayList<String>();
                            for (int i = 0; i < baseJianli.getSkillList().size(); i++) {
                                data.add(baseJianli.getSkillList().get(i).getSkill() + "  " + baseJianli.getSkillList().get(i).getDegree());
                            }
                            labelView.setLabels(data);
                        } else {
                            tvJineng.setVisibility(View.VISIBLE);
                            labelView.setVisibility(View.GONE);
                        }

                        if (baseJianli.getResume().getSelf_description() == null) {
                            tvDescDesc.setVisibility(View.GONE);
                            tvDesc.setVisibility(View.VISIBLE);
                        } else {
                            tvDesc.setVisibility(View.GONE);
                            tvDescDesc.setVisibility(View.VISIBLE);
                            tvDescDesc.setText(baseJianli.getResume().getSelf_description());
                        }

                    }
                }
            });
        }
    }

    private void initView() {
        textHeadTitle.setText("简历");

        ex_adapter = new CommonAdapter<WorkExperenceBean>(mContext, ex_data, R.layout.home_experence_ls_item) {
            @Override
            public void convert(ViewHolder helper, WorkExperenceBean item) {
                helper.setText(R.id.tv_co, item.getCompany());
                helper.setText(R.id.tv_time, item.getStart_time() + "至" + item.getEnd_time() + "    " + item.getPosition());
                helper.setText(R.id.tv_desc, item.getDescription());
            }
        };
        lsExperence.setAdapter(ex_adapter);
        lsExperence.setFocusable(false);

        pr_adapter = new CommonAdapter<ProjectBean>(mContext, pr_data, R.layout.home_experence_ls_item) {
            @Override
            public void convert(ViewHolder helper, ProjectBean item) {
                helper.setText(R.id.tv_co, item.getName());
                helper.setText(R.id.tv_time, item.getStart_time() + "至" + item.getEnd_time() + "    ");
                helper.setText(R.id.tv_desc, item.getDescription());
            }
        };
        lsProject.setAdapter(pr_adapter);
        lsProject.setFocusable(false);

        ed_adapter = new CommonAdapter<EducitionBean>(mContext, ed_data, R.layout.home_experence_ls_item) {
            @Override
            public void convert(ViewHolder helper, EducitionBean item) {
                helper.setText(R.id.tv_co, item.getSchool());
                helper.setText(R.id.tv_time, item.getStarttime() + "至" + item.getEndtime() + "       " + item.getEducation() + "      " + item.getMajor());
                helper.setText(R.id.tv_desc, "" + item.getDescription());
            }
        };
        lsJiaoyue.setFocusable(false);
        lsJiaoyue.setAdapter(ed_adapter);
    }

    @OnClick({R.id.rl_desc, R.id.rl_work, R.id.rl_jinyan, R.id.rl_study, R.id.rl_jineng, R.id.rl_pingjia,R.id.iv_co_icon})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.rl_desc:
                which = "1";
                startActivity(new Intent(mContext, DescribeDetailsActivity.class));
                break;
            case R.id.rl_work:
                which = "2";
                startActivity(new Intent(mContext, WorkExperienceActivity.class));
                break;
            case R.id.rl_jinyan:
                which = "3";
                startActivity(new Intent(mContext, WorkExperienceActivity.class));
                break;
            case R.id.rl_study:
                which = "4";
                startActivity(new Intent(mContext, WorkExperienceActivity.class));
                break;
            case R.id.rl_jineng:
                which = "5";
                startActivity(new Intent(mContext, DescribeDetailsActivity.class));
                break;
            case R.id.rl_pingjia:
                which = "6";
                startActivity(new Intent(mContext, DescribeDetailsActivity.class));
                break;
            case R.id.iv_co_icon:
                final BottomPhotoDialog dialog = new BottomPhotoDialog(mContext, R.layout.view_popupwindow);
                dialog.show();
                dialog.getTv_pick_phone().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        takePhoto();
                    }
                });

                dialog.getTv_select_photo().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        selectPhoto();
                    }
                });
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onStop() {
        super.onStop();
        if (which != null) {
            SelectJianLiBean selectJianLiBean = new SelectJianLiBean();
            BaseJianli baseJianli = new BaseJianli();
            switch (which) {
                case "1":
                    if (jianli != null) {
                        baseJianli.setResume(jianli.getResume());
                        selectJianLiBean.setJi(baseJianli);
                    }
                    selectJianLiBean.setType("1");
                    EventBus.getDefault().post(selectJianLiBean);
                    break;
                case "2":
                    selectJianLiBean.setType("2");
                    baseJianli.setExperienceList(jianli.getExperienceList());
                    selectJianLiBean.setJi(baseJianli);
                    EventBus.getDefault().post(selectJianLiBean);
                    break;
                case "3":
                    selectJianLiBean.setType("3");
                    baseJianli.setProjectList(jianli.getProjectList());
                    selectJianLiBean.setJi(baseJianli);
                    EventBus.getDefault().post(selectJianLiBean);
                    break;
                case "4":
                    selectJianLiBean.setType("4");
                    baseJianli.setEducationList(jianli.getEducationList());
                    selectJianLiBean.setJi(baseJianli);
                    EventBus.getDefault().post(selectJianLiBean);
                    break;
                case "5":
                    selectJianLiBean.setType("5");
                    baseJianli.setSkillList(jianli.getSkillList());
                    selectJianLiBean.setJi(baseJianli);
                    EventBus.getDefault().post(selectJianLiBean);
                    break;
                case "6":
                    if (jianli != null) {
                        baseJianli.setResume(jianli.getResume());
                        selectJianLiBean.setJi(baseJianli);
                    }
                    selectJianLiBean.setType("6");
                    EventBus.getDefault().post(selectJianLiBean);
                    break;

            }
        }
    }

    private void takePhoto() {
        RxPermissions permissions = new RxPermissions((MainActivity)mContext);
        permissions.request(Manifest.permission.CAMERA,Manifest.permission.VIBRATE,Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean){
                    captureManager = new ImageCaptureManager(mContext);
                    Intent intent = null;
                    try {
                        intent = captureManager.dispatchTakePictureIntent();
                        startActivityForResult(intent, ImageCaptureManager.REQUEST_TAKE_PHOTO);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void selectPhoto() {
        RxPermissions permissions = new RxPermissions((MainActivity)mContext);
        permissions.request(Manifest.permission.CAMERA,Manifest.permission.VIBRATE,Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (aBoolean){
                    //选择相册
                    PhotoPicker.builder()
                            .setPhotoCount(1)
                            .setShowCamera(true)
                            .setShowGif(true)
                            .setPreviewEnabled(false)
                            .start(mContext ,JianLiFragment.this,PhotoPicker.REQUEST_CODE);
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                // 拍照
                case ImageCaptureManager.REQUEST_TAKE_PHOTO:
                    if(captureManager.getCurrentPhotoPath() != null) {
                        captureManager.galleryAddPic();
                        // 照片地址
                        String imagePaht = captureManager.getCurrentPhotoPath();
                        updatePhoto(imagePaht);
                    }
                    break;
            }
        }
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE){
            if (data != null) {
                ArrayList<String> photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                String imagePaht = photos.get(0) ;
                updatePhoto(imagePaht);
            }
        }
    }
    private void updatePhoto(String imagePaht) {
        if (imagePaht!=null) {
            File file = new File(imagePaht);
            try {
                OkHttpClientManager.postAsyn(UrlUtis.UPLOAD_FILE, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        BaseEntity entity = gson.fromJson(response,BaseEntity.class);
                        if (entity.getCode().equals(PublicUtils.SUCCESS)){
                            PhotoBean photoBean = gson.fromJson(gson.toJson(entity.getData()),PhotoBean.class);
                            showToast(photoBean.getInfo());
                            Map<String,Object> h_map = new HashMap<>();
                            h_map.put("rid",rid);
                            h_map.put("photo",photoBean.getFilepath());
                            OkHttpClientManager.postAsynJson(gson.toJson(h_map), UrlUtis.EDIT_RESUME_INFO, new OkHttpClientManager.StringCallback() {
                                @Override
                                public void onFailure(Request request, IOException e) {

                                }

                                @Override
                                public void onResponse(String response) {
                                    BaseEntity entity1 = gson.fromJson(response,BaseEntity.class);
                                    if (entity1.getCode().equals(PublicUtils.SUCCESS)){
                                        initData();
                                    }
                                }
                            });
                        }
                    }
                },file,"file");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
