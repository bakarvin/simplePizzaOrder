package com.bakarvin.pizzatime.Model.CustomTransaksi;

import java.util.List;

public class ModelCustomTransaksi {

    private String id_trans;
    private String size_custom;
    private List<String> free_topping;
    private List<String> extra_topping;
    private String total_trans;

    public ModelCustomTransaksi(String id_trans, String size_custom, List<String> free_topping, List<String> extra_topping, String total_trans) {
        this.id_trans = id_trans;
        this.size_custom = size_custom;
        this.free_topping = free_topping;
        this.extra_topping = extra_topping;
        this.total_trans = total_trans;
    }

    public String getId_trans() {
        return id_trans;
    }

    public void setId_trans(String id_trans) {
        this.id_trans = id_trans;
    }

    public String getSize_custom() {
        return size_custom;
    }

    public void setSize_custom(String size_custom) {
        this.size_custom = size_custom;
    }

    public List<String> getFree_topping() {
        return free_topping;
    }

    public void setFree_topping(List<String> free_topping) {
        this.free_topping = free_topping;
    }

    public List<String> getExtra_topping() {
        return extra_topping;
    }

    public void setExtra_topping(List<String> extra_topping) {
        this.extra_topping = extra_topping;
    }

    public String getTotal_trans() {
        return total_trans;
    }

    public void setTotal_trans(String total_trans) {
        this.total_trans = total_trans;
    }
}
