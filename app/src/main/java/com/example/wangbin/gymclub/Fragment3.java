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


import com.example.wangbin.gymclub.adapter.ArticleItemAdapter;
import com.example.wangbin.gymclub.adapter.RecyclerViewOnItemClickListener;
import com.example.wangbin.gymclub.model.ArticleItem;
import com.example.wangbin.gymclub.net.HttpCallbackListener;
import com.example.wangbin.gymclub.net.HttpSettings;
import com.example.wangbin.gymclub.net.HttpUtil;
import com.example.wangbin.gymclub.service.ContentService;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IT-CTY on 2018/4/25.
 */

public class Fragment3 extends Fragment {
    private RecyclerView rec;
    private ArrayList<ArticleItem> list;
    private ArticleItemAdapter aia;
    private SwipeRefreshLayout mRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment3,container,false);
        rec=(RecyclerView)view.findViewById(R.id.newList);
        mRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.article_refresh);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this.getActivity());
        rec.setLayoutManager(layoutManager);
        list=new ArrayList<ArticleItem>();
        aia=new ArticleItemAdapter(this.getContext(),list);
        aia.setOnItemClickListener(new RecyclerViewOnItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                ArticleItem ai = list.get(postion);
                Intent i = new Intent(getContext(), ArticleActivity.class);
                i.putExtra("id", ai.getId());
                i.putExtra("title", ai.getTitle());
                i.putExtra("time", ai.getTime());
                i.putExtra("author", ai.getAuthor());
                i.putExtra("mode",ai.getMode());
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
        HttpUtil.sendHttpGetRequestOfNone(HttpSettings.httpUrl + HttpSettings.articleUrl+"/all", new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                try {

                    JSONObject res = new JSONObject(response);
                    String result = res.getString("result");
                    if(result.equals("true")){

                        JSONArray ja=res.getJSONArray("data");
                        final ArrayList<ArticleItem> list1 = new ArrayList<ArticleItem>();
                        for(int i=0;i<ja.length();i++){
                            JSONObject jo=ja.getJSONObject(i);
                            ArticleItem ai=new ArticleItem(jo.getInt("id"),jo.getString("title")
                                    ,jo.getString("time"),jo.getString("author"),R.drawable.shu);
                            ai.setMode("net");
                            list1.add(ai);

                        }
                        list1.get(0).setTitle("这才是跑步最佳的时长，快来get下");
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
                final ArrayList<ArticleItem> list1 = new ArrayList<ArticleItem>();
                ContentService cs = new ContentService(getContext());
                List<ArticleItem> list2=cs.findAll();
                for(int i=0;i<list2.size();i++){
                    ArticleItem ai=list2.get(i);
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

