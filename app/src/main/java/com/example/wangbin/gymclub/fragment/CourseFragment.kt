package com.example.wangbin.gymclub.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wangbin.gymclub.R
import com.example.wangbin.gymclub.adapter.CourseAdapter

class CourseFragment : Fragment() {
    private var recyclerView:RecyclerView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.fragment_course_layout,container,false)
        var data : MutableList<CourseAdapter.HolderItem> = ArrayList()
        for(i in 0..10){
            data.add( CourseAdapter.HolderItem("减肥",R.drawable.reduce_fat))
        }
        recyclerView = view.findViewById(R.id.recycler_course)
        recyclerView?.layoutManager = LinearLayoutManager(activity!!,LinearLayoutManager.VERTICAL,false)
        var courseAdapter = CourseAdapter(activity!!, data)
        recyclerView?.adapter = courseAdapter
        return view
    }
}