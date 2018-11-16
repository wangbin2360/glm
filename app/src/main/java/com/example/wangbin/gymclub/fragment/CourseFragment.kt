package com.example.wangbin.gymclub.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wangbin.gymclub.R
import com.example.wangbin.gymclub.adapter.Course1Adapter
import kotlinx.android.synthetic.main.fragment_course_layout.*

class CourseFragment : Fragment() {
    private var recyclerView:RecyclerView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view:View = inflater.inflate(R.layout.fragment_course_layout,container,false)
        var data : MutableList<Course1Adapter.HolderItem> = ArrayList()
        for(i in 0..10){
            data.add( Course1Adapter.HolderItem("减肥",R.drawable.reduce_fat))
        }
        recyclerView = view.findViewById(R.id.recycler_course)
        recyclerView?.layoutManager = LinearLayoutManager(activity!!,LinearLayoutManager.VERTICAL,false)
        var courseAdapter = Course1Adapter(activity!!,data)
        recyclerView?.adapter = courseAdapter
        return view
    }
}