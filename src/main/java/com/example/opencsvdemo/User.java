package com.example.opencsvdemo;

public class User {

    public String isAdmin;
    public String username;
    public String password;

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String isAdmin() {
        return isAdmin;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(String admin) {
        isAdmin = admin;
    }

    public User(String un, String pw, String isAdmin){
        setUsername(un);
        setPassword(pw);
        setAdmin(isAdmin);
    }
}







