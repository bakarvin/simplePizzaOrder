package com.bakarvin.pizzatime.Model.DetailTransaksi;

import java.util.List;

public class DetailTransaksiResponse {

    private int kode;
    private String pesan;
    private List<ModelDetailTransaksi> result;

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

    public List<ModelDetailTransaksi> getResult() {
        return result;
    }

    public void setResult(List<ModelDetailTransaksi> result) {
        this.result = result;
    }
}
