package com.bakarvin.pizzatime.Model;

public class ModelTopping {
    private int imgTopping;
    private String namaTopping;
    private int hargaTopping;
    private Boolean clickedTopping = false;

    public ModelTopping() {
    }

    public ModelTopping(int imgTopping, String namaTopping, int hargaTopping) {
        this.imgTopping = imgTopping;
        this.namaTopping = namaTopping;
        this.hargaTopping = hargaTopping;
    }

    public int getImgTopping() {
        return imgTopping;
    }

    public void setImgTopping(int imgTopping) {
        this.imgTopping = imgTopping;
    }

    public String getNamaTopping() {
        return namaTopping;
    }

    public void setNamaTopping(String namaTopping) {
        this.namaTopping = namaTopping;
    }
//
//    public String getHargaTopping() {
//        return hargaTopping;
//    }
//
//    public void setHargaTopping(String hargaTopping) {
//        this.hargaTopping = hargaTopping;
//    }
    public int getHargaTopping() {
        return hargaTopping;
    }

    public void setHargaTopping(int hargaTopping) {
        this.hargaTopping = hargaTopping;
    }

    public void setClickedTopping(Boolean selected){
        clickedTopping = selected;
    }
    public boolean clickedTopping(){
        return clickedTopping;
    }

}
