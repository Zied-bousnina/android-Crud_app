package com.example.androidprojet;


public class UsersItem {

    String userID;
    String userName;
    String userTel;


    public UsersItem() {
    }

    public UsersItem(String userID, String userName, String userTel) {
        this.userID = userID;
        this.userName = userName;
        this.userTel = userTel;

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserEmail(String userEmail) {
        this.userTel = userEmail;
    }


}
