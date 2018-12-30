package com.example.wangbin.gymclub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.wangbin.gymclub.net.HttpCallbackListener;
import com.example.wangbin.gymclub.net.HttpSettings;
import com.example.wangbin.gymclub.net.HttpUtil;

import org.json.JSONObject;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText mId;
    private EditText mPhone;
    private EditText mPassword,mPassword2;
    private EditText mName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ImageButton mEmailView = (ImageButton) findViewById(R.id.imageButton11);
        Button singupButton = (Button) findViewById(R.id.account_sign_up_button);
        mEmailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();
                finish();
            }
        });
        mId= (EditText) findViewById(R.id.userid);
        mPhone= (EditText) findViewById(R.id.phone);
        mName= (EditText) findViewById(R.id.name);
        mPassword= (EditText) findViewById(R.id.password1);
        mPassword2= (EditText) findViewById(R.id.password2);

        singupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mId.getText().toString();
                String pwd = mPassword.getText().toString();
                String phone = mPhone.getText().toString();
                String name = mName.getText().toString();
                String pwd2 = mPassword2.getText().toString();
                if (id.length() <= 4 || id.length() > 20) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "账号输入应该在5-20位", Toast.LENGTH_SHORT);
                    mId.setText("");
                    toast.show();
                } else if (pwd.length() <= 5 || pwd.length() > 20) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "密码输入应该在6-20位", Toast.LENGTH_SHORT);
                    mPassword.setText("");
                    toast.show();
                } else if (name.length()<=1) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "姓名输入错误", Toast.LENGTH_SHORT);
                    mName.setText("");
                    toast.show();
                } else if (pwd2.length() <= 5 || pwd2.length() > 20) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "确认密码输入应该在6-20位", Toast.LENGTH_SHORT);
                    mPassword2.setText("");
                    toast.show();
                } else if (phone.length() !=11) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "手机输入应该为11位", Toast.LENGTH_SHORT);
                    mPhone.setText("");
                    toast.show();
                } else if (!pwd.equals(pwd2)) {
                    Toast toast = Toast.makeText(RegisterActivity.this, "确认密码与密码输入不一致", Toast.LENGTH_SHORT);
                    mPassword.setText("");
                    mPassword2.setText("");
                    toast.show();
                } else {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("user_id", id);
                    map.put("user_pwd", pwd);
                    map.put("user_phone", phone);
                    map.put("user_name", name);
                    HttpUtil.sendHttpPostRequest(HttpSettings.httpUrl + HttpSettings.registerUrl, map, new HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            try {
                                JSONObject res = new JSONObject(response);
                                String result = res.getString("result");
                                if (result.equals("true")) {
                                    JSONObject data = res.getJSONObject("data");
                                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                    i.putExtra("name", data.getString("name"));
                                    i.putExtra("phone", data.getString("phone"));
                                    startActivity(i);
                                } else {
                                    Looper.prepare();
                                    Toast toast = Toast.makeText(RegisterActivity.this, "账号或手机已经被注册", Toast.LENGTH_SHORT);
                                    mId.setText("");
                                    mPhone.setText("");
                                    toast.show();
                                    Looper.loop();
                                }

                            } catch (Exception e) {
                                Looper.prepare();
                                Toast toast = Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT);
                                mId.setText("");
                                mPhone.setText("");
                                mName.setText("");
                                mPassword.setText("");
                                mPassword2.setText("");
                                toast.show();
                                Looper.loop();
                            }

                        }
                        @Override
                        public void onError(Exception e) {
                            Looper.prepare();
                            Toast toast = Toast.makeText(RegisterActivity.this, "注册失败,请检查网络", Toast.LENGTH_SHORT);
                            toast.show();
                            Looper.loop();
                        }
                    });

                }
            }
        });
    }
}
