package com.bakarvin.pizzatime.Model.CustomTransaksi;

import java.util.List;

public class CustomTransaksiResponse {
    int kode;
    String pesan;
    List<ModelCustomTransaksi> result;

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

    public List<ModelCustomTransaksi> getResult() {
        return result;
    }

    public void setResult(List<ModelCustomTransaksi> result) {
        this.result = result;
    }
}
