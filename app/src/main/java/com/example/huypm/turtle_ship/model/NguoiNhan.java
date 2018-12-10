package com.example.huypm.turtle_ship.model;

public class NguoiNhan {
    private int Id = -1;
    private String Ten = "";
    private String SDT = "";

    public NguoiNhan(){}

    public NguoiNhan(int id, String ten , String sdt){
        this.Id = id;
        this.Ten = ten;
        this.SDT = sdt;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

}
