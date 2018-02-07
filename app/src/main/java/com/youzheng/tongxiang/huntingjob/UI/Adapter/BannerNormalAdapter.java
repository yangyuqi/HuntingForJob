package com.youzheng.tongxiang.huntingjob.UI.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.youzheng.tongxiang.huntingjob.R;

import java.util.List;

/**
 * Created by qiuweiyu on 2018/2/7.
 */

public class BannerNormalAdapter extends StaticPagerAdapter {

    private List<Integer> banner_date;

    public BannerNormalAdapter(List<Integer> entity) {
        banner_date = entity;
    }

    @Override
    public View getView(ViewGroup container, final int position) {
        View new_view = LayoutInflater.from(container.getContext()).inflate(R.layout.image_new_layout,null);
        ImageView view = (ImageView) new_view.findViewById(R.id.iv_new);
        Glide.with(container.getContext()).load(banner_date.get(position)).placeholder(R.mipmap.ic_launcher).into(view);
        return new_view;
    }


    @Override
    public int getCount() {
        return banner_date.size();
    }
}