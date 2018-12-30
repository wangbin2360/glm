package com.example.wangbin.gymclub.model;

public class ArticleItem {
    private int id;
    private String title;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    private String time;
    private String author;
    private String content;
    private int imageid;
    private String mode;

    public ArticleItem(){}
    public ArticleItem(int id, String title, String time, String author, int imageid) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.author = author;
        this.imageid = imageid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleItem(int id, String title, String time, String author, int imageid, String content) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.author = author;
        this.imageid = imageid;
        this.content=content;

    }

    @Override
    public String toString() {
        return "ArticleItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", time='" + time + '\'' +
                ", author='" + author + '\'' +
                ", imageid=" + imageid +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }
}
