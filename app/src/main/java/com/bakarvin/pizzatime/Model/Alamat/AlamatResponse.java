package com.bakarvin.pizzatime.Model.Alamat;

import java.util.List;

public class AlamatResponse {
    private int kode;
    private String pesan;
    private List<ModelAlamat> result;

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

    public List<ModelAlamat> getResult() {
        return result;
    }

    public void setResult(List<ModelAlamat> result) {
        this.result = result;
    }
}
