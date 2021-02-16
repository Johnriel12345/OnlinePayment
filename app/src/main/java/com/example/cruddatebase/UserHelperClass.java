package com.example.cruddatebase;

public class UserHelperClass {

    String uName,Mail;

    public UserHelperClass() {

    }

    public UserHelperClass(String uName, String mail) {
        uName = uName;
        Mail = mail;

    }

    public String getUname() {
        return uName;
    }

    public void setUname(String uName) {
        uName = uName;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

}
