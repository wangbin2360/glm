package com.example.wangbin.gymclub.model;

public class Teacher {
    private String name;
    private String introduce;
    private int imageId;
    private int id;
    private String phone;
    private String email;
    private String context;
    private String mode;
    private String type;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Teacher(String name, String introduce, int id, String phone, String email, String context, String type) {
        this.name = name;
        this.introduce = introduce;
        this.id = id;
        this.phone = phone;

        this.email = email;
        this.context = context;
        this.type=type;

    }
    public Teacher(){}
    public Teacher(String name, String introduce, int imageId, int id) {
        this.name = name;
        this.introduce = introduce;
        this.imageId = imageId;
        this.id = id;
    }
    public Teacher(int id,String name, String introduce) {
        this.name = name;
        this.introduce = introduce;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", imageId=" + imageId +
                ", id='" + id + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }
}
