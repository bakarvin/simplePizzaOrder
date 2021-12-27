package com.bakarvin.pizzatime.Model.Alamat;

public class ModelAlamat {
    private String username;
    private String nama_user;
    private String telp_user;
    private String tipe_alamat_user;
    private String kota_user;
    private String kode_pos_user;
    private String alamat_user;
    private String alamat_utama;

    public ModelAlamat(String username, String nama_user, String telp_user, String tipe_alamat_user, String kota_user, String kode_pos_user, String alamat_user, String alamat_utama) {
        this.username = username;
        this.nama_user = nama_user;
        this.telp_user = telp_user;
        this.tipe_alamat_user = tipe_alamat_user;
        this.kota_user = kota_user;
        this.kode_pos_user = kode_pos_user;
        this.alamat_user = alamat_user;
        this.alamat_utama = alamat_utama;
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

    public String getTipe_alamat_user() {
        return tipe_alamat_user;
    }

    public void setTipe_alamat_user(String tipe_alamat_user) {
        this.tipe_alamat_user = tipe_alamat_user;
    }

    public String getKota_user() {
        return kota_user;
    }

    public void setKota_user(String kota_user) {
        this.kota_user = kota_user;
    }

    public String getKode_pos_user() {
        return kode_pos_user;
    }

    public void setKode_pos_user(String kode_pos_user) {
        this.kode_pos_user = kode_pos_user;
    }

    public String getAlamat_user() {
        return alamat_user;
    }

    public void setAlamat_user(String alamat_user) {
        this.alamat_user = alamat_user;
    }

    public String getAlamat_utama() {
        return alamat_utama;
    }

    public void setAlamat_utama(String alamat_utama) {
        this.alamat_utama = alamat_utama;
    }
}
