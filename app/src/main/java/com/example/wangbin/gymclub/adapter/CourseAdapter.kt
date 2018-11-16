package com.example.wangbin.gymclub.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.wangbin.gymclub.R
import kotlinx.android.synthetic.main.course_item.view.*
class CourseAdapter : RecyclerView.Adapter<CourseAdapter.CourseHolder> {
    private var mContext:Context? = null
    private var mData:List<HolderItem>? = null
    constructor(context:Context,data:List<HolderItem>):super(){
        this.mContext = context
        this.mData = data
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CourseHolder {
        var courseHolder = CourseHolder(LayoutInflater.from(mContext).inflate(R.layout.course_item,p0,false))
        return courseHolder
    }

    override fun getItemCount(): Int {
       return mData?.size!!
    }

    override fun onBindViewHolder(p0: CourseHolder, p1: Int) {
       p0.bind(mData?.get(p1)!!)
    }

    class CourseHolder : RecyclerView.ViewHolder {
        private var mTvCourse : TextView? = null
        private var mImgCourse : ImageView? = null
        constructor(view: View):super(view){
            mTvCourse = view.findViewById(R.id.tv_course)
            mImgCourse = view.findViewById(R.id.img_course)
        }

        fun bind(holderItem: HolderItem){
            mTvCourse?.text = holderItem.text
            mImgCourse?.setImageResource(holderItem.img_url_id)
        }

    }

    class HolderItem(val text: String,val img_url_id: Int)
}