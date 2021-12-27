package com.bakarvin.pizzatime.Model.Menu;

import java.util.List;

public class MenuResponse {

    private int kode;
    private String pesan;
    private List<ModelMenu> result;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ModelMenu> getResult() {
        return result;
    }

    public void setResult(List<ModelMenu> result) {
        this.result = result;
    }
}
