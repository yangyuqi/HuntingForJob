package com.youzheng.tongxiang.huntingjob.UI.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.Prestener.activity.FillInfoActivity;
import com.youzheng.tongxiang.huntingjob.Prestener.activity.RegisterPhoneActivity;
import com.youzheng.tongxiang.huntingjob.R;

import butterknife.BindView;

/**
 * Created by qiuweiyu on 2018/2/8.
 */

public class RegisterSuccessDialog extends Dialog {
    TextView tvTime;
    private Context context;
    private CountDownTimer downTimer ;
    private int countDownSecond = 3;//倒计时多少秒

    public RegisterSuccessDialog(@NonNull Context context) {
        super(context, R.style.DeleteDialogStyle);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.register_success_dialog_layout, null);
        tvTime = view.findViewById(R.id.tv_time);
        setContentView(view);
        downTimer = new CountDownTimer(countDownSecond*1000, 1000) {
            @Override
            public void onTick(long l) {
                tvTime.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                if (isShowing()) {
                    context.startActivity(new Intent(context, FillInfoActivity.class));
                    dismiss();
                    ((RegisterPhoneActivity)context).finish();
                }
            }
        };
        downTimer.start();
    }
}
