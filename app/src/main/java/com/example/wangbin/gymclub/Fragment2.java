package com.example.wangbin.gymclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.wangbin.gymclub.adapter.CourseAdapter;
import com.example.wangbin.gymclub.adapter.RecyclerViewOnItemClickListener;
import com.example.wangbin.gymclub.model.Course;

import java.util.ArrayList;

/**
 * Created by IT-CTY on 2018/4/25.
 */

public class Fragment2 extends Fragment {
    private RecyclerView rec;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment2,container,false);
        rec=(RecyclerView)view.findViewById(R.id.courseList);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this.getActivity());
        rec.setLayoutManager(layoutManager);
        final ArrayList<Course> list=initCourse();
        CourseAdapter adapter=new CourseAdapter(this.getContext(),list);

        adapter.setOnItemClickListener(new RecyclerViewOnItemClickListener(){
            @Override
            public void onItemClick(View view, int postion) {
                Course course=list.get(postion);
                Intent i = new Intent(getContext(), VideoActivity.class);
                i.putExtra("title",course.getTitle());
                i.putExtra("url",course.getUrl());
                startActivity(i);
            }
        });
        rec.setAdapter(adapter);
    }

    private ArrayList<Course> initCourse(){
        ArrayList<Course> list = new ArrayList<Course>();
        list.add(new Course("一小时跑步","让全身都动起来",R.mipmap.paobu,"http://39.107.73.232:8080/video/download?name=1.mkv"));
        list.add(new Course("健身训练","让你的身体壮起来",R.mipmap.jianshen,"http://39.107.73.232:8080/video/download?name=12.mkv"));
        list.add(new Course("瑜伽练习","让你的身体美起来",R.mipmap.yujia,"http://39.107.73.232:8080/video/download?name=2.mkv"));
        return  list;
    }
}

