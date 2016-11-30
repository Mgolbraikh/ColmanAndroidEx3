package com.example.owner.studentsapp;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by owner on 29-Nov-16.
 */

public class Student {

    public Student(String name, int id, String phone, String address) {
        this.name = name;
        Id = id;
        this.phone = phone;
        this.address = address;
        this.picture = picture;
    }

    private String name;
    private int Id;
    private Boolean checked;
    private String phone;
    private String address;
    private Image picture;



    public String getName() {
        return name;
    }

    public int getId() {
        return Id;
    }

    public Boolean getChecked() {
        return checked;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Image getPicture() {
        return picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

    }