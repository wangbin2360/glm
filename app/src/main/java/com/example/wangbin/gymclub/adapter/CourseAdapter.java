package com.example.wangbin.gymclub.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangbin.gymclub.R;
import com.example.wangbin.gymclub.VideoActivity;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder>{
    private Context context;
    private List<HolderItem> holderItems;
    public CourseAdapter(Context context, List<HolderItem> holderItems){
        this.context = context;
        this.holderItems = holderItems;
    }
    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_item,viewGroup,false);
        return new CourseHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder courseHolder, int i) {
        courseHolder.bind(holderItems.get(i));
    }

    @Override
    public int getItemCount() {
        return holderItems.size();
    }

    class CourseHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textView;
        private ImageView imageView;

        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_course);
            imageView = itemView.findViewById(R.id.img_course);
            itemView.setOnClickListener(this);
        }

        public void bind(HolderItem holderItem){
            textView.setText(holderItem.text);
            imageView.setImageResource(holderItem.img_id);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, VideoActivity.class);
            context.startActivity(i);
        }
    }
    public static class HolderItem{
        public String text;
        public int img_id;

        public HolderItem(String text, int img_id) {
            this.text = text;
            this.img_id = img_id;
        }
    }
}
