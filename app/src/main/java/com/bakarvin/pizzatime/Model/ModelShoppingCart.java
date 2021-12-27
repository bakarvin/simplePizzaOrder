package com.bakarvin.pizzatime.Model;

public class ModelShoppingCart {
    private int id;
    private String id_menu;
    private String nama;
    private String harga_satuan;
    private String harga_total;
    private int qty;
    private String desc;

    public ModelShoppingCart() {

    }

    public ModelShoppingCart(int id, String id_menu, String nama, String harga_satuan, String harga_total, int qty, String desc) {
        this.id = id;
        this.id_menu = id_menu;
        this.nama = nama;
        this.harga_satuan = harga_satuan;
        this.harga_total = harga_total;
        this.qty = qty;
        this.desc = desc;
    }

    public String getId_menu() {
        return id_menu;
    }

    public void setId_menu(String id_menu) {
        this.id_menu = id_menu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getHarga_satuan() {
        return harga_satuan;
    }

    public void setHarga_satuan(String harga_satuan) {
        this.harga_satuan = harga_satuan;
    }

    public String getHarga_total() {
        return harga_total;
    }

    public void setHarga_total(String harga_total) {
        this.harga_total = harga_total;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
