package com.example.huypm.turtle_ship.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Customer_Employee {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Ten")
    @Expose
    private String ten;
    @SerializedName("SDT")
    @Expose
    private String sDT;
    @SerializedName("Email")
    @Expose
    private String email;
    @SerializedName("NV")
    @Expose
    private String nV;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSDT() {
        return sDT;
    }

    public void setSDT(String sDT) {
        this.sDT = sDT;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNV() {
        return nV;
    }

    public void setNV(String nV) {
        this.nV = nV;
    }

    public Customer_Employee(String id, String ten, String sDT, String email, String nv){
        this.id =id;
        this.ten=ten;
        this.sDT=sDT;
        this.email=email;
        this.nV=nv;
    }

}


