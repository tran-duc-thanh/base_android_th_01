package com.example.baseandroidth1.model;

public class Item {

    public static int count = 0;

    private Integer id;
    private int img;
    private String name;
    private String content;
    private String date;
    private Boolean gender;

    public Item() {
        this.id = ++count;
    }

    public Item(int img, String name, String content, String date, Boolean gender) {
        this.img = img;
        this.id = ++count;
        this.name = name;
        this.content = content;
        this.date = date;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
}
