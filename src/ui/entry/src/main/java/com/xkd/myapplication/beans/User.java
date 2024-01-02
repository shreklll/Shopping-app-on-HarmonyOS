package com.xkd.myapplication.beans;

public class User {
    private String account;
    private String password;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String accout, String password, String name) {
        this.account = accout;
        this.password = password;
        this.name = name;
    }

    public String getAccout() {
        return account;
    }

    public void setAccout(String accout) {
        this.account = accout;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "accout='" + account + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
