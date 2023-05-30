package com.testing.parkpro;

public class UserModel {
    private String fullname, number, email;

    public UserModel() {
    }

    public UserModel(String fullname, String number, String email) {
        this.fullname = fullname;
        this.number = number;
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
