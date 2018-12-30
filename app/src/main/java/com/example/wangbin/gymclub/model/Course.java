package com.example.wangbin.gymclub.model;

public class Course {
    private String title;
    private String content;
    private int imageId;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Course(String title, String content, int imageId, String url) {
        this.title=title;
        this.content=content;
        this.imageId = imageId;
        this.url=url;

    }

    @Override
    public String toString() {
        return "Course{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imageId=" + imageId +
                '}';
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
