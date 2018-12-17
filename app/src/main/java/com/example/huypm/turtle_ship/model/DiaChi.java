package com.example.huypm.turtle_ship.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DiaChi implements Serializable {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Cus_Emp")
    @Expose
    private String cusEmp;
    @SerializedName("ThanhPho")
    @Expose
    private String thanhPho;
    @SerializedName("Quan")
    @Expose
    private String quan;
    @SerializedName("Phuong")
    @Expose
    private String phuong;
    @SerializedName("Duong")
    @Expose
    private String duong;
    @SerializedName("GiaoNhan")
    @Expose
    private String giaoNhan;
    @SerializedName("DCChinh")
    @Expose
    private String dCChinh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCusEmp() {
        return cusEmp;
    }

    public void setCusEmp(String cusEmp) {
        this.cusEmp = cusEmp;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public String getQuan() {
        return quan;
    }

    public void setQuan(String quan) {
        this.quan = quan;
    }

    public String getPhuong() {
        return phuong;
    }

    public void setPhuong(String phuong) {
        this.phuong = phuong;
    }

    public String getDuong() {
        return duong;
    }

    public void setDuong(String duong) {
        this.duong = duong;
    }

    public String getGiaoNhan() {
        return giaoNhan;
    }

    public void setGiaoNhan(String giaoNhan) {
        this.giaoNhan = giaoNhan;
    }

    public String getDCChinh() {
        return dCChinh;
    }

    public void setDCChinh(String dCChinh) {
        this.dCChinh = dCChinh;
    }

    public DiaChi(String  id, String cus_Emp, String tp, String quan, String phuong, String duong, String giaoNhan,String dcchinh){
        this.id=id;
        this.cusEmp=cus_Emp;
        this.thanhPho=tp;
        this.quan=quan;
        this.phuong=phuong;
        this.duong=duong;
        this.giaoNhan=giaoNhan;
        this.dCChinh = dcchinh;
    }

}

