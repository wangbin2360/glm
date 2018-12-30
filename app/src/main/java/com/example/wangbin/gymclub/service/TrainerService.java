package com.example.wangbin.gymclub.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.wangbin.gymclub.R;
import com.example.wangbin.gymclub.model.Teacher;
import com.example.wangbin.gymclub.util.DBOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TrainerService {
    private DBOpenHelper dbOpenHelper;

    public TrainerService(Context context){
        this.dbOpenHelper = new DBOpenHelper(context);
    }

    //添加的操作
    public void save(int id,String name,String phone,String email,String context,String type,String intro){
        //如果要对数据进行更改，就调用此方法得到用于操作数据库的实例,该方法以读和写方式打开数据库
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("delete from teacher where id=?", new Object[]{id});
        db.execSQL("insert into teacher (id,name,type ,phone,email,intro,context) values(?,?,?,?,?,?,?)", new Object[]{id,name,type,phone,email,intro,context});
        db.close();
    }

    //删除的操作
    public void delete(int id){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("delete from teacher where id=?", new Object[]{id});
        db.close();
    }
    public List<Teacher> findAll()
    {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        List<Teacher> list = new ArrayList<Teacher>();
        Cursor cursor = db.rawQuery("select * from teacher",null);
        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String intro = cursor.getString(cursor.getColumnIndex("intro"));

            Teacher userInfo = new Teacher(id,name,intro);
            if (userInfo.getId()==1)
                userInfo.setImageId(R.mipmap.zjl);
            else if (userInfo.getId()==2)
                userInfo.setImageId(R.mipmap.wf);
            else if (userInfo.getId()==3)
                userInfo.setImageId(R.mipmap.lss);
            else if (userInfo.getId()==4)
                userInfo.setImageId(R.mipmap.ycq);
            list.add(userInfo);
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * 通过id 用户信息
     * id 用户id
     * */
    public Teacher findById(int id)
    {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from teacher where id=?",new String[]{id+""});

        Teacher userInfo=new Teacher();
        while(cursor.moveToNext())
        {

            id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String intro = cursor.getString(cursor.getColumnIndex("intro"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            String context = cursor.getString(cursor.getColumnIndex("context"));
            userInfo.setId(id);
            userInfo.setContext(context);
            userInfo.setEmail(email);
            userInfo.setPhone(phone);
            userInfo.setType(type);
            userInfo.setContext(context);
            userInfo.setIntroduce(intro);
            userInfo.setName(name);
            if (userInfo.getId()==1)
                userInfo.setId(R.mipmap.zjl);
            else if (userInfo.getId()==2)
                userInfo.setId(R.mipmap.wf);
            else if (userInfo.getId()==3)
                userInfo.setId(R.mipmap.lss);
            else if (userInfo.getId()==4)
                userInfo.setId(R.mipmap.ycq);
            break;
        }
        cursor.close();
        db.close();
        return userInfo;

    }
}
