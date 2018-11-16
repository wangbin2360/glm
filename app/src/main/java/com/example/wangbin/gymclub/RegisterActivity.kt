package com.example.wangbin.gymclub

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.wangbin.gymclub.net.RetrofitUtils
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_register.*
import okhttp3.*
import java.io.IOException
import java.util.concurrent.TimeUnit

class RegisterActivity : Activity() {
    private var mPhoneNum: String? = null
    private var mobile_flag: Boolean = false
    private var password_flag: Boolean = false
    private var password:String? = null
    private var phoneNum:String? = null
    private var disposable:Disposable? = null

    inline fun <reified T : Activity> Context.startActivity() {
        startActivity(Intent(this, T::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        close.setOnClickListener({finish()})
        et_mobile.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                phoneNum = s.toString()
                s?.let {
                    mPhoneNum = s.toString()
                    if (it.length == 11) {
                        mobile_flag = true
                        update()
                    } else {
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
        et_password.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                password = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_password.text != null && !et_password.text.toString().equals("") && et_password_again.text != null
                        && !et_password_again.text.toString().equals("")) {
                    if (et_password_again.text.toString().equals(et_password.text.toString())) {
                        password_flag = true
                        update()
                    } else {
                        password_flag = false
                        update()
                    }
                } else {
                    password_flag = false
                    update()
                }
            }

        })
        et_password_again.addTextChangedListener(object  : TextWatcher{
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (et_password.text != null && !et_password.text.toString().equals("") && et_password_again.text != null
                        && !et_password_again.text.toString().equals("")) {
                    if (et_password_again.text.toString().equals(et_password.text.toString())) {
                        password_flag = true
                        update()
                    } else {
                        password_flag = false
                        update()
                    }
                } else {
                    password_flag = false
                    update()
                }
            }

        })
        btn_register.setOnClickListener({
            var register_result: Boolean = false
//            var okHttpClient: OkHttpClient = OkHttpClient.Builder().connectTimeout(5000, TimeUnit.MILLISECONDS).readTimeout(5000, TimeUnit.MILLISECONDS).build();
//            var requestBody:RequestBody = FormBody.Builder().add("phonenum",phoneNum)
//                    .add("password",password)
//                    .build()
//            var request : Request = Request.Builder().url("http://172.27.141.31:8110/register").post(requestBody).build()
//            okHttpClient.newCall(request).enqueue(object : Callback{
//                override fun onFailure(call: Call?, e: IOException?) {
//
//                }
//
//                override fun onResponse(call: Call?, response: Response?) {
//                    Log.d("SSS",response?.body()?.string())
//                }
//
//            })
//        })
            if (et_password.text.toString().equals(et_password_again.text.toString())) {
                RetrofitUtils.register(phoneNum, password)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<ResponseBody> {
                            override fun onComplete() {
                                if (register_result == true) {
                                    startActivity<LoginActivity>()
                                    finish()
                                }else{
                                    Toast.makeText(this@RegisterActivity,R.string.error_register,Toast.LENGTH_SHORT).show()
                                }
                            }

                            override fun onSubscribe(d: Disposable) {
                                disposable = d
                            }

                            override fun onNext(t: ResponseBody) {
                                register_result = t.string().toBoolean()
                            }

                            override fun onError(e: Throwable) {
                                e.printStackTrace()
                            }

                        })
            }
        })

    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }

    fun update() {
        if (!password_flag) {
            if (et_password.text != null && !et_password.text.toString().equals("") && et_password_again.text != null
                    && !et_password_again.text.toString().equals("")) {
                error_message.visibility = View.VISIBLE
            } else {
                error_message.visibility = View.GONE
            }
        } else {
            error_message.visibility = View.GONE
        }
        if (mobile_flag && et_password_again.text != null && !et_password_again.text.toString().equals("")
                && et_password.text != null && !et_password.text.toString().equals("")) {
            btn_register.isEnabled = true
        } else {
            btn_register.isEnabled = false
        }
    }
}