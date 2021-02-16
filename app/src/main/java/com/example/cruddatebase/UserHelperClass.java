package com.example.cruddatebase;

public class UserHelperClass {

    String Uname,Mail,Pword,Cpword;

    public UserHelperClass() {

    }

    public UserHelperClass(String uname, String mail, String pword, String cpword) {
        Uname = uname;
        Mail = mail;
        Pword = pword;
        Cpword = cpword;
    }

    public String getUname() {
        return Uname;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getPword() {
        return Pword;
    }

    public void setPword(String pword) {
        Pword = pword;
    }

    public String getCpword() {
        return Cpword;
    }

    public void setCpword(String cpword) {
        Cpword = cpword;
    }
}
