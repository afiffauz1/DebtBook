package com.example.pengendaliapi.debtbook.Model;

public class Users {
    private String uid;
    private String email;
    private String username;
    private String fullname;
    private String phone;
    private String password;

    public Users(String uid, String email, String username, String fullname, String phone, String password) {
        this.uid = uid;
        this.email = email;
        this.username = username;
        this.fullname = fullname;
        this.phone = phone;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
