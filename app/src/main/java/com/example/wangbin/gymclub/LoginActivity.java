package com.example.wangbin.gymclub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.wangbin.gymclub.net.HttpCallbackListener;
import com.example.wangbin.gymclub.net.HttpSettings;
import com.example.wangbin.gymclub.net.HttpUtil;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */


    // UI references.
    private AutoCompleteTextView mIdView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private IUiListener iuiListener;
    private Tencent mTencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mIdView= (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        Button qqEmailSignInButton = (Button) findViewById(R.id.qq_sign_in_button);
        qqEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginQQ();
            }
        });
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = mIdView.getText().toString();
                String pwd = mPasswordView.getText().toString();
                if (id.length() <= 4 || id.length() > 20) {
                    Toast toast = Toast.makeText(LoginActivity.this, "账号输入应该在5-20位", Toast.LENGTH_SHORT);
                    mIdView.setText("");
                    toast.show();
                } else if (pwd.length() <= 5 || pwd.length() > 20) {
                    Toast toast = Toast.makeText(LoginActivity.this, "密码输入应该在6-20位", Toast.LENGTH_SHORT);
                    mPasswordView.setText("");
                    toast.show();
                } else {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("user_id", id);
                    map.put("pwd", pwd);

                    HttpUtil.sendHttpPostRequest(HttpSettings.httpUrl + HttpSettings.loginUrl, map, new HttpCallbackListener() {
                        @Override
                        public void onFinish(String response) {
                            try {
                                JSONObject res = new JSONObject(response);
                                String result = res.getString("result");
                                if (result.equals("true")) {
                                    JSONObject data = res.getJSONObject("data");
                                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                    i.putExtra("name", data.getString("name"));
                                    startActivity(i);
                                } else {
                                    Looper.prepare();
                                    Toast toast = Toast.makeText(LoginActivity.this, "密码错误或者账号不存在", Toast.LENGTH_SHORT);
                                    mIdView.setText("");
                                    mPasswordView.setText("");
                                    toast.show();
                                    Looper.loop();
                                }
                            } catch (Exception e) {
                                Looper.prepare();
                                Toast toast = Toast.makeText(LoginActivity.this, "密码错误或者账号不存在", Toast.LENGTH_SHORT);
                                mIdView.setText("");
                                mPasswordView.setText("");
                                toast.show();
                                Looper.loop();
                            }

                        }

                        @Override
                        public void onError(Exception e) {
                            Looper.prepare();
                            Toast toast = Toast.makeText(LoginActivity.this, "登录失败,请检查网络", Toast.LENGTH_SHORT);
                            mIdView.setText("");
                            mPasswordView.setText("");
                            toast.show();
                            Looper.loop();
                        }
                    });

                }
            }
        });
        Button mEmailSignInButton1 = (Button) findViewById(R.id.email_sign_in_button1);
        mEmailSignInButton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //attemptLogin();
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }
    public void LoginQQ(){
        iuiListener=new BaseUiListener();
        mTencent = Tencent.createInstance("101538822", this.getApplicationContext());
        mTencent.login(LoginActivity.this,"all", iuiListener);
    }

    private class BaseUiListener implements IUiListener {

        @Override
        public void onComplete(Object response) {
            Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                //获得的数据是JSON格式的，获得你想获得的内容
                //如果你不知道你能获得什么，看一下下面的LOG
                JSONObject openidString = (JSONObject) response;
                //access_token= ((JSONObject) response).getString("access_token");				//expires_in = ((JSONObject) response).getString("expires_in");
            /**到此已经获得OpneID以及其他你想获得的内容了
             QQ登录成功了，我们还想获取一些QQ的基本信息，比如昵称，头像什么的，这个时候怎么办？
             sdk给我们提供了一个类UserInfo，这个类中封装了QQ用户的一些信息，我么可以通过这个类拿到这些信息
             如何得到这个UserInfo类呢？  */
            try {
                String openID = openidString.getString("openid");
                String accessToken = openidString.getString("access_token");
                String expires = openidString.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                UserInfo info = new UserInfo(getApplicationContext(), qqToken);
                info.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object response) {
                        JSONObject json = (JSONObject) response;
                        if (json.has("nickname")) {
                            try {
                                String name = json.getString("nickname");
                                String url = json.getString("figureurl_qq_2");
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                i.putExtra("name", name);
                                i.putExtra("url", url);
                                startActivity(i);
                            } catch (JSONException e) {
                            }
                        }
                    }

                    @Override
                    public void onError(UiError uiError) {

                    }

                    @Override
                    public void onCancel() {

                    }


                });
            }catch (Exception e){

            }
        }

        @Override
        public void onError(UiError e) {
            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onCancel() {
            Toast.makeText(getApplicationContext(), "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mTencent.onActivityResultData(requestCode, resultCode, data, iuiListener);
        if (requestCode == Constants.REQUEST_API) {
            if(requestCode == Constants.REQUEST_LOGIN){
                Tencent.handleResultData(data, iuiListener);
                super.onActivityResult(requestCode, resultCode, data);
            }

        }
    }

}



