package com.example.wangbin.gymclub;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class TeacherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Intent intent = getIntent();
        String name= intent.getStringExtra("name");
        int img=intent.getIntExtra("img",0);
        int id=intent.getIntExtra("id",0);
        ImageButton imageButton= (ImageButton) findViewById(R.id.teacher_Return_Button);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        ImageView imageView=(ImageView)findViewById(R.id.teacher_image);
        imageView.setImageResource(img);
        TextView textView=(TextView)findViewById(R.id.teacherName);
        textView.setText(name);

        TextView textView1=(TextView)findViewById(R.id.teacherPhone);
        TextView textView2=(TextView)findViewById(R.id.teacherEmail);


        final SpannableStringBuilder style= new SpannableStringBuilder();
        final SpannableStringBuilder style2= new SpannableStringBuilder();
        //设置文字
        style.append("18401605989");
        style2.append("14301087@bjtu.edu.cn");
        //设置部分文字点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                showChoise(((TextView)widget).getText().toString());
            }
        };
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                showChoise2(((TextView)widget).getText().toString());
            }
        };
        style.setSpan(clickableSpan, 0, 11, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style2.setSpan(clickableSpan1, 0, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView2.setText(style2);
        textView1.setText(style);
        textView1.setMovementMethod(LinkMovementMethod.getInstance());//不设置 没有点击事件
        textView1.setHighlightColor(Color.TRANSPARENT);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());//不设置 没有点击事件
        textView2.setHighlightColor(Color.TRANSPARENT);

    }

    private void showChoise(final String phone)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("请选择");
        builder.setMessage("您选择了"+phone+",请选择以下操作");

        //监听下方button点击事件
        builder.setPositiveButton("拨打电话", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phone);
                intent.setData(data);
                try {
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    Toast toast = Toast.makeText(TeacherActivity.this, "没有相应的应用", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        builder.setNeutralButton("发送短信", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String action = Intent.ACTION_SENDTO;
                Uri uri = Uri.parse("smsto:" + phone);
                Intent intent = new Intent(action, uri);
                try {
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    Toast toast = Toast.makeText(TeacherActivity.this, "没有相应的应用", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        //设置对话框是可取消的
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        dialog.show();

    }
    private void showChoise2(final String email)
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("请确认");
        builder.setMessage("是否前去发送邮件给"+email+"?");

        //监听下方button点击事件
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent data=new Intent(Intent.ACTION_SENDTO);
                data.setData(Uri.parse("mailto:"+email));
                try {
                    startActivity(data);
                }catch (ActivityNotFoundException e){
                    Toast toast = Toast.makeText(TeacherActivity.this, "没有相应的应用", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        //设置对话框是可取消的
        builder.setCancelable(true);
        AlertDialog dialog=builder.create();
        dialog.show();

    }
}
