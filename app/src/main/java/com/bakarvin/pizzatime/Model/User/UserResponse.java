package com.bakarvin.pizzatime.Model.User;

import java.util.List;

public class UserResponse {

    private int kode;
    private String pesan;
    private List<ModelUser> result;

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

    public List<ModelUser> getResult() {
        return result;
    }

    public void setResult(List<ModelUser> result) {
        this.result = result;
    }
}
