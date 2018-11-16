package com.example.wangbin.gymclub

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.MenuItem
import android.view.ViewParent
import com.example.wangbin.gymclub.adapter.MainFragmentAdapter
import com.example.wangbin.gymclub.fragment.CourseFragment
import com.example.wangbin.gymclub.fragment.HomeFragment
import com.example.wangbin.gymclub.fragment.MyFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener{
    override fun onPageScrollStateChanged(p0: Int) {

    }

    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
    }

    override fun onPageSelected(position: Int) {
        main_navigation.menu.findItem(main_navigation.selectedItemId).isChecked = false
        main_navigation.menu.getItem(position).isChecked = true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navigation_home->{
                main_view_pager.currentItem = 0
                return true
            }
            R.id.navigation_dashboard->{
                main_view_pager.currentItem= 1
                return true
            }
            R.id.navigation_my-> {
                main_view_pager.currentItem = 2
                return true
            }
        }
        return false
    }

    inline fun <reified T:Activity> Context.startActivity(){
        startActivity(Intent(this,T::class.java))
    }

    inline fun <reified T:Fragment> newInstance():T{
        return T::class.java.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_navigation.setOnNavigationItemSelectedListener (this)
        var fragmentList:MutableList<Fragment> = ArrayList<Fragment>()
        fragmentList.add(newInstance<HomeFragment>())
        fragmentList.add(newInstance<CourseFragment>())
        fragmentList.add(newInstance<MyFragment>())
        main_view_pager.adapter = MainFragmentAdapter(supportFragmentManager,this,fragmentList)
        main_view_pager.addOnPageChangeListener(this)
    }
}
