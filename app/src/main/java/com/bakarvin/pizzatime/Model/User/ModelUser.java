package com.bakarvin.pizzatime.Model.User;

public class ModelUser {

    private String username;
    private String nama_user;
    private String telp_user;
    private String password_user;

    public ModelUser(String username, String nama_user, String telp_user, String password_user) {
        this.username = username;
        this.nama_user = nama_user;
        this.telp_user = telp_user;
        this.password_user = password_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNama_user() {
        return nama_user;
    }

    public void setNama_user(String nama_user) {
        this.nama_user = nama_user;
    }

    public String getTelp_user() {
        return telp_user;
    }

    public void setTelp_user(String telp_user) {
        this.telp_user = telp_user;
    }

    public String getPassword_user() {
        return password_user;
    }

    public void setPassword_user(String password_user) {
        this.password_user = password_user;
    }
}
