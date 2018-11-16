package com.example.wangbin.gymclub

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import com.example.wangbin.gymclub.net.RetrofitUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class LoginActivity: Activity() {
    private var mobile_flag:Boolean = false
    private var password_flag:Boolean = false
    private var mPhoneNum : String? = null
    private var mPassword : String? = null
    private var compositeDisposable : CompositeDisposable? = null
    private var disposable : Disposable? = null
    inline fun <reified T:Activity> Context.startActivity(){
        startActivity(Intent(this,T::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        et_mobile.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    mPhoneNum = s.toString()
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
                    mPassword = s.toString()
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
            var login_result : Boolean = false;
            if (mPhoneNum == null || mPassword==null){
                Toast.makeText(this@LoginActivity,resources.getString(R.string.error_login1),Toast.LENGTH_SHORT).show()
            }else if(mPhoneNum!!.length<11){
                Toast.makeText(this@LoginActivity,resources.getString(R.string.error_login2),Toast.LENGTH_SHORT).show()
            }else {
                RetrofitUtils.login(mPhoneNum, mPassword)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<ResponseBody>{
                            override fun onComplete() {
                                if (login_result == true) {
                                    startActivity<MainActivity>()
                                }else{
                                    Toast.makeText(this@LoginActivity,R.string.error_login3,Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onSubscribe(d: Disposable) {
                                disposable = d
                                compositeDisposable?.add(disposable!!)
                            }

                            override fun onNext(t: ResponseBody) {
                                login_result = t.string().toBoolean()
                            }

                            override fun onError(e: Throwable) {
                                Toast.makeText(this@LoginActivity,e.message,Toast.LENGTH_SHORT).show()
                                e.printStackTrace()
                            }

                        })
            }
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

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}