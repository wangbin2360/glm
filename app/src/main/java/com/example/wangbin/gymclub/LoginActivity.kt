package com.example.wangbin.gymclub

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: Activity() {
    private var mobile_flag:Boolean = false
    private var password_flag:Boolean = false
    inline fun <reified T:Activity> Context.startActivity(){
        startActivity(Intent(this,T::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        et_mobile.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if(it.length == 11){
                        mobile_flag = true
                        update()
                    }else{
                        mobile_flag = false
                        update()
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        et_password.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if(it.length > 0){
                        password_flag = true
                        update()
                    }else{
                        password_flag = false
                        update()
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })
        btn_login.setOnClickListener({
            startActivity<MainActivity>()
        })
        register.setOnClickListener({
            startActivity<RegisterActivity>()
        })
    }

    fun update(){
        if(mobile_flag && password_flag){
            btn_login.isEnabled = true
        }else{
            btn_login.isEnabled = false
        }
    }
}