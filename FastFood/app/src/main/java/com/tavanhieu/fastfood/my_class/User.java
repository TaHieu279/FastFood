package com.tavanhieu.fastfood.my_class;

import android.net.Uri;

import java.io.Serializable;

public class User implements Serializable {
    private String uid, userName, email, dateOfBirth, address, phoneNumber;
    private Uri imgAvatar;

    public User() {}

    public User(String uid, String userName, String address, String phoneNumber) {
        this.uid = uid;
        this.userName = userName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public User(String userName, String email, String uid) {
        this.userName = userName;
        this.email = email;
        this.uid = uid;
    }

    public User(String uid, String userName, String email, String dateOfBirth, String address, Uri imgAvatar) {
        this.uid = uid;
        this.userName = userName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.imgAvatar = imgAvatar;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Uri getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(Uri imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
