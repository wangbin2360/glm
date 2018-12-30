package com.example.wangbin.gymclub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangbin.gymclub.net.HttpCallbackListener;
import com.example.wangbin.gymclub.net.HttpSettings;
import com.example.wangbin.gymclub.net.HttpUtil;
import com.example.wangbin.gymclub.service.ContentService;

import org.json.JSONObject;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        Intent intent = getIntent();
        final int id= intent.getIntExtra("id",0);
        final String author= intent.getStringExtra("author");
        final String time= intent.getStringExtra("time");
        final String title= intent.getStringExtra("title");
        final String mode= intent.getStringExtra("mode");
        TextView textView1 = (TextView) findViewById(R.id.article_title);
        TextView textView2 = (TextView) findViewById(R.id.article_author);
        TextView textView3 = (TextView) findViewById(R.id.article_time);
        final TextView textView4 = (TextView) findViewById(R.id.article_content);
        textView1.setText(title);
        textView2.setText("作者：" + author);
        textView3.setText("时间：" + time);
        ImageButton imageButton = (ImageButton) findViewById(R.id.artReturnButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        textView4.setMovementMethod(ScrollingMovementMethod.getInstance());
        if(mode.equals("net")) {

            HttpUtil.sendHttpGetRequestOfNone(HttpSettings.httpUrl + HttpSettings.articleUrl + "/" + id, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    try {

                        JSONObject res = new JSONObject(response);
                        String result = res.getString("result");
                        if (result.equals("true")) {
                            final String content = res.getString("content");
                            ContentService cs = new ContentService(ArticleActivity.this);
                            cs.save(id, title, time, author, content);
                            ArticleActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView4.setText(content);
                                }
                            });

                        }
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onError(Exception e) {
                    Looper.prepare();
                    Toast toast = Toast.makeText(ArticleActivity.this, "登录失败,请检查网络", Toast.LENGTH_SHORT);
                    toast.show();
                    Looper.loop();
                }
            });
        }
        else{
            ContentService cs = new ContentService(ArticleActivity.this);
            final String content=cs.findById(id).getContent();
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView4.setText(content);
                }
            });
        }
    }
}
