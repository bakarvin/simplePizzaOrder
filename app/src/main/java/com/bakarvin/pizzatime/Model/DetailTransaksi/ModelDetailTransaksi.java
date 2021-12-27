package com.bakarvin.pizzatime.Model.DetailTransaksi;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ModelDetailTransaksi {
    private String id_menu;
    private String nama_menu;
    private String id_trans;
    private String qty_menu;
    private String total_menu;

    public ModelDetailTransaksi(String id_menu, String nama_menu, String id_trans, String qty_menu, String total_menu) {
        this.id_menu = id_menu;
        this.nama_menu = nama_menu;
        this.id_trans = id_trans;
        this.qty_menu = qty_menu;
        this.total_menu = total_menu;
    }

    public ModelDetailTransaksi() {

    }

    public String getId_menu() {
        return id_menu;
    }

    public void setId_menu(String id_menu) {
        this.id_menu = id_menu;
    }

    public String getId_trans() {
        return id_trans;
    }

    public void setId_trans(String id_trans) {
        this.id_trans = id_trans;
    }

    public String getQty_menu() {
        return qty_menu;
    }

    public void setQty_menu(String qty_menu) {
        this.qty_menu = qty_menu;
    }

    public String getTotal_menu() {
        return total_menu;
    }

    public void setTotal_menu(String total_menu) {
        this.total_menu = total_menu;
    }

    public String getNama_menu() {
        return nama_menu;
    }

    public void setNama_menu(String nama_menu) {
        this.nama_menu = nama_menu;
    }

    public JSONObject getJSONOb(){
        JSONObject jsonObj = new JSONObject();
        try {
            jsonObj.put("id_trans", id_trans);
            jsonObj.put("id_menu", id_menu);
            jsonObj.put("nama_menu", nama_menu);
            jsonObj.put("qty_menu", qty_menu);
            jsonObj.put("total_menu", total_menu);
        } catch (JSONException e) {
            Log.d( "JSONException = ", e.getMessage());
        }
        return jsonObj;
    }



}
