package com.youzheng.tongxiang.huntingjob.Prestener.activity.User;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.Request;
import com.tbruyelle.rxpermissions.RxPermissions;
import com.youzheng.tongxiang.huntingjob.Model.Event.BaseEntity;
import com.youzheng.tongxiang.huntingjob.Model.entity.user.PhotoBean;
import com.youzheng.tongxiang.huntingjob.Model.entity.user.UserInfoSeeBean;
import com.youzheng.tongxiang.huntingjob.Model.request.OkHttpClientManager;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.BaseActivity;
import com.youzheng.tongxiang.huntingjob.R;
import com.youzheng.tongxiang.huntingjob.UI.Utils.PublicUtils;
import com.youzheng.tongxiang.huntingjob.UI.Utils.UrlUtis;
import com.youzheng.tongxiang.huntingjob.UI.Widget.CircleImageView;
import com.youzheng.tongxiang.huntingjob.UI.dialog.BottomPhotoDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.iwf.photopicker.PhotoPicker;
import me.iwf.photopicker.utils.ImageCaptureManager;
import rx.functions.Action1;

/**
 * Created by qiuweiyu on 2018/2/11.
 */

public class UserCenterActivity extends BaseActivity {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.textHeadTitle)
    TextView textHeadTitle;
    @BindView(R.id.civ)
    CircleImageView civ;
    @BindView(R.id.rl_change_photo)
    RelativeLayout rlChangePhoto;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    ImageCaptureManager captureManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_layout);
        ButterKnife.bind(this);

        initView();

        initDate();
    }

    private void initDate() {
        if (!accessToken.equals("")) {
            Map<String, Object> map = new HashMap<>();
            map.put("accessToken", accessToken);
            OkHttpClientManager.postAsynJson(gson.toJson(map), UrlUtis.USER_INFO_SEE, new OkHttpClientManager.StringCallback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(String response) {
                    BaseEntity entity = gson.fromJson(response, BaseEntity.class);
                    if (entity.getCode().equals(PublicUtils.SUCCESS)) {
                        UserInfoSeeBean userInfoSeeBean = gson.fromJson(gson.toJson(entity.getData()), UserInfoSeeBean.class);
                        if (userInfoSeeBean.getUser().getPhoto()!=null) {
                            Glide.with(mContext).load(userInfoSeeBean.getUser().getPhoto()).into(civ);
                        }
                        tvPhone.setText(userInfoSeeBean.getUser().getUsername());
                    }
                }
            });
        }
    }

    private void initView() {
        btnBack.setVisibility(View.VISIBLE);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        textHeadTitle.setText("个人信息");

        rlChangePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });
    }

    private void takePhoto() {
        RxPermissions permissions = new RxPermissions((UserCenterActivity)mContext);
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
        RxPermissions permissions = new RxPermissions((UserCenterActivity)mContext);
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
                            .start(UserCenterActivity.this ,PhotoPicker.REQUEST_CODE);
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
                            h_map.put("accessToken",accessToken);
                            h_map.put("photo",photoBean.getFilepath());
                            OkHttpClientManager.postAsynJson(gson.toJson(h_map), UrlUtis.EDIT_PERSIOM_INFO, new OkHttpClientManager.StringCallback() {
                                @Override
                                public void onFailure(Request request, IOException e) {

                                }

                                @Override
                                public void onResponse(String response) {
                                    BaseEntity entity1 = gson.fromJson(response,BaseEntity.class);
                                    if (entity1.getCode().equals(PublicUtils.SUCCESS)){
                                        initDate();
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
