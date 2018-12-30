package com.example.wangbin.gymclub.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangbin.gymclub.R;


public class MyViewHolder extends RecyclerView.ViewHolder  {
    TextView tv1;
    TextView tv2;
    ImageView iv;
    CardView cv;
    View root;


    // 构造函数中添加自定义的接口的参数
    public MyViewHolder(View view) {
        super(view);
        this.root=view;
        cv = (CardView)view.findViewById(R.id.course);
        tv1 = (TextView) view.findViewById(R.id.c_item1);
        tv2 = (TextView) view.findViewById(R.id.c_item2);
        iv = (ImageView) view.findViewById(R.id.image1);

    }



}
