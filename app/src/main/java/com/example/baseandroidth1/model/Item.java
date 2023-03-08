package com.example.baseandroidth1.model;

import com.example.baseandroidth1.R;

public class Item {

    public static int count = 0;

    private Integer id;
    private int img;
    private int position;
    private String name;
    private String content;
    private String date;
    private Boolean gender;

    public Item() {}

    public Item(String name, String content, String date, Boolean gender) {
        this.img = gender ? R.drawable.icon_male : R.drawable.icon_girl;
        this.name = name;
        this.content = content;
        this.date = date;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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
