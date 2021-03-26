package com.nk.user.dto;

public class PasswordPoJo {
    String oldPassword;
    String password;
    Long id;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PasswordPoJo{" +
                "oldPassword='" + oldPassword + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }
}
