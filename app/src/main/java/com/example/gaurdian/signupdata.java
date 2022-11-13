package com.example.gaurdian;

public class signupdata {
    String name, user, email, phone , password;

    public signupdata() {// EMPTY CONSTRUCTOR IS CREATED IN ORDER TO AVOID THE ERROR
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public signupdata(String name, String user, String email, String phone, String password) {
        this.name = name;
        this.user = user;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
