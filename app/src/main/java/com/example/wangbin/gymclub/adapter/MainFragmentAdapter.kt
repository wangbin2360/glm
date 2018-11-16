package com.example.wangbin.gymclub.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainFragmentAdapter : FragmentPagerAdapter {
    private var fragments:List<Fragment>? = null
    private var mContext:Context? = null
    constructor(manager:FragmentManager,context: Context,fragmenetList:List<Fragment>):super(manager){
        fragments = fragmenetList
        mContext = context
    }
    override fun getCount(): Int {
       return fragments?.size?:0
    }

    override fun getItem(index: Int): Fragment {
       return fragments?.get(index)?:Fragment()
    }
}