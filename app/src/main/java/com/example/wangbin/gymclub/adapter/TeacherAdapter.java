package com.example.wangbin.gymclub.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangbin.gymclub.R;
import com.example.wangbin.gymclub.TeacherActivity;
import com.example.wangbin.gymclub.model.Teacher;

import java.util.ArrayList;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.MyViewHolder> {
    private Context mContext;
    ArrayList<Teacher> mlist;

    public TeacherAdapter(Context context, ArrayList<Teacher> list) {
        this.mContext = context;
        this.mlist = list;
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.course_item_01, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        myViewHolder.bind(mlist.get(position));

    }

     class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv1;
        private TextView tv2;
        private ImageView iv;
        private Teacher mTeacher;

        // 构造函数中添加自定义的接口的参数
        public MyViewHolder(View view) {
            super(view);
            tv1 = (TextView) view.findViewById(R.id.c_item1);
            tv2 = (TextView) view.findViewById(R.id.c_item2);
            iv = (ImageView) view.findViewById(R.id.image1);
            view.setOnClickListener(this);
        }

        public void bind(Teacher teacher) {
            tv1.setText(teacher.getName());
            tv2.setText(teacher.getIntroduce());
            iv.setImageResource(teacher.getImageId());
            mTeacher = teacher;
        }


        @Override
        public void onClick(View v) {
            Intent i = new Intent(mContext, TeacherActivity.class);
            i.putExtra("name", mTeacher.getName());
            i.putExtra("id", mTeacher.getId());
            i.putExtra("img", mTeacher.getImageId());
            mContext.startActivity(i);
        }
    }
}
