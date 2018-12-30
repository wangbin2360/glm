package com.example.wangbin.gymclub.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.wangbin.gymclub.R;
import com.example.wangbin.gymclub.model.ArticleItem;
import com.example.wangbin.gymclub.util.DBOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class ContentService {
    private DBOpenHelper dbOpenHelper;

    public ContentService(Context context){
        this.dbOpenHelper = new DBOpenHelper(context);
    }

    //添加的操作
    public void save(int id,String name,String time,String author,String content){
        //如果要对数据进行更改，就调用此方法得到用于操作数据库的实例,该方法以读和写方式打开数据库
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("delete from article where id=?", new Object[]{id});
        db.execSQL("insert into article (id,title,time ,author,content) values(?,?,?,?,?)", new Object[]{id,name,time,author,content});
        db.close();
    }

    //删除的操作
    public void delete(int id){
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        db.execSQL("delete from article where id=?", new Object[]{id});
        db.close();
    }
    public List<ArticleItem> findAll()
    {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        List<ArticleItem> list = new ArrayList<ArticleItem>();
        Cursor cursor = db.rawQuery("select * from article",null);
        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String author = cursor.getString(cursor.getColumnIndex("author"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            ArticleItem userInfo = new ArticleItem(id,title,time,author, R.mipmap.yd);
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
    public ArticleItem findById(int id)
    {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        Cursor cursor =db.rawQuery("select * from article where id=?",new String[]{id+""});

        ArticleItem user = new ArticleItem();

        while(cursor.moveToNext())
        {

            String author = cursor.getString(cursor.getColumnIndex("author"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            user.setAuthor(author);
            user.setContent(content);
            user.setTime(time);
            user.setTitle(title);
            break;
        }
        cursor.close();
        db.close();
        return user;

    }


}
