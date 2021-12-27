package com.bakarvin.pizzatime.Model.Transaksi;

import java.util.List;

public class TransaksiResponse {

    private int kode;
    private String pesan;
    private List<ModelTransaksi> result;

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

    public List<ModelTransaksi> getResult() {
        return result;
    }

    public void setResult(List<ModelTransaksi> result) {
        this.result = result;
    }
}
