package com.youzheng.tongxiang.huntingjob.UI.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.youzheng.tongxiang.huntingjob.R;

/**
 * Created by qiuweiyu on 2018/2/8.
 */

public class RegisterSuccessDialog extends Dialog {
    private Context context;
    public RegisterSuccessDialog(@NonNull Context context) {
        super(context, R.style.DeleteDialogStyle);
        this.context = context ;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.register_success_dialog_layout, null);
        setContentView(view);
    }
}
