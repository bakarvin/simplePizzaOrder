package com.bakarvin.pizzatime.Model.Menu;

public class ModelMenu {

    private int no_menu;
    private String id_menu;
    private String nama_menu;
    private String harga_menu;
    private String kategori_menu;

    public ModelMenu(){}

    public ModelMenu(int no_menu, String id_menu, String nama_menu, String harga_menu, String kategori_menu) {
        this.no_menu = no_menu;
        this.id_menu = id_menu;
        this.nama_menu = nama_menu;
        this.harga_menu = harga_menu;
        this.kategori_menu = kategori_menu;
    }

    public int getNo_menu() {
        return no_menu;
    }

    public void setNo_menu(int no_menu) {
        this.no_menu = no_menu;
    }

    public String getId_menu() {
        return id_menu;
    }

    public void setId_menu(String id_menu) {
        this.id_menu = id_menu;
    }

    public String getNama_menu() {
        return nama_menu;
    }

    public void setNama_menu(String nama_menu) {
        this.nama_menu = nama_menu;
    }

    public String getHarga_menu() {
        return harga_menu;
    }

    public void setHarga_menu(String harga_menu) {
        this.harga_menu = harga_menu;
    }

    public String getKategori_menu() {
        return kategori_menu;
    }

    public void setKategori_menu(String kategori_menu) {
        this.kategori_menu = kategori_menu;
    }
}
