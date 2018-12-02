package com.example.wangbin.gymclub.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.wangbin.gymclub.R;
import com.example.wangbin.gymclub.adapter.TeacherAdapter;
import com.example.wangbin.gymclub.model.Teacher;

import java.util.ArrayList;

public class TrainerFragment extends Fragment {
    private RecyclerView rec;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_glm_trainer_layout,container,false);
        rec=(RecyclerView)view.findViewById(R.id.TeacherList);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this.getActivity());
        rec.setLayoutManager(layoutManager);
        final ArrayList<Teacher> list=initCourse();
        TeacherAdapter adapter=new TeacherAdapter(this.getContext(),list);
        rec.setAdapter(adapter);
    }

    private ArrayList<Teacher> initCourse(){
        ArrayList<Teacher> list = new ArrayList<Teacher>();
        list.add(new Teacher("xxx ","听妈妈的话,锻炼身体",R.drawable.reduce_fat,1));
        list.add(new Teacher("yyy","在训练房里,一起摇摆",R.drawable.sculpture,2));
        list.add(new Teacher("zzz","在舞蹈中,成为梦中的若曦",R.drawable.stretch_relaxation,3));
        list.add(new Teacher("aaa","让你一次跑个够",R.drawable.timg,4));
        return  list;
    }
}
