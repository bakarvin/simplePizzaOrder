package com.bakarvin.pizzatime.Model.Transaksi;

public class ModelTransaksi {

    private String id_trans;
    private String username;
    private String tgl_trans;
    private String total_trans;
    private String alamat_user;
    private String status_trans;

    public ModelTransaksi(String id_trans, String username, String tgl_trans, String total_trans, String alamat_user, String status_trans) {
        this.id_trans = id_trans;
        this.username = username;
        this.tgl_trans = tgl_trans;
        this.total_trans = total_trans;
        this.alamat_user = alamat_user;
        this.status_trans = status_trans;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlamat_user() {
        return alamat_user;
    }

    public void setAlamat_user(String alamat_user) {
        this.alamat_user = alamat_user;
    }

    public String getId_trans() {
        return id_trans;
    }

    public void setId_trans(String id_trans) {
        this.id_trans = id_trans;
    }

    public String getTgl_trans() {
        return tgl_trans;
    }

    public void setTgl_trans(String tgl_trans) {
        this.tgl_trans = tgl_trans;
    }

    public String getTotal_trans() {
        return total_trans;
    }

    public void setTotal_trans(String total_trans) {
        this.total_trans = total_trans;
    }

    public String getStatus_trans() {
        return status_trans;
    }

    public void setStatus_trans(String status_trans) {
        this.status_trans = status_trans;
    }
}
