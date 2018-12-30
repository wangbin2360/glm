package com.example.wangbin.gymclub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wangbin.gymclub.adapter.RecyclerViewOnItemClickListener;
import com.example.wangbin.gymclub.adapter.TeacherAdapter;
import com.example.wangbin.gymclub.model.Teacher;
import com.example.wangbin.gymclub.net.HttpCallbackListener;
import com.example.wangbin.gymclub.net.HttpSettings;
import com.example.wangbin.gymclub.net.HttpUtil;
import com.example.wangbin.gymclub.service.TrainerService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragment4 extends Fragment {
    private RecyclerView rec;
    private ArrayList<Teacher> list;
    private TeacherAdapter aia;
    private SwipeRefreshLayout mRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment4,container,false);
        rec=(RecyclerView)view.findViewById(R.id.TeacherList);
        mRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.teacher_refresh);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        rec.setLayoutManager(layoutManager);
        list=new ArrayList<Teacher>();
        aia=new TeacherAdapter(this.getContext(),list);

        TeacherAdapter adapter = new TeacherAdapter(this.getContext(), list);

        rec.setAdapter(adapter);
        aia.setOnItemClickListener(new RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Teacher ai = list.get(postion);
                Intent i = new Intent(getContext(), TeacherActivity.class);
                i.putExtra("id", ai.getId());
                i.putExtra("name", ai.getName());
                i.putExtra("mode",ai.getMode());
                i.putExtra("imageid",ai.getImageId());
                startActivity(i);
            }
        });
        rec.setAdapter(aia);
        getList();

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                getList();
                mRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void getList(){
        HttpUtil.sendHttpGetRequestOfNone(HttpSettings.httpUrl + HttpSettings.trainerUrl+"/all", new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                try {

                    JSONObject res = new JSONObject(response);
                    String result = res.getString("result");
                    if(result.equals("true")){

                        JSONArray ja=res.getJSONArray("data");
                        final ArrayList<Teacher> list1 = new ArrayList<Teacher>();
                        for(int i=0;i<ja.length();i++){
                            JSONObject jo=ja.getJSONObject(i);
                            Teacher ai=new Teacher(jo.getInt("id"),jo.getString("name")
                                    ,jo.getString("intro"));
                            if (ai.getId()==1)
                                ai.setImageId(R.mipmap.zjl);
                            else if (ai.getId()==2)
                                ai.setImageId(R.mipmap.wf);
                            else if (ai.getId()==3)
                                ai.setImageId(R.mipmap.lss);
                            else if (ai.getId()==4)
                                ai.setImageId(R.mipmap.ycq);
                            ai.setMode("net");
                            list1.add(ai);

                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                list.clear();
                                list.addAll(list1);
                                aia.notifyDataSetChanged();
                            }
                        });
                    }
                } catch (Exception e) {
                    Looper.prepare();
                    Toast toast = Toast.makeText(getContext(), "数据获取失败", Toast.LENGTH_SHORT);
                    toast.show();
                    Looper.loop();
                }
            }
            @Override
            public void onError(Exception e) {
                final ArrayList<Teacher> list1 = new ArrayList<Teacher>();
                TrainerService cs = new TrainerService(getContext());
                List<Teacher> list2=cs.findAll();
                for(int i=0;i<list2.size();i++){
                    Teacher ai=list2.get(i);
                    ai.setMode("loacl");
                    list1.add(ai);
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        list.addAll(list1);
                        aia.notifyDataSetChanged();
                        Toast toast = Toast.makeText(getContext(), "获取本地缓存", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }
        });
    }
}
