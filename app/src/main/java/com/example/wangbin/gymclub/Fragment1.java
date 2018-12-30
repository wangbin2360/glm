package com.example.wangbin.gymclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by IT-CTY on 2018/4/25.
 */

public class Fragment1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment1,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle =this.getArguments();//得到从Activity传来的数据
        String mess = null;
        String url=null;
        if(bundle!=null){
            DisplayImageOptions op=new DisplayImageOptions.Builder().build();
            ImageLoaderConfiguration con=new ImageLoaderConfiguration.Builder(this.getContext())
                    .defaultDisplayImageOptions(op)
                    .build();
            ImageLoader.getInstance().init(con);
            mess = bundle.getString("name");
            url = bundle.getString("url");
        }
        TextView mView = (TextView)getActivity().findViewById(R.id.editText13);
        ImageView img=getActivity().findViewById(R.id.image1);
        if (url !=null)
            ImageLoader.getInstance().displayImage(url,img);
        mView.setText(mess);
        Button mEmailSignInButton1 = (Button)getActivity().findViewById(R.id.email_sign_out_button1);
        mEmailSignInButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();
                Intent i =new Intent(getActivity(),LoginActivity.class);
                startActivity(i);
            }
        });
    }
}


